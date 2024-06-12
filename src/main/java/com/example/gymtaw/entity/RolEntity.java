package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rol", schema = "gymtaw", catalog = "")
public class RolEntity {
    @Basic
    @Column(name = "type", nullable = true, length = 20)
    private String type;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rol", nullable = false)
    private int idRol;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEntity rolEntity = (RolEntity) o;
        return idRol == rolEntity.idRol && Objects.equals(type, rolEntity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, idRol);
    }
}
