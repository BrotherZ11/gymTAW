package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "exercise_session", schema = "gymtaw", catalog = "")
public class ExerciseSessionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exercise_sessioncol", nullable = false)
    private int exerciseSessioncol;
    @Basic
    @Column(name = "exercise_id", nullable = false)
    private int exerciseId;
    @Basic
    @Column(name = "session_id", nullable = false)
    private int sessionId;
    @Basic
    @Column(name = "order", nullable = true)
    private Integer order;

    public int getExerciseSessioncol() {
        return exerciseSessioncol;
    }

    public void setExerciseSessioncol(int exerciseSessioncol) {
        this.exerciseSessioncol = exerciseSessioncol;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
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
        return exerciseSessioncol == that.exerciseSessioncol && exerciseId == that.exerciseId && sessionId == that.sessionId && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseSessioncol, exerciseId, sessionId, order);
    }
}
