package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "exercise", schema = "gymtaw", catalog = "")
public class ExerciseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;
    @Basic
    @Column(name = "video", nullable = false, length = 250)
    private String video;
    @Basic
    @Column(name = "type_idtype", nullable = false, insertable=false, updatable=false)
    private Integer typeIdtype;
    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ClientExerciseEntity> clientExercisesById;
    @ManyToOne
    @JoinColumn(name = "type_idtype", referencedColumnName = "idtype", nullable = false)
    private TypeEntity typeByTypeIdtype;
    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ExerciseHasSessionEntity> exerciseHasSessionsById;
    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ValoracionEntity> valoracionsById;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getTypeIdtype() {
        return typeIdtype;
    }

    public void setTypeIdtype(Integer typeIdtype) {
        this.typeIdtype = typeIdtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseEntity that = (ExerciseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(video, that.video) && Objects.equals(typeIdtype, that.typeIdtype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, video, typeIdtype);
    }

    public Collection<ClientExerciseEntity> getClientExercisesById() {
        return clientExercisesById;
    }

    public void setClientExercisesById(Collection<ClientExerciseEntity> clientExercisesById) {
        this.clientExercisesById = clientExercisesById;
    }

    public TypeEntity getTypeByTypeIdtype() {
        return typeByTypeIdtype;
    }

    public void setTypeByTypeIdtype(TypeEntity typeByTypeIdtype) {
        this.typeByTypeIdtype = typeByTypeIdtype;
    }

    public Collection<ExerciseHasSessionEntity> getExerciseHasSessionsById() {
        return exerciseHasSessionsById;
    }

    public void setExerciseHasSessionsById(Collection<ExerciseHasSessionEntity> exerciseHasSessionsById) {
        this.exerciseHasSessionsById = exerciseHasSessionsById;
    }

    public Collection<ValoracionEntity> getValoracionsById() {
        return valoracionsById;
    }

    public void setValoracionsById(Collection<ValoracionEntity> valoracionsById) {
        this.valoracionsById = valoracionsById;
    }
}
