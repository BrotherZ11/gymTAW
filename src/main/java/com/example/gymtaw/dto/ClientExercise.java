package com.example.gymtaw.dto;
//Marta Granado Rodríguez 100%
import lombok.Data;

@Data
public class ClientExercise {
    private ClientExerciseId id;
    private User user;
    private User trainer;
    private Exercise exercise;
    private String reps;
    private String sets;
    private String weight;
    private Double calories;
    private Double distance;
}
