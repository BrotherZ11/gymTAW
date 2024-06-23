package com.example.gymtaw.service;
//Marta Granado Rodríguez 80%
import com.example.gymtaw.dao.ClientExerciseRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dao.ValoracionRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.dto.Valoracion;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.UserEntity;
import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.entity.ValoracionEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValoracionService extends DTOService<Valoracion, ValoracionEntity>{
    @Autowired
    protected ValoracionRepository valoracionRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ExerciseRepository exerciseRepository;

    @Autowired
    protected ClientExerciseRepository clientExerciseRepository;


    public void buscarOCrearValoracion(Exercise exercise, User usuario, String done, Integer idEjercicio) {
        // Busca las valoraciones del ejercicio especificado
        List<ValoracionEntity> valoraciones = valoracionRepository.getValoracionesByExercise(exercise.getId());

        boolean valoracionExistente = false;

        // Si hay valoraciones, busca si ya existe una para el usuario
        if (valoraciones != null) {
            for (ValoracionEntity valoracion : valoraciones) {
                if (valoracion.getUser().getId().equals(usuario.getId())) {
                    // Actualiza el estado de "done" según el valor recibido
                    valoracion.setDone("1".equals(done) ? (byte) 1 : (byte) 0);
                    valoracionRepository.save(valoracion);
                    valoracionExistente = true;
                    break;
                }
            }
        }

        // Si no se encontró una valoración existente para el usuario, crea una nueva si "done" es "1"
        if (!valoracionExistente && "1".equals(done)) {
            ValoracionEntity nuevaValoracion = new ValoracionEntity();
            ValoracionEntityId valoracionId = new ValoracionEntityId();
            valoracionId.setUserId(usuario.getId());
            valoracionId.setExerciseId(idEjercicio);
            nuevaValoracion.setId(valoracionId);
            nuevaValoracion.setUser(userRepository.findById(usuario.getId()).orElseThrow(() -> new RuntimeException("User not found")));
            nuevaValoracion.setExercise(exerciseRepository.findById(exercise.getId()).orElseThrow(() -> new RuntimeException("Exercise not found")));
            nuevaValoracion.setDone((byte) 1);
            valoracionRepository.save(nuevaValoracion);
        }
    }


    public List<Valoracion> getValoracionesByUsuario( User usuario) {
        UserEntity user = userRepository.findById(usuario.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        List<ValoracionEntity> valoraciones = valoracionRepository.findValoracionEntitiesByIdCliente(user.getId());

        return this.entidadesADTO(valoraciones);

    }

    public void buscarOCrearValoracionSinDone(Exercise exercise, User usuario, Integer exerciseId, Integer stars) {
        List<ValoracionEntity> val = valoracionRepository.getValoracionesByExercise(exercise.getId());
        boolean valoracionExists = false;
        if (val != null) {
            for (ValoracionEntity v : val) {
                if (v.getUser().getId().equals(usuario.getId())) {
                    v.setStars(stars);  // Set the rating
                    valoracionRepository.save(v);
                    valoracionExists = true;
                    break;
                }
            }
        }

        if (!valoracionExists) {
            ValoracionEntity nuevaValoracion = new ValoracionEntity();
            ValoracionEntityId valoracionEntityId = new ValoracionEntityId();
            valoracionEntityId.setUserId(usuario.getId());
            valoracionEntityId.setExerciseId(exerciseId);

            nuevaValoracion.setId(valoracionEntityId);
            nuevaValoracion.setUser(userRepository.findById(usuario.getId()).orElse(new UserEntity()));
            nuevaValoracion.setExercise(exerciseRepository.findById(exercise.getId()).orElse(new ExerciseEntity()));
            nuevaValoracion.setStars(stars);
            nuevaValoracion.setDone((byte) 1); // Marking the rating as done

            valoracionRepository.save(nuevaValoracion);
        }
    }


    public void guardarReview(User usuario, Integer exerciseId, String review) {
        ValoracionEntityId valoracionId = new ValoracionEntityId();
        valoracionId.setUserId(usuario.getId());
        valoracionId.setExerciseId(exerciseId);

        Optional<ValoracionEntity> valoracionEntity = this.valoracionRepository.findById(valoracionId);
        if (valoracionEntity.isPresent()) {
            ValoracionEntity valoracion = valoracionEntity.get();
            valoracion.setReview(review);
            valoracionRepository.save(valoracion);
        }
    }

    public List<Valoracion> findValoracionByIdClient(Integer idCliente){
        List<ValoracionEntity> valoraciones = valoracionRepository.findValoracionEntitiesByIdCliente(idCliente);
        return this.entidadesADTO(valoraciones);
    }

    public void crearValoracionNueva(Integer idCliente, Integer idEjercicio){
        ValoracionEntity valoracion = valoracionRepository.getValoracionesByExerciseAndCliente(idEjercicio, idCliente);
        if(valoracion == null){
            ExerciseEntity ejercicio = exerciseRepository.findById(idEjercicio).orElse(null);
            UserEntity cliente = userRepository.findById(idCliente).orElse(null);

            ValoracionEntityId valoracionEntityId = new ValoracionEntityId();
            valoracionEntityId.setExerciseId(idEjercicio);
            valoracionEntityId.setUserId(idCliente);

            valoracion = new ValoracionEntity();
            valoracion.setId(valoracionEntityId);
            valoracion.setExercise(ejercicio);
            valoracion.setUser(cliente);
            valoracion.setDone((byte) 0);

            valoracionRepository.save(valoracion);
        }
    }
}
