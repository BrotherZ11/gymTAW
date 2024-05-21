package com.example.gymtaw.dao;



import com.example.gymtaw.entity.SessionRoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRoutineRepository extends JpaRepository<SessionRoutineEntity,Integer> {
    @Query("SELECT sr FROM SessionRoutineEntity sr WHERE sr.routineIdroutine = :routineId")
    List<SessionRoutineEntity> findSessionsByRoutineId(Integer routineId);
}
