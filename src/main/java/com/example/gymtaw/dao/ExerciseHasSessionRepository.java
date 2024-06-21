package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.ExerciseHasSessionEntity;
import com.example.gymtaw.entity.ExerciseHasSessionEntityId;
import com.example.gymtaw.entity.RoutineHasSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseHasSessionRepository
        extends JpaRepository<ExerciseHasSessionEntity, ExerciseHasSessionEntityId> {
    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion")
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);

    @Query(value = "SELECT e FROM ExerciseHasSessionEntity e where e.session.id = :id")
    public List<ExerciseHasSessionEntity> getExercisesHasSessionByIdSession(@Param("id") Integer id);

    @Query("select es from ExerciseHasSessionEntity es where es.session.id = :idSesion order by es.id.order")
    public List<ExerciseHasSessionEntity> findBySessionId(@Param("idSesion") Integer idSesion);

}
