package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ExerciseHasSessionEntityId implements Serializable {
    private static final long serialVersionUID = -6353819351898563205L;
    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Column(name = "`order`", nullable = false)
    private Integer order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExerciseHasSessionEntityId entity = (ExerciseHasSessionEntityId) o;
        return Objects.equals(this.exerciseId, entity.exerciseId) &&
                Objects.equals(this.sessionId, entity.sessionId) &&
                Objects.equals(this.order, entity.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, sessionId, order);
    }

}