package com.example.gymtaw.entity;
//Marta Granado Rodríguez (toDTO())
import com.example.gymtaw.dto.ClientExercise;
import com.example.gymtaw.dto.DTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "client_exercise")
public class ClientExerciseEntity implements DTO<ClientExercise> {
    @EmbeddedId
    private ClientExerciseEntityId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @MapsId("trainerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "trainer_id", nullable = false)
    private UserEntity trainer;

    @MapsId("exerciseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @Column(name = "reps", length = 45)
    private String reps;

    @Column(name = "sets", length = 45)
    private String sets;

    @Column(name = "weight", length = 45)
    private String weight;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "distance")
    private Double distance;

    public ClientExercise toDTO() {
        ClientExercise clientExercise = new ClientExercise();
        clientExercise.setId(this.id.toDTO());
        clientExercise.setUser(this.user.toDTO());
        clientExercise.setTrainer(this.trainer.toDTO());
        clientExercise.setExercise(this.exercise.toDTO());
        clientExercise.setReps(this.reps);
        clientExercise.setSets(this.sets);
        clientExercise.setWeight(this.weight);
        clientExercise.setCalories(this.calories);
        clientExercise.setDistance(this.distance);
        return clientExercise;
    }

}