package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ExerciseHasSessionEntityId implements java.io.Serializable {
    private static final long serialVersionUID = 2761221926982042939L;
    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExerciseHasSessionEntityId entity = (ExerciseHasSessionEntityId) o;
        return Objects.equals(this.exerciseId, entity.exerciseId) &&
                Objects.equals(this.sessionId, entity.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, sessionId);
    }

}