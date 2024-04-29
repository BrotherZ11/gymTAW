package com.example.gymtaw.dao;

import com.example.gymtaw.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {
}
