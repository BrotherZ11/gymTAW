package com.example.gymtaw.entity;
//Marta Granado Rodríguez (toDTO())
import com.example.gymtaw.dto.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "valoracion")
public class ValoracionEntity  implements DTO<Valoracion> {
    @EmbeddedId
    private ValoracionEntityId id;

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
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @Column(name = "review", length = 5000)
    private String review;

    @Column(name = "stars")
    private Integer stars;

    @ColumnDefault("0")
    @Column(name = "done", nullable = false)
    private Byte done;

    public Valoracion toDTO() {
        Valoracion valoracion = new Valoracion();
        valoracion.setId(this.id.toDTO());
        valoracion.setUser(this.user.toDTO());
        valoracion.setTrainer(this.trainer.toDTO());
        valoracion.setExercise(this.exercise.toDTO());
        valoracion.setReview(this.review);
        valoracion.setStars(this.stars);
        valoracion.setDone(this.done);
        return valoracion;
    }
}