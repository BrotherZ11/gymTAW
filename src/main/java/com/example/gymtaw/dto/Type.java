//David Zarzavilla Borrego 100%
package com.example.gymtaw.dto;

import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.SessionEntity;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class Type {
    private Integer id;
    private String name;
    private Set<Integer> exercises = new LinkedHashSet<>();
    private Set<Integer> routines = new LinkedHashSet<>();
    private Set<Integer> sessions = new LinkedHashSet<>();
}
