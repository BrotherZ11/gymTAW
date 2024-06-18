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
public class RoutineEntity {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idclient")
    private UserEntity idclient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtrainer", nullable = false)
    private UserEntity idtrainer;

    @OneToMany(mappedBy = "routine")
    private Set<RoutineHasSessionEntity> routineHasSessions = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "type_has_routine",
            joinColumns = @JoinColumn(name = "routine_idroutine"),
            inverseJoinColumns = @JoinColumn(name = "type_idtype"))
    private Set<TypeEntity> types = new LinkedHashSet<>();

}