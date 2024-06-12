package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "type", schema = "gymtaw", catalog = "")
public class TypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idtype", nullable = false)
    private int idtype;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeEntity that = (TypeEntity) o;
        return idtype == that.idtype && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtype, name);
    }
}
