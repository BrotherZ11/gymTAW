package com.example.gymtaw.dao;

import com.example.gymtaw.entity.SessionRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRoutineRepository extends JpaRepository<SessionRoutine,Integer> {
    @Query("SELECT sr FROM SessionRoutine sr WHERE sr.routineIdroutine.id = :routineId")
    List<SessionRoutine> findSessionsByRoutineId(Integer routineId);
}
