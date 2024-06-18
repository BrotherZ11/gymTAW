package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "idRol")
    private Set<UserEntity> users = new LinkedHashSet<>();

}