package com.example.gymtaw.dto;

import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.ExerciseHasSessionEntityId;
import com.example.gymtaw.entity.SessionEntity;
import lombok.Data;

@Data
public class ExerciseHasSession {
    private ExerciseHasSessionId id;
    private Exercise exercise;
    private Session session;
}
