package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "session_routine", schema = "gymtaw", catalog = "")
public class SessionRoutineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idsession_routine", nullable = false)
    private Integer idsessionRoutine;
    @Basic
    @Column(name = "session_id", nullable = false)
    private Integer sessionId;
    @Basic
    @Column(name = "routine_idroutine", nullable = false)
    private Integer routineIdroutine;
    @Basic
    @Column(name = "day", nullable = true)
    private Integer day;
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
    private SessionEntity sessionBySessionId;
    @ManyToOne
    @JoinColumn(name = "routine_idroutine", referencedColumnName = "idroutine", nullable = false)
    private RoutineEntity routineByRoutineIdroutine;

    public Integer getIdsessionRoutine() {
        return idsessionRoutine;
    }

    public void setIdsessionRoutine(Integer idsessionRoutine) {
        this.idsessionRoutine = idsessionRoutine;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getRoutineIdroutine() {
        return routineIdroutine;
    }

    public void setRoutineIdroutine(Integer routineIdroutine) {
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
        return Objects.equals(idsessionRoutine, that.idsessionRoutine) && Objects.equals(sessionId, that.sessionId) && Objects.equals(routineIdroutine, that.routineIdroutine) && Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsessionRoutine, sessionId, routineIdroutine, day);
    }

    public SessionEntity getSessionBySessionId() {
        return sessionBySessionId;
    }

    public void setSessionBySessionId(SessionEntity sessionBySessionId) {
        this.sessionBySessionId = sessionBySessionId;
    }

    public RoutineEntity getRoutineByRoutineIdroutine() {
        return routineByRoutineIdroutine;
    }

    public void setRoutineByRoutineIdroutine(RoutineEntity routineByRoutineIdroutine) {
        this.routineByRoutineIdroutine = routineByRoutineIdroutine;
    }
}
