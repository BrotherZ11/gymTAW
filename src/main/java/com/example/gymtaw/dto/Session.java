package com.example.gymtaw.dto;

import com.example.gymtaw.entity.ExerciseHasSessionEntity;
import com.example.gymtaw.entity.RoutineHasSessionEntity;
import com.example.gymtaw.entity.TypeEntity;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class Session {
    private Integer id;
    private String name;
    private Integer trainerId;
    private Set<ExerciseHasSessionEntity> exerciseHasSessions = new LinkedHashSet<>();
    private Set<RoutineHasSessionEntity> routineHasSessions = new LinkedHashSet<>();
    private Set<TypeEntity> types = new LinkedHashSet<>();
}
