package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user", schema = "gymtaw", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "email", nullable = false, length = 250)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 250)
    private String password;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;
    @Basic
    @Column(name = "dni", nullable = false, length = 45)
    private String dni;
    @Basic
    @Column(name = "phone", nullable = false, length = 45)
    private String phone;
    @Basic
    @Column(name = "age", nullable = false)
    private int age;
    @Basic
    @Column(name = "gender", nullable = false, length = 45)
    private String gender;
    @Basic
    @Column(name = "id_rol", nullable = false)
    private int idRol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && age == that.age && idRol == that.idRol && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(dni, that.dni) && Objects.equals(phone, that.phone) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, dni, phone, age, gender, idRol);
    }
}
