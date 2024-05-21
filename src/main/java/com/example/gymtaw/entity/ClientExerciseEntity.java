package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "client_exercise", schema = "gymtaw", catalog = "")
public class ClientExerciseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idclient_exercise", nullable = false)
    private int idclientExercise;
    @Basic
    @Column(name = "reps", nullable = true, length = 45)
    private String reps;
    @Basic
    @Column(name = "sets", nullable = true, length = 45)
    private String sets;
    @Basic
    @Column(name = "weight", nullable = true, length = 45)
    private String weight;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "exercise_id", nullable = false)
    private int exerciseId;
    @Basic
    @Column(name = "calories", nullable = true, precision = 0)
    private Double calories;
    @Basic
    @Column(name = "distance", nullable = true, precision = 0)
    private Double distance;

    public int getIdclientExercise() {
        return idclientExercise;
    }

    public void setIdclientExercise(int idclientExercise) {
        this.idclientExercise = idclientExercise;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientExerciseEntity that = (ClientExerciseEntity) o;
        return idclientExercise == that.idclientExercise && userId == that.userId && exerciseId == that.exerciseId && Objects.equals(reps, that.reps) && Objects.equals(sets, that.sets) && Objects.equals(weight, that.weight) && Objects.equals(calories, that.calories) && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idclientExercise, reps, sets, weight, userId, exerciseId, calories, distance);
    }
}
