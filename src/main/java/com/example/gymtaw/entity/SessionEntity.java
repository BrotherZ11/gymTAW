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

    @OneToMany(mappedBy = "sessionEntity")
    private Set<ExerciseHasSessionEntity> exerciseHasSessions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sessionEntity")
    private Set<RoutineHasSessionEntity> routineHasSessions = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "type_has_session",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "type_idtype"))
    private Set<TypeEntity> types = new LinkedHashSet<>();

}