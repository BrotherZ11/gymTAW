package com.example.gymtaw.dao;

import com.example.gymtaw.entity.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Integer> {

/*    @Query(value = "SELECT routine.idroutine as idroutine, routine.name as name, routine.description as description, routine.date as date, routine.idTrainer as id_trainer " +
            "FROM routine JOIN trainer_routine " +
            "ON routine.idroutine = trainer_routine.routine_idroutine " +
            "WHERE trainer_routine.trainer_id = :idEntrenador " +
            "AND trainer_routine.user_id = :idCliente",
            nativeQuery = true)*/
    @Query ("select r from RoutineEntity r where r.idtrainer = :idEntrenador and r.idclient = :idCliente")
    public List<RoutineEntity> getRoutinesByIdEntrenadorAndIdCliente(@Param("idEntrenador") Integer idEntrenador,
                                                                     @Param("idCliente") Integer idCliente);

    @Query(value = "SELECT routine.idroutine as idroutine, routine.name as name, routine.description as description, routine.date as date, routine.idTrainer as id_trainer " +
            "FROM routine JOIN trainer_routine " +
            "ON routine.idroutine = trainer_routine.routine_idroutine " +
            "WHERE trainer_routine.trainer_id = :idEntrenador " +
            "AND trainer_routine.user_id != :idCliente",
            nativeQuery = true)
    public List<RoutineEntity> getRoutinesByIdEntrenadorDontHaveIdCliente(@Param("idEntrenador") Integer idEntrenador,
                                                                          @Param("idCliente") Integer idCliente);

/*    @Query(value = "SELECT  routine.idroutine as idroutine, routine.name as name, routine.description as description, routine.date as date, routine.idTrainer as id_trainer " +
            " FROM routine " +
            "WHERE routine.idTrainer = :idEntrenador",
            nativeQuery = true)
    public List<RoutineEntity> getRoutinesByIdEntrenador(@Param("idEntrenador") Integer idEntrenador);*/

    @Query("select r from RoutineEntity r where r.idtrainer = :idEntrenador")
    public List<RoutineEntity> getRoutinesbyEntrenador(@Param("idEntrenador") Integer idEntrenador);

    @Query("select r from RoutineEntity r where r.name like concat('%', :filtro, '%') ")
    public List<RoutineEntity> findByFiltro(@Param("filtro") String filtro);

    @Query("SELECT r FROM RoutineEntity r WHERE r.idclient.id = :idCliente")
    List<RoutineEntity> getRoutinesByClient(@Param("idCliente") Integer idCliente);

}
