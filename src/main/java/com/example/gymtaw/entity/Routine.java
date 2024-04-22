package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "routine")
public class Routine {
    @Id
    @Column(name = "idroutine", nullable = false)
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "date")
    private LocalDate date;

}