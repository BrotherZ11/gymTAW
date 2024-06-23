package com.example.gymtaw.dao;
//Marta Granado Rodríguez 70%
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion")
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);

    @Query(value = "select e from ExerciseEntity e where e.id = :idEjercicio")
    ExerciseEntity getExercisesByIdEjercicio(@Param("idEjercicio") Integer idEjercicio);

    @Query(value = "select e from ExerciseEntity e join ValoracionEntity v on v.exercise.id = :id where e.id = :id AND v.stars = :stars")
    ExerciseEntity getExercisesByIdEjercicioAndFiltro(Integer id, Integer stars);

    @Query("SELECT e FROM ExerciseEntity e WHERE e.name LIKE CONCAT('%', :nombre, '%')")
    List<ExerciseEntity> findExercisesByName(@Param("nombre") String nombre);

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion and exists (select ce from ClientExerciseEntity ce where ce.exercise = es.exercise)")
    public List<ExerciseEntity> getExercisesByIdSessionWithData(@Param("idSesion") Integer idSesion);

    @Query("select e from ExerciseEntity e join ValoracionEntity v on v.exercise.id = e.id where v.user.id = :idCliente order by e.id")
    public List<ExerciseEntity> getExerciseEntitiesByIdClienteAndHaveReview(Integer idCliente);

    @Query("select e from ExerciseEntity e join ValoracionEntity v on v.exercise.id = e.id where v.stars = :stars and v.user.id = :idUsuario")
    List<ExerciseEntity> getExercisesByNumEstrellasEIdUsuario(Integer idUsuario, Integer stars);
}
