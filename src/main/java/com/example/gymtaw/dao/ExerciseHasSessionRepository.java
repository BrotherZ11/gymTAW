package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseHasSessionEntity;
import com.example.gymtaw.entity.ExerciseHasSessionEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseHasSessionRepository extends JpaRepository<ExerciseHasSessionEntity, ExerciseHasSessionEntityId> {
}
