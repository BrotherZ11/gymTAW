package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.UserHasTrainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "user_has_trainer")
public class UserHasTrainerEntity implements DTO<UserHasTrainer> {
    @EmbeddedId
    private UserHasTrainerEntityId id;

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

    public UserHasTrainer toDTO() {
        UserHasTrainer userHasTrainer = new UserHasTrainer();
        userHasTrainer.setId(this.id.toDTO());
        userHasTrainer.setUser(this.user.toDTO());
        userHasTrainer.setTrainer(this.trainer.toDTO());
        return userHasTrainer;
    }
}