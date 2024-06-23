package com.example.gymtaw.service;
//Marta Granado Rodríguez 70%
import com.example.gymtaw.dao.ClientExerciseRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.ui.FiltroEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService extends DTOService<Exercise, ExerciseEntity>{

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

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

    public List<Exercise> filtrarValoraciones(Integer idUsuario, Integer stars) {

        List<ExerciseEntity> ejercicios = exerciseRepository.getExercisesByNumEstrellasEIdUsuario(idUsuario, stars);
        if(stars == 0){
            ejercicios = exerciseRepository.getExerciseEntitiesByIdClienteAndHaveReview(idUsuario);
        }
        return this.entidadesADTO(ejercicios);
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
