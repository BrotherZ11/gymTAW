package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exercise_has_session")
public class ExerciseHasSessionEntity {
    @EmbeddedId
    private ExerciseHasSessionEntityId id;

    @MapsId("exerciseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exerciseEntity;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity sessionEntity;

    @Column(name = "`order`", nullable = false)
    private Integer order;

}