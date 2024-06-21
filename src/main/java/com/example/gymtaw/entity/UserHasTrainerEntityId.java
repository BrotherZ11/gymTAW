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
public class UserHasTrainerEntityId implements java.io.Serializable {
    private static final long serialVersionUID = -5390409715576860477L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "trainer_id", nullable = false)
    private Integer trainerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserHasTrainerEntityId entity = (UserHasTrainerEntityId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.trainerId, entity.trainerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, trainerId);
    }

}