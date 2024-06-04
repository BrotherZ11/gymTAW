package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "routine", schema = "gymtaw", catalog = "")
public class RoutineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idroutine", nullable = false)
    private Integer idroutine;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;
    @Basic
    @Column(name = "date", nullable = true)
    private Date date;
    @Basic
    @Column(name = "idclient", nullable = true)
    private Integer idclient;
    @Basic
    @Column(name = "idtrainer", nullable = false)
    private Integer idtrainer;
    @ManyToOne
    @JoinColumn(name = "idclient", referencedColumnName = "id")
    private UserEntity userByIdclient;
    @ManyToOne
    @JoinColumn(name = "idtrainer", referencedColumnName = "id", nullable = false)
    private UserEntity userByIdtrainer;
    @OneToMany(mappedBy = "routineByRoutineIdroutine")
    private Collection<RoutineHasSessionEntity> routineHasSessionsByIdroutine;
    @OneToMany(mappedBy = "routineByRoutineIdroutine")
    private Collection<SessionRoutineEntity> sessionRoutinesByIdroutine;
    @OneToMany(mappedBy = "routineByRoutineIdroutine")
    private Collection<TrainerRoutineEntity> trainerRoutinesByIdroutine;
    @OneToMany(mappedBy = "routineByRoutineIdroutine")
    private Collection<TypeHasRoutineEntity> typeHasRoutinesByIdroutine;

    public Integer getIdroutine() {
        return idroutine;
    }

    public void setIdroutine(Integer idroutine) {
        this.idroutine = idroutine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public Integer getIdtrainer() {
        return idtrainer;
    }

    public void setIdtrainer(Integer idtrainer) {
        this.idtrainer = idtrainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineEntity that = (RoutineEntity) o;
        return Objects.equals(idroutine, that.idroutine) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(idclient, that.idclient) && Objects.equals(idtrainer, that.idtrainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idroutine, name, description, date, idclient, idtrainer);
    }

    public UserEntity getUserByIdclient() {
        return userByIdclient;
    }

    public void setUserByIdclient(UserEntity userByIdclient) {
        this.userByIdclient = userByIdclient;
    }

    public UserEntity getUserByIdtrainer() {
        return userByIdtrainer;
    }

    public void setUserByIdtrainer(UserEntity userByIdtrainer) {
        this.userByIdtrainer = userByIdtrainer;
    }

    public Collection<RoutineHasSessionEntity> getRoutineHasSessionsByIdroutine() {
        return routineHasSessionsByIdroutine;
    }

    public void setRoutineHasSessionsByIdroutine(Collection<RoutineHasSessionEntity> routineHasSessionsByIdroutine) {
        this.routineHasSessionsByIdroutine = routineHasSessionsByIdroutine;
    }

    public Collection<SessionRoutineEntity> getSessionRoutinesByIdroutine() {
        return sessionRoutinesByIdroutine;
    }

    public void setSessionRoutinesByIdroutine(Collection<SessionRoutineEntity> sessionRoutinesByIdroutine) {
        this.sessionRoutinesByIdroutine = sessionRoutinesByIdroutine;
    }

    public Collection<TrainerRoutineEntity> getTrainerRoutinesByIdroutine() {
        return trainerRoutinesByIdroutine;
    }

    public void setTrainerRoutinesByIdroutine(Collection<TrainerRoutineEntity> trainerRoutinesByIdroutine) {
        this.trainerRoutinesByIdroutine = trainerRoutinesByIdroutine;
    }

    public Collection<TypeHasRoutineEntity> getTypeHasRoutinesByIdroutine() {
        return typeHasRoutinesByIdroutine;
    }

    public void setTypeHasRoutinesByIdroutine(Collection<TypeHasRoutineEntity> typeHasRoutinesByIdroutine) {
        this.typeHasRoutinesByIdroutine = typeHasRoutinesByIdroutine;
    }
}
