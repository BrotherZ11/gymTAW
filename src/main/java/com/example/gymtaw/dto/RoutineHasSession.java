//David Zarzavilla Borrego
package com.example.gymtaw.dto;

import lombok.Data;

@Data
public class RoutineHasSession {
    private RoutineHasSessionId id;
    private Session session;
    private Routine routine;
}
