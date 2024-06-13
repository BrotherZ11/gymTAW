package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "type_has_routine", schema = "gymtaw", catalog = "")
@IdClass(TypeHasRoutineEntityPK.class)
public class TypeHasRoutineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_idtype", nullable = false)
    private Integer typeIdtype;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routine_idroutine", nullable = false)
    private Integer routineIdroutine;
    @ManyToOne
    @JoinColumn(name = "type_idtype", referencedColumnName = "idtype", nullable = false)
    private TypeEntity typeByTypeIdtype;
    @ManyToOne
    @JoinColumn(name = "routine_idroutine", referencedColumnName = "idroutine", nullable = false)
    private RoutineEntity routineByRoutineIdroutine;

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
        TypeHasRoutineEntity that = (TypeHasRoutineEntity) o;
        return Objects.equals(typeIdtype, that.typeIdtype) && Objects.equals(routineIdroutine, that.routineIdroutine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeIdtype, routineIdroutine);
    }

    public TypeEntity getTypeByTypeIdtype() {
        return typeByTypeIdtype;
    }

    public void setTypeByTypeIdtype(TypeEntity typeByTypeIdtype) {
        this.typeByTypeIdtype = typeByTypeIdtype;
    }

    public RoutineEntity getRoutineByRoutineIdroutine() {
        return routineByRoutineIdroutine;
    }

    public void setRoutineByRoutineIdroutine(RoutineEntity routineByRoutineIdroutine) {
        this.routineByRoutineIdroutine = routineByRoutineIdroutine;
    }
}
