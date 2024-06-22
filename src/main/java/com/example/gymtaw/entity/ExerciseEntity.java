package com.example.gymtaw.entity;

import com.example.gymtaw.dto.Exercise;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "exercise")
public class ExerciseEntity {
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

        List<Integer> typeIdtype = new ArrayList<>();
        for(TypeEntity type : this.typeIdtype){
            typeIdtype.add(type.getId());
        }
        exercise.setTypeIdtype(typeIdtype);


        return exercise;
    }
}