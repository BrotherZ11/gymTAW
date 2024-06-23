package com.example.gymtaw.dto;
//Gonzalo Muñoz Rubio 100%
import lombok.Data;

import java.util.Set;

@Data
public class Rol {
    private Integer id;
    private String type;
    private Set<Integer> users;
}
