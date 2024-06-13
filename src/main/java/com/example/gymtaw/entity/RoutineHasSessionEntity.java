package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "routine_has_session", schema = "gymtaw", catalog = "")
@IdClass(RoutineHasSessionEntityPK.class)
public class RoutineHasSessionEntity {
    @Id
    @Column(name = "routine_idroutine", nullable = false)
    private Integer routineIdroutine;
    @Id
    @Column(name = "session_id", nullable = false)
    private Integer sessionId;
    @Id
    @Column(name = "day", nullable = false)
    private Integer day;
    @ManyToOne
    @JoinColumn(name = "routine_idroutine", referencedColumnName = "idroutine", nullable = false)
    private RoutineEntity routineByRoutineIdroutine;
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
    private SessionEntity sessionBySessionId;

    public Integer getRoutineIdroutine() {
        return routineIdroutine;
    }

    public void setRoutineIdroutine(Integer routineIdroutine) {
        this.routineIdroutine = routineIdroutine;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineHasSessionEntity that = (RoutineHasSessionEntity) o;
        return Objects.equals(routineIdroutine, that.routineIdroutine) && Objects.equals(sessionId, that.sessionId) && Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineIdroutine, sessionId, day);
    }

    public RoutineEntity getRoutineByRoutineIdroutine() {
        return routineByRoutineIdroutine;
    }

    public void setRoutineByRoutineIdroutine(RoutineEntity routineByRoutineIdroutine) {
        this.routineByRoutineIdroutine = routineByRoutineIdroutine;
    }

    public SessionEntity getSessionBySessionId() {
        return sessionBySessionId;
    }

    public void setSessionBySessionId(SessionEntity sessionBySessionId) {
        this.sessionBySessionId = sessionBySessionId;
    }
}
