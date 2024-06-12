package com.example.gymtaw.dao;

import com.example.gymtaw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u where u.email = :user and u.password = :pwd")
    UserEntity autenticacion (@Param("user") String user, @Param("pwd")String pwd);

    @Query("select u from UserEntity u where u.idRol = :rol")
    List<UserEntity> findUserEntitiesByIdRol(@Param("rol") int rol);
}
