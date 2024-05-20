package com.example.gymtaw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {

    @Query(value = "select * from Routine r join trainer_routine tr on r.idroutine = tr.routine_idroutine  where tr.trainer_id = :idEntrenador and tr.user_id = :idCliente", nativeQuery = true)
    public List<Routine> getRoutinesByIdEntrenadorAndIdCliente(@Param("idEntrenador") Integer idEntrenador,
                                                @Param("idCliente") Integer idCliente);

}
