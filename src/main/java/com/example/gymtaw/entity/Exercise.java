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
public class Exercise {
    @Id
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
    private Type typeIdtype;

    @OneToMany(mappedBy = "exercise")
    private Set<ClientExercise> clientExercises = new LinkedHashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseSession> exerciseSessions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<Valoracion> valoracions = new LinkedHashSet<>();

}