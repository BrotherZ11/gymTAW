package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion")
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);


    @Query(value = "select e from ExerciseEntity e where e.id = :idEjercicio")
    ExerciseEntity getExercisesByIdEjercicio(@Param("idEjercicio") Integer idEjercicio);
}
