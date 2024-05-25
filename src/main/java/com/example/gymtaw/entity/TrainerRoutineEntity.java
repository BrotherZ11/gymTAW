package com.example.gymtaw.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "trainer_routine", schema = "gymtaw")
public class TrainerRoutineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iduser_routine", nullable = false)
    private int iduserRoutine;

    @Basic
    @Column(name = "trainer_id", nullable = false)  // Update the column name
    private int trainerId;

    @Basic
    @Column(name = "routine_idroutine", nullable = false)
    private int routineIdroutine;

    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;

    public int getIduserRoutine() {
        return iduserRoutine;
    }

    public void setIduserRoutine(int iduserRoutine) {
        this.iduserRoutine = iduserRoutine;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getRoutineIdroutine() {
        return routineIdroutine;
    }

    public void setRoutineIdroutine(int routineIdroutine) {
        this.routineIdroutine = routineIdroutine;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerRoutineEntity that = (TrainerRoutineEntity) o;
        return iduserRoutine == that.iduserRoutine && trainerId == that.trainerId && routineIdroutine == that.routineIdroutine && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduserRoutine, trainerId, routineIdroutine, userId);
    }
}
