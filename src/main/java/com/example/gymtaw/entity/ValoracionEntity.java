package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "valoracion", schema = "gymtaw", catalog = "")
@IdClass(ValoracionEntityPK.class)
public class ValoracionEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Id
    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;
    @Basic
    @Column(name = "review", nullable = true, length = 5000)
    private String review;
    @Basic
    @Column(name = "stars", nullable = true)
    private Integer stars;
    @Basic
    @Column(name = "done", nullable = false)
    private Byte done;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Byte getDone() {
        return done;
    }

    public void setDone(Byte done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValoracionEntity that = (ValoracionEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(exerciseId, that.exerciseId) && Objects.equals(review, that.review) && Objects.equals(stars, that.stars) && Objects.equals(done, that.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, exerciseId, review, stars, done);
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
