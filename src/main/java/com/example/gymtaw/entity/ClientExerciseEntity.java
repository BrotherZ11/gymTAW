package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "client_exercise")
public class ClientExerciseEntity {
    @EmbeddedId
    private ClientExerciseEntityId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @MapsId("exerciseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @Column(name = "reps", length = 45)
    private String reps;

    @Column(name = "sets", length = 45)
    private String sets;

    @Column(name = "weight", length = 45)
    private String weight;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "distance")
    private Double distance;

}