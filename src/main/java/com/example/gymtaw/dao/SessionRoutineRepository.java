package com.example.gymtaw.dao;



import com.example.gymtaw.entity.SessionEntity;
import com.example.gymtaw.entity.SessionRoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRoutineRepository extends JpaRepository<SessionRoutineEntity,Integer> {
    @Query("SELECT sr FROM SessionRoutineEntity sr WHERE sr.routineIdroutine = :routineId")
    List<SessionRoutineEntity> findSessionsByRoutineId(Integer routineId);

    @Query(value = "SELECT * FROM session\n" +
            "join session_routine\n" +
            "on session.id = session_routine.session_id\n" +
            "where session_routine.routine_idroutine = :idRutina\n" +
            "order by session_routine.day", nativeQuery = true)
    public List<SessionRoutineEntity> getSessionsRoutineByIdRoutine(@Param("idRutina") Integer idRutina);
}
