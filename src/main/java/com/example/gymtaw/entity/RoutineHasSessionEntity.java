package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "routine_has_session")
public class RoutineHasSessionEntity {
    @EmbeddedId
    private RoutineHasSessionEntityId id;

    @MapsId("routineId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routine_id", nullable = false)
    private RoutineEntity routine;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    public int getDay() {
        return id.getDay();
    }
}