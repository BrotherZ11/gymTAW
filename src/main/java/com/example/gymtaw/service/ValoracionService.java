package com.example.gymtaw.service;

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
import com.example.gymtaw.ui.FiltroValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        // Busca o crea la valoracion para el ejercicio

        List<ValoracionEntity> val = valoracionRepository.getValoracionesByExercise(exercise.getId());
        boolean valoracionExists = false;
        if (val != null) {
            for (ValoracionEntity v : val) {
                if (v.getUser().getId().equals(usuario.getId())) {
                    if ("1".equals(done)) {
                        v.setDone((byte) 1); // Marca como completado
                    } else {
                        v.setDone((byte) 0); // Marca como NO completado
                    }
                    valoracionRepository.save(v);
                    valoracionExists = true;
                    break;
                }
            }
        }

        if (!valoracionExists && "1".equals(done)) {
            ValoracionEntity newValoracion = new ValoracionEntity();
            ValoracionEntityId valoracionId = new ValoracionEntityId();
            valoracionId.setUserId(usuario.getId());
            valoracionId.setExerciseId(idEjercicio);
            newValoracion.setId(valoracionId);
            newValoracion.setUser(userRepository.findById(usuario.getId()).orElse(new UserEntity()));
            newValoracion.setExercise(exerciseRepository.findById(exercise.getId()).orElse(new ExerciseEntity()));
            newValoracion.setDone((byte) 1);
            valoracionRepository.save(newValoracion);
        }
    }

    public List<Valoracion> getValoracionesByExercises(List<Exercise> ejercicios) {
        List<ValoracionEntity> valoraciones = new ArrayList<>();
        ValoracionEntity valoracion = new ValoracionEntity();
        for (Exercise exercise : ejercicios) {
          valoracion = valoracionRepository.getValoracionByExercise(exercise.getId());
          valoraciones.add(valoracion);
        }

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

    public List<Valoracion> findValoracionEntitiesByIdClient(Integer idCliente){
        List<ValoracionEntity> valoraciones = valoracionRepository.findValoracionEntitiesByIdCliente(idCliente);
        return this.entidadesADTO(valoraciones);
    }
}
