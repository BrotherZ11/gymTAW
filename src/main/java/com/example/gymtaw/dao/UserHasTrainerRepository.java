package com.example.gymtaw.dao;

import com.example.gymtaw.entity.UserHasTrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasTrainerRepository extends JpaRepository<UserHasTrainerEntity, Integer> {
}
