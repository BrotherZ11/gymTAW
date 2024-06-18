package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    @Query(value = "SELECT * FROM exercise\n" +
            "join exercise_session\n" +
            "on exercise.id = exercise_session.exercise_id\n" +
            "where exercise_session.session_id = :idSesion", nativeQuery = true)
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);

}
