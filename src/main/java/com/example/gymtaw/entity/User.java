package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "dni", nullable = false, length = 45)
    private String dni;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false, length = 45)
    private String gender;


    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "user")
    private Set<ClientExercise> clientExercises = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user")
    private Rol Rol;

    @OneToMany(mappedBy = "idEntrenador")
    private Set<TrainerClient> trainerClients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<TrainerClient> clientsTrainer = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trainer")
    private Set<TrainerRoutine> trainerRoutines = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TrainerRoutine> userRoutines = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Valoracion> valoracions = new LinkedHashSet<>();

}