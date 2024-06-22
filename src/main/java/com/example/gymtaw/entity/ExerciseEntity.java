package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.Routine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "exercise")
public class ExerciseEntity  implements DTO<Exercise> {
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
    private Set<ClientExerciseEntity> clientExercises = new HashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseHasSessionEntity> exerciseHasSessions = new HashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<ValoracionEntity> valoracions = new HashSet<>();

    public Exercise toDTO() {
        Exercise exercise = new Exercise();
        exercise.setId(id);
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setVideo(video);
        exercise.setTypeIdtype(typeIdtype.toDTO());

        Set<ClientExerciseEntityId> clientExercises = new LinkedHashSet<>();
        this.clientExercises.forEach(clientExercise -> {clientExercise.getId();});
        exercise.setClientExercises(clientExercises);

        Set<Integer> exerciseHasSessions = new LinkedHashSet<>();
        this.exerciseHasSessions.forEach(exerciseHasSessionEntity -> {exerciseHasSessions.add(exerciseHasSessionEntity.getSession().getId());});
        exercise.setExerciseHasSessions(exerciseHasSessions);

        Set<ValoracionEntityId> valoracions = new LinkedHashSet<>();
        this.valoracions.forEach(valoracionEntityId -> {valoracionEntityId.getId();});
        exercise.setValoracions(valoracions);

        return exercise;
    }
}