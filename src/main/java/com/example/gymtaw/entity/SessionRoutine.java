package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "session_routine")
public class SessionRoutine {
    @Id
    @Column(name = "idsession_routine", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routine_idroutine", nullable = false)
    private Routine routineIdroutine;

    @Column(name = "day")
    private Integer day;

}