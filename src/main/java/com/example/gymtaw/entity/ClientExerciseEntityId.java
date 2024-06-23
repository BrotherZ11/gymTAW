package com.example.gymtaw.entity;
//Marta Granado Rodríguez
import com.example.gymtaw.dto.ClientExerciseId;
import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.ExerciseHasSessionId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ClientExerciseEntityId implements Serializable, DTO<ClientExerciseId> {
    private static final long serialVersionUID = -3932182480453692957L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "exercise_id", nullable = false)
    private Integer exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClientExerciseEntityId entity = (ClientExerciseEntityId) o;
        return Objects.equals(this.exerciseId, entity.exerciseId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, userId);
    }

    public ClientExerciseId toDTO() {
        ClientExerciseId clientExerciseEntityId = new ClientExerciseId();
        clientExerciseEntityId.setExerciseId(this.exerciseId);
        clientExerciseEntityId.setUserId(this.userId);
        return clientExerciseEntityId;
    }
}