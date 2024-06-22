package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.RoutineHasSession;
import com.example.gymtaw.dto.UserHasTrainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "routine_has_session")
public class RoutineHasSessionEntity implements DTO<RoutineHasSession> {
    @EmbeddedId
    private RoutineHasSessionEntityId id;

    @MapsId("routineId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "routine_id", nullable = false)
    private RoutineEntity routine;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    public int getDay() {
        return id.getDay();
    }

    public RoutineHasSession toDTO() {
        RoutineHasSession routineHasSession = new RoutineHasSession();
        routineHasSession.setId(this.id.toDTO());
        routineHasSession.setRoutine(this.routine.toDTO());
        routineHasSession.setSession(this.session.toDTO());
        return routineHasSession;
    }
}