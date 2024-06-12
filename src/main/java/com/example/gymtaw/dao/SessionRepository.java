package com.example.gymtaw.dao;

import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

    @Query(value = "SELECT * FROM session \n" +
            "join session_routine \n" +
            "on session.id = session_routine.session_id\n" +
            "where session_routine.routine_idroutine = :idRutina", nativeQuery = true)
    public List<SessionEntity> getSessionsByIdRoutine(@Param("idRutina") Integer idRutina);

}
