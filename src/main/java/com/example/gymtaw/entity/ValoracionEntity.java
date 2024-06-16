package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "valoracion")
public class ValoracionEntity {
    @EmbeddedId
    private ValoracionEntityId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @MapsId("exerciseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exerciseEntity;

    @Column(name = "review", length = 5000)
    private String review;

    @Column(name = "stars")
    private Integer stars;

    @ColumnDefault("0")
    @Column(name = "done", nullable = false)
    private Byte done;

}