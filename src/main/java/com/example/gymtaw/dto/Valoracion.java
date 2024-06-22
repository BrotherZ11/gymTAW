package com.example.gymtaw.dto;

import com.example.gymtaw.entity.ValoracionEntityId;
import lombok.Data;

@Data
public class Valoracion {
    private ValoracionEntityId id;
    private User user;
    private Exercise exercise;
    private String review;
    private Integer stars;
    private Byte done;
}
