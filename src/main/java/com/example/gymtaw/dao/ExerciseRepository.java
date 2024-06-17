package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exerciseEntity.id where es.sessionEntity.id = :idSesion order by es.id.order")
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);

}
