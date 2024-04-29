package com.example.gymtaw.dao;

import com.example.gymtaw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
