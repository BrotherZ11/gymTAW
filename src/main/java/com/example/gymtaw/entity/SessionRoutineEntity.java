package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "session_routine", schema = "gymtaw", catalog = "")
public class SessionRoutineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idsession_routine", nullable = false)
    private int idsessionRoutine;
    @Basic
    @Column(name = "session_id", nullable = false)
    private int sessionId;
    @Basic
    @Column(name = "routine_idroutine", nullable = false)
    private int routineIdroutine;
    @Basic
    @Column(name = "day", nullable = true)
    private Integer day;

    public int getIdsessionRoutine() {
        return idsessionRoutine;
    }

    public void setIdsessionRoutine(int idsessionRoutine) {
        this.idsessionRoutine = idsessionRoutine;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getRoutineIdroutine() {
        return routineIdroutine;
    }

    public void setRoutineIdroutine(int routineIdroutine) {
        this.routineIdroutine = routineIdroutine;
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
        SessionRoutineEntity that = (SessionRoutineEntity) o;
        return idsessionRoutine == that.idsessionRoutine && sessionId == that.sessionId && routineIdroutine == that.routineIdroutine && Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsessionRoutine, sessionId, routineIdroutine, day);
    }
}
