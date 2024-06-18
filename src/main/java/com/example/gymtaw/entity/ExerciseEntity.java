package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "exercise")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "video", nullable = false, length = 250)
    private String video;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_idtype", nullable = false)
    private TypeEntity typeIdtype;

    @OneToMany(mappedBy = "exercise")
    private Set<ClientExerciseEntity> clientExercises = new LinkedHashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseHasSessionEntity> exerciseHasSessions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<ValoracionEntity> valoracions = new LinkedHashSet<>();

}