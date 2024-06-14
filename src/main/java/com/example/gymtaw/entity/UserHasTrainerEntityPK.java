package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserHasTrainerEntityPK implements Serializable {
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "trainer_id", nullable = false)
    private Integer trainerId;

    public UserHasTrainerEntityPK() {}

    public UserHasTrainerEntityPK(Integer userId, Integer trainerId) {
        this.userId = userId;
        this.trainerId = trainerId;
    }

    // Getters, setters, equals, and hashCode methods

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasTrainerEntityPK that = (UserHasTrainerEntityPK) o;
        return Objects.equals(userId, that.userId) && Objects.equals(trainerId, that.trainerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, trainerId);
    }
}
