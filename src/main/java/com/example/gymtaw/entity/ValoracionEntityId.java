package com.example.gymtaw.entity;
//Marta Granado Rodríguez (toDTO())
import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.ValoracionId;
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
public class ValoracionEntityId implements Serializable, DTO<ValoracionId> {
    private static final long serialVersionUID = -8405939330370840664L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "trainer_id", nullable = false)
    private Integer trainerId;

    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ValoracionEntityId entity = (ValoracionEntityId) o;
        return Objects.equals(this.exerciseId, entity.exerciseId) &&
                Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.trainerId, entity.trainerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, userId, trainerId);
    }

    public ValoracionId toDTO() {
        ValoracionId entity = new ValoracionId();
        entity.setExerciseId(this.exerciseId);
        entity.setUserId(this.userId);
        entity.setTrainerId(this.trainerId);
        return entity;
    }
}