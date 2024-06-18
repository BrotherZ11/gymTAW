package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "session")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtrainer", nullable = false)
    private UserEntity idtrainer;

    @OneToMany(mappedBy = "session")
    private Set<ExerciseHasSessionEntity> exerciseHasSessions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "session")
    private Set<RoutineHasSessionEntity> routineHasSessions = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "session")
    private Set<TypeEntity> types = new LinkedHashSet<>();

}