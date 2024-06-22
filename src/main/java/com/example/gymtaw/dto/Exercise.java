package com.example.gymtaw.dto;

import com.example.gymtaw.entity.ClientExerciseEntityId;
import com.example.gymtaw.entity.ExerciseHasSessionEntityId;
import com.example.gymtaw.entity.ValoracionEntityId;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Exercise {
    private Integer id;
    private String name;
    private String description;
    private String video;
    private Type typeIdtype;
    private List<ClientExerciseEntityId> clientExercises;
    private List<Integer> exerciseHasSessions;
    private List<ValoracionEntityId> valoracions;
}
