package com.example.gymtaw.service;

import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.Rol;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.RolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService extends DTOService<Exercise, ExerciseEntity>{

    @Autowired
    ExerciseRepository exerciseRepository;

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
}
