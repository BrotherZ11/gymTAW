//David Zarzavilla Borrego 100%
package com.example.gymtaw.dao;

import com.example.gymtaw.entity.RoutineHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutineHasSessionRepository extends JpaRepository<RoutineHasSessionEntity,Integer> {
    @Query("select rs from RoutineHasSessionEntity rs where rs.routine.id = :idRutina order by rs.id.day")
    public List<RoutineHasSessionEntity> getSessionsRoutineByIdRoutine(@Param("idRutina") Integer idRutina);
}
