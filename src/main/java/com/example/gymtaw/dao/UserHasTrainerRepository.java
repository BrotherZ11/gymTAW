package com.example.gymtaw.dao;

import com.example.gymtaw.entity.UserHasTrainerEntity;
import com.example.gymtaw.entity.UserHasTrainerEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasTrainerRepository extends JpaRepository<UserHasTrainerEntity, UserHasTrainerEntityId> {
}
