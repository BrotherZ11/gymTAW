package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_has_trainer", schema = "gymtaw", catalog = "")
@IdClass(UserHasTrainerEntityPK.class)
public class UserHasTrainerEntity {
    @Id
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Integer userId;

    @Id
    @Column(name = "trainer_id", nullable = false, insertable = false, updatable = false)
    private Integer trainerId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByUserId;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByTrainerId;

    // Getters and setters

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

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public UserEntity getUserByTrainerId() {
        return userByTrainerId;
    }

    public void setUserByTrainerId(UserEntity userByTrainerId) {
        this.userByTrainerId = userByTrainerId;
    }

    // equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasTrainerEntity that = (UserHasTrainerEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(trainerId, that.trainerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, trainerId);
    }
}
