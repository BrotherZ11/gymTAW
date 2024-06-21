package com.example.gymtaw.dao;

import com.example.gymtaw.entity.TypeEntity;
import com.example.gymtaw.entity.TypeHasRoutineEntity;
import com.example.gymtaw.entity.TypeHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeHasSessionRepository extends JpaRepository<TypeHasSessionEntity, Integer> {
    @Query("select t from TypeHasSessionEntity t where t.session.id = :idSession")
    public List<TypeHasSessionEntity> getTypeHasRoutineEntitiesBySessionId(Integer idSession);

    @Query("delete from TypeHasSessionEntity ts where ts.session.id = :idSesion")
    void deleteBySessionId(@Param("idSesion") Integer idSesion);
}
