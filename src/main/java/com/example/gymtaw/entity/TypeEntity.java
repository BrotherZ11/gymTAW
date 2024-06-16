package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtype", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @OneToMany(mappedBy = "typeIdtype")
    private Set<ExerciseEntity> exercises = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "typeIdtype")
    private Set<RoutineEntity> routines = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "typeIdtype")
    private Set<SessionEntity> sessions = new LinkedHashSet<>();

}