package com.example.gymtaw.dao;

import com.example.gymtaw.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {

    @Query("select r from Routine r where r.name like concat('%', :filtro, '%') ")
    public List<Routine> findByFiltro(@Param("filtro") String filtro);
}
