package com.example.gymtaw.dao;

import com.example.gymtaw.entity.Rol;
import com.example.gymtaw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query("select u from User u, Rol r where u.id = r.id and r.type = :rol")
    public List<User> findUsersByRol(@Param("rol") String rol);
}
