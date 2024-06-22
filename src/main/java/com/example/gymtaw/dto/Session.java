package com.example.gymtaw.dto;

import com.example.gymtaw.entity.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class Session {
    private Integer id;
    private String name;
    private Integer trainerId;
    private Set<Integer> exerciseHasSessions = new LinkedHashSet<>();
    private Set<RoutineHasSessionEntityId> routineHasSessions = new LinkedHashSet<>();
    private Set<Integer> types = new LinkedHashSet<>();
}