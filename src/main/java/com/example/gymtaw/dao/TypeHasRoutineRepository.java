package com.example.gymtaw.dao;
//David Molina Lopez 100%
import com.example.gymtaw.entity.TypeHasRoutineEntity;
import com.example.gymtaw.entity.TypeHasRoutineEntityId;
import com.example.gymtaw.entity.TypeHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeHasRoutineRepository extends JpaRepository<TypeHasRoutineEntity, TypeHasRoutineEntityId> {
    @Query("select t from TypeHasRoutineEntity t where t.routineIdroutine.id = :idRoutine")
    public List<TypeHasRoutineEntity> getTypeHasRoutineEntitiesByRoutineIdroutine(Integer idRoutine);
}
