package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "routine")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroutine", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "routineIdroutine")
    private Set<SessionRoutine> sessionRoutines = new LinkedHashSet<>();

    @OneToMany(mappedBy = "routineIdroutine")
    private Set<TrainerRoutine> trainerRoutines = new LinkedHashSet<>();

}