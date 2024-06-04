package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "trainer_client", schema = "gymtaw", catalog = "")
public class TrainerClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser_User", nullable = false)
    private Integer idUserUser;
    @Basic
    @Column(name = "idEntrenador", nullable = false)
    private Integer idEntrenador;
    @Basic
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;
    @ManyToOne
    @JoinColumn(name = "idEntrenador", referencedColumnName = "id", nullable = false)
    private UserEntity userByIdEntrenador;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "id", nullable = false)
    private UserEntity userByIdCliente;

    public Integer getIdUserUser() {
        return idUserUser;
    }

    public void setIdUserUser(Integer idUserUser) {
        this.idUserUser = idUserUser;
    }

    public Integer getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Integer idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerClientEntity that = (TrainerClientEntity) o;
        return Objects.equals(idUserUser, that.idUserUser) && Objects.equals(idEntrenador, that.idEntrenador) && Objects.equals(idCliente, that.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserUser, idEntrenador, idCliente);
    }

    public UserEntity getUserByIdEntrenador() {
        return userByIdEntrenador;
    }

    public void setUserByIdEntrenador(UserEntity userByIdEntrenador) {
        this.userByIdEntrenador = userByIdEntrenador;
    }

    public UserEntity getUserByIdCliente() {
        return userByIdCliente;
    }

    public void setUserByIdCliente(UserEntity userByIdCliente) {
        this.userByIdCliente = userByIdCliente;
    }
}
