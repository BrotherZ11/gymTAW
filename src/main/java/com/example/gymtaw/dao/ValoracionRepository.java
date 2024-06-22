package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.entity.ValoracionEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValoracionRepository extends JpaRepository<ValoracionEntity, ValoracionEntityId> {
    @Query("SELECT v FROM ValoracionEntity v WHERE v.exercise.id = :exerciseId")
    ValoracionEntity getValoracionByExercise(@Param("exerciseId") Integer exerciseId);
}
