package com.example.gymtaw.dao;
//Marta Granado Rodríguez 75%
import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.entity.ValoracionEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ValoracionRepository extends JpaRepository<ValoracionEntity, ValoracionEntityId> {

    @Query("SELECT v FROM ValoracionEntity v WHERE v.exercise.id = :id")
    List<ValoracionEntity> getValoracionesByExercise(Integer id);

    @Query("SELECT v FROM ValoracionEntity v WHERE v.exercise.id = :id")
    ValoracionEntity getValoracionByExercise(Integer id);

    @Query("select v from ValoracionEntity v where v.user.id = :idCliente order by v.exercise.id")
    List<ValoracionEntity> findValoracionEntitiesByIdCliente(Integer idCliente);

    @Query("select  v from ValoracionEntity v where v.user.id = :id AND v.exercise.id = :idEjercicio")
    ValoracionEntity getValoracionesByExerciseAndCliente(Integer idEjercicio, Integer id);
}
