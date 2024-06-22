package com.example.gymtaw.dto;

import lombok.Data;

@Data
public class ExerciseHasSessionId {
    private Integer exerciseId;
    private Integer sessionId;
    private Integer order;
}
