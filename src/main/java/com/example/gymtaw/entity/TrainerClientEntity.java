package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "trainer_client", schema = "gymtaw", catalog = "")
public class TrainerClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser_User", nullable = false)
    private int idUserUser;
    @Basic
    @Column(name = "idEntrenador", nullable = false)
    private int idEntrenador;
    @Basic
    @Column(name = "idCliente", nullable = false)
    private int idCliente;

    public int getIdUserUser() {
        return idUserUser;
    }

    public void setIdUserUser(int idUserUser) {
        this.idUserUser = idUserUser;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerClientEntity that = (TrainerClientEntity) o;
        return idUserUser == that.idUserUser && idEntrenador == that.idEntrenador && idCliente == that.idCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserUser, idEntrenador, idCliente);
    }
}
