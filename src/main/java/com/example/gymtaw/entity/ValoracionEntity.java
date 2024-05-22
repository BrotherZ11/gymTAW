package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "valoracion", schema = "gymtaw", catalog = "")
public class ValoracionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idValoracion", nullable = false)
    private int idValoracion;
    @Basic
    @Column(name = "review", nullable = true, length = 5000)
    private String review;
    @Basic
    @Column(name = "stars", nullable = true)
    private Integer stars;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "exercise_id", nullable = false)
    private int exerciseId;

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValoracionEntity that = (ValoracionEntity) o;
        return idValoracion == that.idValoracion && userId == that.userId && exerciseId == that.exerciseId && Objects.equals(review, that.review) && Objects.equals(stars, that.stars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idValoracion, review, stars, userId, exerciseId);
    }
}
