package com.example.gymtaw.service;

import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ExerciseService extends DTOService<Exercise, ExerciseEntity>{

    @Autowired
    protected ExerciseRepository exerciseRepository;

    public Exercise finById(Integer id) {
        ExerciseEntity exercise = this.exerciseRepository.findById(id).orElse(null);
        if (exercise != null) {
            return exercise.toDTO();
        } else {
            return null;
        }
    }
}
