package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.Exercise;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "exercise")
public class ExerciseEntity implements DTO<Exercise> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "video", nullable = false, length = 250)
    private String video;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_idtype", nullable = false)
    private TypeEntity typeIdtype;

    @OneToMany(mappedBy = "exercise")
    private List<ClientExerciseEntity> clientExercises = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseHasSessionEntity> exerciseHasSessions = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    private List<ValoracionEntity> valoracions = new ArrayList<>();

    public Exercise toDTO() {
        Exercise exercise = new Exercise();
        exercise.setId(id);
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setVideo(video);
        exercise.setTypeIdtype(this.typeIdtype.toDTO());

        List<ClientExerciseEntityId> clientExercises = new ArrayList<>();
        this.clientExercises.forEach(clientExerciseEntity -> clientExercises.add(clientExerciseEntity.getId()));
        exercise.setClientExercises(clientExercises);

        List<ExerciseHasSessionEntityId> exerciseHasSessions = new ArrayList<>();
        this.exerciseHasSessions.forEach(exerciseHasSessionEntity -> {exerciseHasSessions.add(exerciseHasSessionEntity.getId());});
        exercise.setExerciseHasSessions(exerciseHasSessions);

        List<ValoracionEntityId> valoracions = new ArrayList<>();
        this.valoracions.forEach(ValoracionEntity -> {valoracions.add(ValoracionEntity.getId());});
        exercise.setValoracions(valoracions);

        return exercise;
    }
}