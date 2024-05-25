package com.example.gymtaw.dao;

import com.example.gymtaw.entity.TrainerRoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TrainerRoutineRepository extends JpaRepository<TrainerRoutineEntity, Integer> {

    @Query(value = "select *  from trainer_routine \n" +
            "where trainer_routine.trainer_id = :idEntrenador\n" +
            "and  trainer_routine.user_id = :idCliente\n" +
            "and  trainer_routine.routine_idroutine = :idRutina", nativeQuery = true)
    public TrainerRoutineEntity getTrainerRoutine(@Param("idEntrenador") Integer idEntrenador,
                                        @Param("idCliente") Integer idCliente,
                                        @Param("idRutina") Integer idRutina);

}
