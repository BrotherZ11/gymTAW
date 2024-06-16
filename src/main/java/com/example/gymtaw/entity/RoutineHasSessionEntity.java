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

    @MapsId("routineIdroutine")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routine_idroutine", nullable = false)
    private RoutineEntity routineIdroutine;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "sessionentity_id")
    private SessionEntity sessionEntity;

}