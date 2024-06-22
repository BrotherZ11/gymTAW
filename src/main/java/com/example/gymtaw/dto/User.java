package com.example.gymtaw.dto;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private Integer id = -1;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String dni;
    private String phone;
    private Integer age;
    private String gender;
    private Rol idRol;
    private Set<Integer> userExercises;
    private Set<Integer> clientRoutines;
    private Set<Integer> trainerRoutines;
    private Set<Integer> sessions;
}
