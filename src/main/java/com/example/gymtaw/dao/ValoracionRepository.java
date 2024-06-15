package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.entity.ValoracionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ValoracionRepository extends JpaRepository<ValoracionEntity, ValoracionEntityPK> {
//    @Query(value= "select v from valoracion v where v.exercise_id = :exerciseId")
//    List<ValoracionEntity> findByExerciseId(Integer exerciseId);
//    List<ValoracionEntity> findByUserIdAndExerciseId(Integer userId, Integer exerciseId);
}
