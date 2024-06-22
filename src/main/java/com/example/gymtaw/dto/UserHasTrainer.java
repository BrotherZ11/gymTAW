package com.example.gymtaw.dto;

import lombok.Data;

@Data
public class UserHasTrainer {
    private UserHasTrainerId id;
    private User user;
    private User trainer;
}
