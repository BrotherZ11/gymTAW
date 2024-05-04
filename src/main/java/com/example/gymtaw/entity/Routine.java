package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "routine")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroutine", nullable = false)
    private Integer id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "date")
    private LocalDate date;

}