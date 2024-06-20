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
@Table(name = "rol")
public class RolEntity {
    @Id
    @Column(name = "id_rol", nullable = false)
    private Integer id;

    @Column(name = "type", length = 20)
    private String type;

}