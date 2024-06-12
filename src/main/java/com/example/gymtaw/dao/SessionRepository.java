package com.example.gymtaw.dao;

import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

   /* @Query(value = "SELECT * FROM session\n" +
            "join session_routine\n" +
            "on session.id = session_routine.session_id\n" +
            "where session_routine.routine_idroutine = :idRutina\n" +
            "order by session_routine.day", nativeQuery = true)*/
   @Query("select s from SessionEntity s join RoutineHasSessionEntity rs on s.id = rs.sessionId where rs.routineIdroutine = :idRutina order by rs.day")
    public List<SessionEntity> getSessionsByIdRoutine(@Param("idRutina") Integer idRutina);
    @Query(value = "SELECT * FROM session\n" +
            "where session.idTrainer = :idEntrenador", nativeQuery = true)
    public List<SessionEntity> getSessionsByIdEntrenador(@Param("idEntrenador") Integer idEntrenador);

}
