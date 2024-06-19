package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.ExerciseHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseHasSessionRepository extends JpaRepository<ExerciseHasSessionEntity, Integer> {
    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion")
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);

    @Query(value = "SELECT e FROM ExerciseHasSessionEntity e where e.session.id = :id")
    public List<ExerciseHasSessionEntity> getExercisesHasSessionByIdSession(@Param("id") Integer id);
}
