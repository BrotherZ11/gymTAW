package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.Routine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity
@Table(name = "routine")
public class RoutineEntity implements DTO<Routine> {
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
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "idclient")
    private UserEntity idclient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idtrainer", nullable = false)
    private UserEntity idtrainer;

    @OneToMany(mappedBy = "routine")
    private Set<RoutineHasSessionEntity> routineHasSessions = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "type_has_routine",
            joinColumns = @JoinColumn(name = "routine_idroutine"),
            inverseJoinColumns = @JoinColumn(name = "type_idtype"))
    private Set<TypeEntity> types = new LinkedHashSet<>();

    public Routine toDTO() {
        Routine dto = new Routine();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());
        dto.setDate(this.getDate());
        dto.setTrainerId(this.getIdtrainer().getId());
        Set<Integer> sessionIds = new LinkedHashSet<>();
        for(RoutineHasSessionEntity sesion: this.routineHasSessions){
            sessionIds.add(sesion.getSession().getId());
        }
        dto.setSessions(sessionIds);

        Set<Integer> typesIds = new LinkedHashSet<>();
        for(TypeEntity type: this.types){
            typesIds.add(type.getId());
        }
        dto.setTypes(typesIds);

        return dto;
    }
}