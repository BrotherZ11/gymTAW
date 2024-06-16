package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "type_has_routine")
public class TypeHasRoutineEntity {
    @EmbeddedId
    private TypeHasRoutineEntityId id;

    @MapsId("typeIdtype")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_idtype", nullable = false)
    private TypeEntity typeIdtype;

    @MapsId("routineIdroutine")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routine_idroutine", nullable = false)
    private RoutineEntity routineIdroutine;

}