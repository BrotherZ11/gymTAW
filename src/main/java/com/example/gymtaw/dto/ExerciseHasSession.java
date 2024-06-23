//David Zarzavilla Borrego 100%
package com.example.gymtaw.dto;

import lombok.Data;

@Data
public class ExerciseHasSession {
    private ExerciseHasSessionId id;
    private Exercise exercise;
    private Session session;
}
