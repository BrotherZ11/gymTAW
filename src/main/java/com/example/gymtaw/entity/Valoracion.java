package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "valoracion")
public class Valoracion {
    @Id
    @Column(name = "idValoracion", nullable = false)
    private Integer id;

    @Column(name = "review", length = 5000)
    private String review;

    @Column(name = "stars")
    private Integer stars;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

}