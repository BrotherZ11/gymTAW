package com.example.gymtaw.dto;
//Marta Granado Rodríguez 100%
import lombok.Data;

@Data
public class Valoracion
{
    private ValoracionId id;
    private User user;
    private Exercise exercise;
    private String review;
    private Integer stars;
    private Byte done;
}
