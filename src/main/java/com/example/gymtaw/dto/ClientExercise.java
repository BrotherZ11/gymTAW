package com.example.gymtaw.dto;

import com.example.gymtaw.entity.ClientExerciseEntityId;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.UserEntity;
import lombok.Data;

@Data
public class ClientExercise {
    private ClientExerciseId id;
    private User user;
    private Exercise exercise;
    private String reps;
    private String sets;
    private String weight;
    private Double calories;
    private Double distance;
}
