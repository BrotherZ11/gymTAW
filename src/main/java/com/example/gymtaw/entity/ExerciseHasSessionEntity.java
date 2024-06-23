package com.example.gymtaw.entity;
//Marta Granado Rodríguez
import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.ExerciseHasSession;
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
public class ExerciseHasSessionEntity implements DTO<ExerciseHasSession> {
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

    public ExerciseHasSession toDTO() {
        ExerciseHasSession exerciseHasSession = new ExerciseHasSession();
        exerciseHasSession.setId(this.id.toDTO());
        exerciseHasSession.setExercise(this.exercise.toDTO());
        exerciseHasSession.setSession(this.session.toDTO());
        return exerciseHasSession;
    }
}