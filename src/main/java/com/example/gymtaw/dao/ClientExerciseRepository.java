package com.example.gymtaw.dao;
//Marta Granado Rodríguez
import com.example.gymtaw.entity.ClientExerciseEntity;
import com.example.gymtaw.entity.ClientExerciseEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientExerciseRepository extends JpaRepository<ClientExerciseEntity, ClientExerciseEntityId> {

    @Query("select ce from ClientExerciseEntity ce where ce.exercise.id = :idEjercicio")
    List<ClientExerciseEntity> getClientExercisesByIdEjercicio(@Param("idEjercicio") Integer idEjercicio);

//    @Query("select ce.id from ClientExerciseEntity ce where ce.exercise.id = :idEjercicio")
//    Integer idClienteByExerciseId(@Param("idEjercicio") Integer idEjercicio);


//    @Query("select e.id from ClientExerciseEntity ce join ExerciseEntity e on e.id = ce.exercise.id where ce.user.id = :idCliente")
//    List<Integer> findExerciseIdByClientId(Integer idCliente);



    @Query("select ce from ClientExerciseEntity ce join ValoracionEntity v on v.exercise.id = ce.exercise.id where v.user.id = :idCliente order by ce.exercise.id")
    public List<ClientExerciseEntity> getClientExerciseEntitiesByIdClienteAndHaveReview(Integer idCliente);
}
