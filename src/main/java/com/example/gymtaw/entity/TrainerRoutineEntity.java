package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "trainer_routine", schema = "gymtaw", catalog = "")
public class TrainerRoutineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iduser_routine", nullable = false)
    private Integer iduserRoutine;
    @Basic
    @Column(name = "trainer_id", nullable = false)
    private Integer trainerId;
    @Basic
    @Column(name = "routine_idroutine", nullable = false)
    private Integer routineIdroutine;
    @Basic
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByTrainerId;
    @ManyToOne
    @JoinColumn(name = "routine_idroutine", referencedColumnName = "idroutine", nullable = false)
    private RoutineEntity routineByRoutineIdroutine;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByUserId;

    public Integer getIduserRoutine() {
        return iduserRoutine;
    }

    public void setIduserRoutine(Integer iduserRoutine) {
        this.iduserRoutine = iduserRoutine;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Integer getRoutineIdroutine() {
        return routineIdroutine;
    }

    public void setRoutineIdroutine(Integer routineIdroutine) {
        this.routineIdroutine = routineIdroutine;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerRoutineEntity that = (TrainerRoutineEntity) o;
        return Objects.equals(iduserRoutine, that.iduserRoutine) && Objects.equals(trainerId, that.trainerId) && Objects.equals(routineIdroutine, that.routineIdroutine) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduserRoutine, trainerId, routineIdroutine, userId);
    }

    public UserEntity getUserByTrainerId() {
        return userByTrainerId;
    }

    public void setUserByTrainerId(UserEntity userByTrainerId) {
        this.userByTrainerId = userByTrainerId;
    }

    public RoutineEntity getRoutineByRoutineIdroutine() {
        return routineByRoutineIdroutine;
    }

    public void setRoutineByRoutineIdroutine(RoutineEntity routineByRoutineIdroutine) {
        this.routineByRoutineIdroutine = routineByRoutineIdroutine;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
