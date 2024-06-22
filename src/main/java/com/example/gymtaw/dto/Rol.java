package com.example.gymtaw.dto;

import lombok.Data;

import java.util.Set;

@Data
public class Rol {
    private Integer id;
    private String type;
    private Set<Integer> users;
}
