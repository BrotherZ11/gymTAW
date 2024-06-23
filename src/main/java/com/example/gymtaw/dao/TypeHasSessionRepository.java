
package com.example.gymtaw.dao;
//David Molina Lopez 100%
import com.example.gymtaw.entity.TypeHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeHasSessionRepository extends JpaRepository<TypeHasSessionEntity, Integer> {
    @Query("select t from TypeHasSessionEntity t where t.session.id = :idSession")
    public List<TypeHasSessionEntity> getTypeHasRoutineEntitiesBySessionId(Integer idSession);
}
