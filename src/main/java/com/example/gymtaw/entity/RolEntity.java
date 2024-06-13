package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Collection;
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
    private Integer idRol;
    @OneToMany(mappedBy = "rolByIdRol")
    private Collection<UserEntity> usersByIdRol;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEntity rolEntity = (RolEntity) o;
        return Objects.equals(type, rolEntity.type) && Objects.equals(idRol, rolEntity.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, idRol);
    }

    public Collection<UserEntity> getUsersByIdRol() {
        return usersByIdRol;
    }

    public void setUsersByIdRol(Collection<UserEntity> usersByIdRol) {
        this.usersByIdRol = usersByIdRol;
    }
}
