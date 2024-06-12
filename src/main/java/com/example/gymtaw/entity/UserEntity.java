package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "gymtaw", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
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
    private Integer age;
    @Basic
    @Column(name = "gender", nullable = false, length = 45)
    private String gender;
    @Basic
    @Column(name = "id_rol", nullable = false, insertable=false, updatable=false)
    private Integer idRol;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<ClientExerciseEntity> clientExercisesById;
    @OneToMany(mappedBy = "userByIdclient")
    private Collection<RoutineEntity> routinesById;
    @OneToMany(mappedBy = "userByIdtrainer")
    private Collection<RoutineEntity> routinesById_0;
    @OneToMany(mappedBy = "userByIdtrainer")
    private Collection<SessionEntity> sessionsById;
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false)
    private RolEntity rolByIdRol;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserHasTrainerEntity> userHasTrainersById;
    @OneToMany(mappedBy = "userByTrainerId")
    private Collection<UserHasTrainerEntity> userHasTrainersById_0;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<ValoracionEntity> valoracionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(dni, that.dni) && Objects.equals(phone, that.phone) && Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(idRol, that.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, dni, phone, age, gender, idRol);
    }

    public Collection<ClientExerciseEntity> getClientExercisesById() {
        return clientExercisesById;
    }

    public void setClientExercisesById(Collection<ClientExerciseEntity> clientExercisesById) {
        this.clientExercisesById = clientExercisesById;
    }

    public Collection<RoutineEntity> getRoutinesById() {
        return routinesById;
    }

    public void setRoutinesById(Collection<RoutineEntity> routinesById) {
        this.routinesById = routinesById;
    }

    public Collection<RoutineEntity> getRoutinesById_0() {
        return routinesById_0;
    }

    public void setRoutinesById_0(Collection<RoutineEntity> routinesById_0) {
        this.routinesById_0 = routinesById_0;
    }

    public Collection<SessionEntity> getSessionsById() {
        return sessionsById;
    }

    public void setSessionsById(Collection<SessionEntity> sessionsById) {
        this.sessionsById = sessionsById;
    }

    public RolEntity getRolByIdRol() {
        return rolByIdRol;
    }

    public void setRolByIdRol(RolEntity rolByIdRol) {
        this.rolByIdRol = rolByIdRol;
    }

    public Collection<UserHasTrainerEntity> getUserHasTrainersById() {
        return userHasTrainersById;
    }

    public void setUserHasTrainersById(Collection<UserHasTrainerEntity> userHasTrainersById) {
        this.userHasTrainersById = userHasTrainersById;
    }

    public Collection<UserHasTrainerEntity> getUserHasTrainersById_0() {
        return userHasTrainersById_0;
    }

    public void setUserHasTrainersById_0(Collection<UserHasTrainerEntity> userHasTrainersById_0) {
        this.userHasTrainersById_0 = userHasTrainersById_0;
    }

    public Collection<ValoracionEntity> getValoracionsById() {
        return valoracionsById;
    }

    public void setValoracionsById(Collection<ValoracionEntity> valoracionsById) {
        this.valoracionsById = valoracionsById;
    }
}
