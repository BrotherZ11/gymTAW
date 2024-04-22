package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client_exercise")
public class ClientExercise {
    @Id
    @Column(name = "idclient_exercise", nullable = false)
    private Integer id;

    @Column(name = "reps", length = 45)
    private String reps;

    @Column(name = "sets", length = 45)
    private String sets;

    @Column(name = "weight", length = 45)
    private String weight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "distance")
    private Double distance;

}