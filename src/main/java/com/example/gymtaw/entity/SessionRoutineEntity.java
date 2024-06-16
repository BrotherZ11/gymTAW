package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "session_routine")
public class SessionRoutineEntity {
    @Id
    @Column(name = "idsession_routine", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity sessionEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routine_idroutine", nullable = false)
    private RoutineEntity routineEntityIdroutine;

    @Column(name = "day")
    private Integer day;

}