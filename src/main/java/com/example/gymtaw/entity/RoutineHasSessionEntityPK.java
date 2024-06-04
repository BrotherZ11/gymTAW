package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class RoutineHasSessionEntityPK implements Serializable {
    @Column(name = "routine_idroutine", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routineIdroutine;
    @Column(name = "session_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;
    @Column(name = "day", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer day;

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
        RoutineHasSessionEntityPK that = (RoutineHasSessionEntityPK) o;
        return Objects.equals(routineIdroutine, that.routineIdroutine) && Objects.equals(sessionId, that.sessionId) && Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineIdroutine, sessionId, day);
    }
}
