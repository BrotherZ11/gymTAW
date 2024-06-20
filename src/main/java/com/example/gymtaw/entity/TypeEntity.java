package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "type")
public class TypeEntity {
    @Id
    @Column(name = "idtype", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

}