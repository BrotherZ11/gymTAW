package com.example.gymtaw.dao;



import com.example.gymtaw.entity.RoutineHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutineHasSessionRepository extends JpaRepository<RoutineHasSessionEntity,Integer> {
    @Query("SELECT sr FROM RoutineHasSessionEntity sr WHERE sr.id.routineId = :routineId")
    List<RoutineHasSessionEntity> findSessionsByRoutineId(Integer routineId);

 /*   @Query(value = "SELECT * FROM session\n" +
            "join session_routine\n" +
            "on session.id = session_routine.session_id\n" +
            "where session_routine.routine_idroutine = :idRutina\n" +
            "order by session_routine.day", nativeQuery = true)*/
    @Query("select rs from RoutineHasSessionEntity rs where rs.id.routineId = :idRutina order by rs.session.id")
    public List<RoutineHasSessionEntity> getSessionsRoutineByIdRoutine(@Param("idRutina") Integer idRutina);
}
