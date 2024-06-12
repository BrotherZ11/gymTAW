package com.example.gymtaw.dao;

import com.example.gymtaw.entity.Rol;
import com.example.gymtaw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query("Select u from Rol r join User u on r.id = u.id where r.type = :rol")
    public List<User> findByRol(@Param("rol") String rol);
}
