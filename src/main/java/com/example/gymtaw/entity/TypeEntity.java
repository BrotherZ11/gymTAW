package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "type", schema = "gymtaw", catalog = "")
public class TypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idtype", nullable = false)
    private Integer idtype;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @OneToMany(mappedBy = "typeByTypeIdtype")
    private Collection<ExerciseEntity> exercisesByIdtype;
    @OneToMany(mappedBy = "typeByTypeIdtype")
    private Collection<TypeHasRoutineEntity> typeHasRoutinesByIdtype;
    @OneToMany(mappedBy = "typeByTypeIdtype")
    private Collection<TypeHasSessionEntity> typeHasSessionsByIdtype;

    public Integer getIdtype() {
        return idtype;
    }

    public void setIdtype(Integer idtype) {
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
        return Objects.equals(idtype, that.idtype) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtype, name);
    }

    public Collection<ExerciseEntity> getExercisesByIdtype() {
        return exercisesByIdtype;
    }

    public void setExercisesByIdtype(Collection<ExerciseEntity> exercisesByIdtype) {
        this.exercisesByIdtype = exercisesByIdtype;
    }

    public Collection<TypeHasRoutineEntity> getTypeHasRoutinesByIdtype() {
        return typeHasRoutinesByIdtype;
    }

    public void setTypeHasRoutinesByIdtype(Collection<TypeHasRoutineEntity> typeHasRoutinesByIdtype) {
        this.typeHasRoutinesByIdtype = typeHasRoutinesByIdtype;
    }

    public Collection<TypeHasSessionEntity> getTypeHasSessionsByIdtype() {
        return typeHasSessionsByIdtype;
    }

    public void setTypeHasSessionsByIdtype(Collection<TypeHasSessionEntity> typeHasSessionsByIdtype) {
        this.typeHasSessionsByIdtype = typeHasSessionsByIdtype;
    }
}
