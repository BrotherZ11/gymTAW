package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ClientExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientExerciseRepository extends JpaRepository<ClientExerciseEntity, Integer> {

    @Query("select ce from ClientExerciseEntity ce where ce.exercise.id = :idEjercicio")
    List<ClientExerciseEntity> getClientExercisesByIdEjercicio(@Param("idEjercicio") Integer idEjercicio);

    @Query("select ce.id from ClientExerciseEntity ce where ce.exercise.id = :idEjercicio")
    Integer idClienteByExerciseId(@Param("idEjercicio") Integer idEjercicio);
}
