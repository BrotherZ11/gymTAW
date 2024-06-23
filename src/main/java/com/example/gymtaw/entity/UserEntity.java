package com.example.gymtaw.entity;
//Gonzalo Muñoz Rubio
import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity implements DTO<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "password", nullable = false, length = 250)
    private Integer password;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity idRol;

    @OneToMany(mappedBy = "user")
    private Set<ClientExerciseEntity> clientExercises = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idclient")
    private Set<RoutineEntity> routinesClient = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idtrainer")
    private Set<RoutineEntity> routinesTrainer = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idtrainer")
    private Set<SessionEntity> sessions = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_has_trainer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    private Set<UserEntity> trainers = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_has_trainer",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> clients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ValoracionEntity> valoracions = new LinkedHashSet<>();

    public User toDTO() {
        User user = new User();
        user.setId(this.id);
        user.setEmail(this.email);
        user.setPasswordHash(this.password);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setDni(this.dni);
        user.setPhone(this.phone);
        user.setAge(this.age);
        user.setGender(this.gender);
        user.setRol(this.idRol.toDTO());

        Set<Integer> exercises = new LinkedHashSet<>();
        this.clientExercises.forEach(clientExerciseEntity -> exercises.add(clientExerciseEntity.getExercise().getId()));
        user.setUserExercises(exercises);

        Set<Integer> clientRout = new LinkedHashSet<>();
        this.routinesClient.forEach(routineEntity -> clientRout.add(routineEntity.getId()));
        user.setClientRoutines(clientRout);

        Set<Integer> trainerRout = new LinkedHashSet<>();
        this.routinesTrainer.forEach(routineEntity -> trainerRout.add(routineEntity.getId()));
        user.setTrainerRoutines(trainerRout);

        Set<Integer> sessions = new LinkedHashSet<>();
        this.sessions.forEach(sessionEntity -> sessions.add(sessionEntity.getId()));
        user.setSessions(sessions);

        return user;
    }
}
