package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion order by es.id.order")
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion and exists (select ce from ClientExerciseEntity ce where ce.exercise = es.exercise)")
    public List<ExerciseEntity> getExercisesByIdSessionWithData(@Param("idSesion") Integer idSesion);

    @Query("select e from ExerciseEntity e join ValoracionEntity v on v.exercise.id = e.id where v.user.id = :idCliente order by e.id")
    public List<ExerciseEntity> getExerciseEntitiesByIdClienteAndHaveReview(Integer idCliente);

}
