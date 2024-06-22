package com.example.gymtaw.dto;


import com.example.gymtaw.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Exercise {
    private Integer id;
    private String name;
    private String description;
    private String video;
    private Type typeIdtype;
    private List<ClientExerciseEntityId> clientExercises;
    private List<ExerciseHasSessionEntityId> exerciseHasSessions;
    private List<ValoracionEntityId> valoracions;
}
