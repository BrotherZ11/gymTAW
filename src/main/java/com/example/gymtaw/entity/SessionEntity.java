package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "session", schema = "gymtaw", catalog = "")
public class SessionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "idtrainer", nullable = false, insertable=false, updatable=false)
    private Integer idtrainer;
    @OneToMany(mappedBy = "sessionBySessionId")
    private Collection<ExerciseHasSessionEntity> exerciseHasSessionsById;
    @OneToMany(mappedBy = "sessionBySessionId")
    private Collection<RoutineHasSessionEntity> routineHasSessionsById;
    @ManyToOne
    @JoinColumn(name = "idtrainer", referencedColumnName = "id", nullable = false)
    private UserEntity userByIdtrainer;
    @OneToMany(mappedBy = "sessionBySessionId")
    private Collection<TypeHasSessionEntity> typeHasSessionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        SessionEntity that = (SessionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(idtrainer, that.idtrainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idtrainer);
    }

    public Collection<ExerciseHasSessionEntity> getExerciseHasSessionsById() {
        return exerciseHasSessionsById;
    }

    public void setExerciseHasSessionsById(Collection<ExerciseHasSessionEntity> exerciseHasSessionsById) {
        this.exerciseHasSessionsById = exerciseHasSessionsById;
    }

    public Collection<RoutineHasSessionEntity> getRoutineHasSessionsById() {
        return routineHasSessionsById;
    }

    public void setRoutineHasSessionsById(Collection<RoutineHasSessionEntity> routineHasSessionsById) {
        this.routineHasSessionsById = routineHasSessionsById;
    }

    public UserEntity getUserByIdtrainer() {
        return userByIdtrainer;
    }

    public void setUserByIdtrainer(UserEntity userByIdtrainer) {
        this.userByIdtrainer = userByIdtrainer;
    }

    public Collection<TypeHasSessionEntity> getTypeHasSessionsById() {
        return typeHasSessionsById;
    }

    public void setTypeHasSessionsById(Collection<TypeHasSessionEntity> typeHasSessionsById) {
        this.typeHasSessionsById = typeHasSessionsById;
    }
}
