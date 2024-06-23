package com.example.gymtaw.service;

import com.example.gymtaw.dao.ClientExerciseRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.dto.Valoracion;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.ui.FiltroEjercicio;
import com.example.gymtaw.ui.FiltroValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExerciseService extends DTOService<Exercise, ExerciseEntity>{

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    public List<Exercise> listarEjercicios(){
        List<ExerciseEntity> exer = exerciseRepository.findAll();
        return this.entidadesADTO(exer);
    }

    public List<Exercise> getExercisesByIdSession(Integer idSesion){
        List<ExerciseEntity> exer = exerciseRepository.getExercisesByIdSession(idSesion);
        return this.entidadesADTO(exer);
    }

    public Exercise findByidEjercicio(Integer idEjercicio) {
        ExerciseEntity exer = exerciseRepository.findById(idEjercicio).orElse(null);
        if (exer != null) {
            return exer.toDTO();
        } else {
            return null;
        }
    }

    public Exercise getExercisesByIdEjercicio(Integer idEjercicio) {
        ExerciseEntity exer = exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
        if (exer != null) {
            return exer.toDTO();
        } else {
            return null;
        }
    }

    public List<Exercise> filtrarValoraciones(List<Exercise> ejercicios, User usuario, FiltroValoracion filtro) {

        List<Integer> exerciseIds = clientExerciseRepository.findExerciseIdByClientId(usuario.getId());
        // Iteramos por cada ejercicio para buscar el que tenga la valoracion que buscamos.
        for (Integer exerciseId : exerciseIds) {
            ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(exerciseId);
            if (ejercicio != null) {
                Set<ValoracionEntity> valoraciones = ejercicio.getValoracions();
                boolean addEjercicioALista = true;

                // Vemos si el ejercicio tiene la misma valoracion que el filtro
                if (filtro.getStars() != null && filtro.getStars() > 0) {
                    addEjercicioALista = false; // Empieza asumiendo que no tiene la valoracion buscada

                    for (ValoracionEntity valoracion : valoraciones) {
                        if (valoracion.getUser().getId().equals(usuario.getId()) && valoracion.getDone() == 1) {
                            if (valoracion.getStars() != null && valoracion.getStars() == filtro.getStars()) {
                                addEjercicioALista = true; // Ha encontrado la valoracion
                                break;
                            }
                        }
                    }
                }
                if (addEjercicioALista) {
                    ejercicios.add(ejercicio.toDTO());

                }
            }
        }
        return ejercicios;
    }

    public List<Exercise> filtrarEjercicios( FiltroEjercicio filtroEj) {
        List<ExerciseEntity> ejercicios = new ArrayList<>();
        if (filtroEj.getNombre() != null && !filtroEj.getNombre().isEmpty()) {
            ejercicios = exerciseRepository.findExercisesByName(filtroEj.getNombre());
        }
        return this.entidadesADTO(ejercicios);
    }

    public List<Exercise> findExercisesWithAReviewByIdClient(Integer idCliente){
        List<ExerciseEntity> ejercicios = exerciseRepository.getExerciseEntitiesByIdClienteAndHaveReview(idCliente);
        return this.entidadesADTO(ejercicios);
    }

    public List<Exercise> findAllExercisesByUsuarioId(Integer id) {
        List<ExerciseEntity> ejercicios = exerciseRepository.getExerciseEntitiesByIdClienteAndHaveReview(id);
        return this.entidadesADTO(ejercicios);
    }

    public List<Exercise> findExercisesWithDataByIdSesion(Integer idSesion){
        List<ExerciseEntity> ejercicios = exerciseRepository.getExercisesByIdSessionWithData(idSesion);
        return this.entidadesADTO(ejercicios);
    }
}
