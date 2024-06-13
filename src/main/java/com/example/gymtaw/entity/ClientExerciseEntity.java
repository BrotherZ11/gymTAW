package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "client_exercise", schema = "gymtaw", catalog = "")
@IdClass(ClientExerciseEntityPK.class)
public class ClientExerciseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;
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
    @Column(name = "calories", nullable = true, precision = 0)
    private Double calories;
    @Basic
    @Column(name = "distance", nullable = true, precision = 0)
    private Double distance;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = false)
    private ExerciseEntity exerciseByExerciseId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
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
        return Objects.equals(userId, that.userId) && Objects.equals(exerciseId, that.exerciseId) && Objects.equals(reps, that.reps) && Objects.equals(sets, that.sets) && Objects.equals(weight, that.weight) && Objects.equals(calories, that.calories) && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, exerciseId, reps, sets, weight, calories, distance);
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public ExerciseEntity getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(ExerciseEntity exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }
}
