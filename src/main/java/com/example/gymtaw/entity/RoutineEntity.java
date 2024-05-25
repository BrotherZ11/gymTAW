package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "routine", schema = "gymtaw", catalog = "")
public class RoutineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idroutine", nullable = false)
    private int idroutine;
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
    @Column(name = "idTrainer", nullable = false)
    private int idTrainer;

    public int getIdroutine() {
        return idroutine;
    }

    public void setIdroutine(int idroutine) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineEntity that = (RoutineEntity) o;
        return idroutine == that.idroutine && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idroutine, name, description, date);
    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
        this.idTrainer = idTrainer;
    }
}
