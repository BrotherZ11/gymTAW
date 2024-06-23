package com.example.gymtaw.dao;
//Marta Granado Rodríguez
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

   @Query("select s from SessionEntity s join RoutineHasSessionEntity rs on s.id = rs.session.id where rs.routine.id = :idRutina order by rs.id.day")
    public List<SessionEntity> getSessionsByIdRoutine(@Param("idRutina") Integer idRutina);
    @Query(value = "SELECT * FROM session\n" +
            "where session.idTrainer = :idEntrenador", nativeQuery = true)
    public List<SessionEntity> getSessionsByIdEntrenador(@Param("idEntrenador") Integer idEntrenador);

    @Query(value = "select s from SessionEntity s where s.id = :idSesion")
    public List<SessionEntity> findSessionBySessionId(@Param("idSesion") Integer idSesion);

//    @Query(value = "select s from SessionEntity s join  ExerciseHasSessionEntity es on s.id = es.session.id where es.exercise.id = :exerciseId")
//    SessionEntity getSessionsByIdEjercicio(@Param("exerciseId") Integer exerciseId);
}
