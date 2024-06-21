package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseHasSessionRepository extends JpaRepository<ExerciseHasSessionEntity,Integer> {
}
