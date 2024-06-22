package com.example.gymtaw.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Routine {
    private Integer id;
    private String name;
    private String description;
    private LocalDate date;
    private Integer trainerId;
    private List<Session> sessions;
}
