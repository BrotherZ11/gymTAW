package com.example.gymtaw.dao;
//Marta Granado Rodríguez 35%, David Zarzavilla Borrego 15%, Gonzalo Muñoz Rubio 25%, David Molina Lopez 25%
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion")
    public List<ExerciseEntity> getExercisesByIdSession(@Param("idSesion") Integer idSesion);

    @Query("SELECT e FROM ExerciseEntity e WHERE e.name LIKE CONCAT('%', :nombre, '%')")
    List<ExerciseEntity> findExercisesByName(@Param("nombre") String nombre);

    @Query(value = "select e from ExerciseEntity e join ExerciseHasSessionEntity es on e.id = es.exercise.id where es.session.id = :idSesion and exists (select ce from ClientExerciseEntity ce where ce.exercise = es.exercise)")
    public List<ExerciseEntity> getExercisesByIdSessionWithData(@Param("idSesion") Integer idSesion);

    @Query("select e from ExerciseEntity e join ValoracionEntity v on v.exercise.id = e.id where v.user.id = :idCliente order by e.id")
    public List<ExerciseEntity> getExerciseEntitiesByIdClienteAndHaveReview(Integer idCliente);

    @Query("select e from ExerciseEntity e join ValoracionEntity v on v.exercise.id = e.id where v.stars = :stars and v.user.id = :idUsuario")
    List<ExerciseEntity> getExercisesByNumEstrellasEIdUsuario(Integer idUsuario, Integer stars);

    @Query("select e from ExerciseEntity e where e.typeIdtype.id = 1")
    List<ExerciseEntity> getExerciseFuerza();

    @Query("select e from ExerciseEntity e where e.typeIdtype.id = :idTipo")
    List<ExerciseEntity> findExercisesByType(Integer idTipo);

    @Query("select e from ExerciseEntity e where e.name like concat('%', :nombre, '%') and e.description like concat('%', :descripcion, '%') and e.video like concat('%', :url, '%')  ")
    List<ExerciseEntity> findExercisesByNameDescriptionUrl(String nombre, String descripcion, String url);
}
