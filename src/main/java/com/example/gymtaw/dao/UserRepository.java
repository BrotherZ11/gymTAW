package com.example.gymtaw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :user and u.password = :pwd")
    User autenticacion (@Param("user") String user, @Param("pwd")String pwd);

}
