package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 45)
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

}