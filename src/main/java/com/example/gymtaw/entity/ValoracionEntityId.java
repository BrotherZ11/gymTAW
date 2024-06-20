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
public class ValoracionEntityId implements java.io.Serializable {
    private static final long serialVersionUID = -577275918188921086L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ValoracionEntityId entity = (ValoracionEntityId) o;
        return Objects.equals(this.exerciseId, entity.exerciseId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, userId);
    }

}