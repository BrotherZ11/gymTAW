package com.example.gymtaw.dto;

import lombok.Data;

@Data
public class RoutineHasSessionId {
    private Integer routineId;
    private Integer sessionId;
    private Integer day;
}
