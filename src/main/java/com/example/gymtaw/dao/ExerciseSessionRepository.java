package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.ExerciseHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseSessionRepository extends JpaRepository<ExerciseHasSessionEntity, Integer> {

    @Query(value = "SELECT * FROM exercise join exercise_session on exercise.id = exercise_session.exercise_id where exercise_session.session_id = :id", nativeQuery = true)
    public List<ExerciseEntity> getExercisesByIdSession(@Param("id") Integer id);

}
