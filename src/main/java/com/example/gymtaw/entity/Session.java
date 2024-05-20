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
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "session")
    private Set<ExerciseSession> exerciseSessions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "session")
    private Set<SessionRoutine> sessionRoutines = new LinkedHashSet<>();

}