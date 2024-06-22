package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.UserHasTrainerId;
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
public class UserHasTrainerEntityId implements Serializable, DTO<UserHasTrainerId>{
    private static final long serialVersionUID = 3113045283274485412L;
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

    public UserHasTrainerId toDTO() {
        UserHasTrainerId userHasTrainerId = new UserHasTrainerId();
        userHasTrainerId.setUser_id(this.userId);
        userHasTrainerId.setTrainer_id(this.trainerId);
        return userHasTrainerId;
    }
}