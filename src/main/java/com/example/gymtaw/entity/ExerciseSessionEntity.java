package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "exercise_session", schema = "gymtaw", catalog = "")
public class ExerciseSessionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exercise_sessioncol", nullable = false)
    private Integer exerciseSessioncol;
    @Basic
    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;
    @Basic
    @Column(name = "session_id", nullable = false)
    private Integer sessionId;
    @Basic
    @Column(name = "order", nullable = true)
    private Integer order;
    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = false)
    private ExerciseEntity exerciseByExerciseId;
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
    private SessionEntity sessionBySessionId;

    public Integer getExerciseSessioncol() {
        return exerciseSessioncol;
    }

    public void setExerciseSessioncol(Integer exerciseSessioncol) {
        this.exerciseSessioncol = exerciseSessioncol;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseSessionEntity that = (ExerciseSessionEntity) o;
        return Objects.equals(exerciseSessioncol, that.exerciseSessioncol) && Objects.equals(exerciseId, that.exerciseId) && Objects.equals(sessionId, that.sessionId) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseSessioncol, exerciseId, sessionId, order);
    }

    public ExerciseEntity getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(ExerciseEntity exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }

    public SessionEntity getSessionBySessionId() {
        return sessionBySessionId;
    }

    public void setSessionBySessionId(SessionEntity sessionBySessionId) {
        this.sessionBySessionId = sessionBySessionId;
    }
}
