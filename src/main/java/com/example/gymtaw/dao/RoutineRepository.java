package com.example.gymtaw.dao;

import com.example.gymtaw.entity.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Integer> {

    @Query(value = "select * from routine join trainer_routine\n" +
            "on routine.idroutine = trainer_routine.routine_idroutine\n" +
            "where trainer_routine.trainer_id = :idEntrenador\n" +
            "and trainer_routine.user_id = :idCliente", nativeQuery = true)
    public List<RoutineEntity> getRoutinesByIdEntrenadorAndIdCliente(@Param("idEntrenador") Integer idEntrenador,
                                                                     @Param("idCliente") Integer idCliente);

    @Query(value = "select * from routine join trainer_routine\n" +
            "on routine.idroutine = trainer_routine.routine_idroutine\n" +
            "where trainer_routine.trainer_id = :idEntrenador\n" +
            "and trainer_routine.user_id != :idCliente", nativeQuery = true)
    public List<RoutineEntity> getRoutinesByIdEntrenadorDontHaveIdCliente(@Param("idEntrenador") Integer idEntrenador,
                                                       @Param("idCliente") Integer idCliente);
    @Query(value = "select * from routine join trainer_routine\n" +
            "on routine.idroutine = trainer_routine.routine_idroutine\n" +
            "where trainer_routine.trainer_id = :idEntrenador\n", nativeQuery = true)
    public List<RoutineEntity> getRoutinesByIdEntrenador(@Param("idEntrenador") Integer idEntrenador);

}
