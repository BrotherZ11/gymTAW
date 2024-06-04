package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class TypeHasRoutineEntityPK implements Serializable {
    @Column(name = "type_idtype", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeIdtype;
    @Column(name = "routine_idroutine", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routineIdroutine;

    public Integer getTypeIdtype() {
        return typeIdtype;
    }

    public void setTypeIdtype(Integer typeIdtype) {
        this.typeIdtype = typeIdtype;
    }

    public Integer getRoutineIdroutine() {
        return routineIdroutine;
    }

    public void setRoutineIdroutine(Integer routineIdroutine) {
        this.routineIdroutine = routineIdroutine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeHasRoutineEntityPK that = (TypeHasRoutineEntityPK) o;
        return Objects.equals(typeIdtype, that.typeIdtype) && Objects.equals(routineIdroutine, that.routineIdroutine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeIdtype, routineIdroutine);
    }
}
