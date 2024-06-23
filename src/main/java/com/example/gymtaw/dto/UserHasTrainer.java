package com.example.gymtaw.dto;
//Gonzalo Muñoz Rubio 100%
import lombok.Data;

@Data
public class UserHasTrainer {
    private UserHasTrainerId id;
    private User user;
    private User trainer;
}
