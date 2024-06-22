package com.example.gymtaw.entity;

import com.example.gymtaw.dto.RoutineHasSession;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "exercise_has_session")
public class ExerciseHasSessionEntity {
    @EmbeddedId
    private ExerciseHasSessionEntityId id;

    @MapsId("exerciseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    public ExerciseHasSessionEntity toDTO() {
        ExerciseHasSessionEntity exerciseHasSessionEntity = new ExerciseHasSessionEntity();
        exerciseHasSessionEntity.setId(this.id.toDTO());
        exerciseHasSessionEntity.setExercise(this.exercise);
        exerciseHasSessionEntity.setSession(this.session);
        return exerciseHasSessionEntity;
    }
}