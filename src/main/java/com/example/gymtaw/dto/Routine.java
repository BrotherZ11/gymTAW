package com.example.gymtaw.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class Routine {
    private Integer id = -1;
    private String name;
    private String description;
    private LocalDate date;
    private Integer trainerId;
    private Set<Integer> sessions;
    private Set<Integer> types;
}
