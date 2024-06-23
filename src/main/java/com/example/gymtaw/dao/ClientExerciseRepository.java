package com.example.gymtaw.dao;
//Marta Granado Rodríguez 50%, David Molina Lopez 50%
import com.example.gymtaw.entity.ClientExerciseEntity;
import com.example.gymtaw.entity.ClientExerciseEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientExerciseRepository extends JpaRepository<ClientExerciseEntity, ClientExerciseEntityId> {

    @Query("select ce from ClientExerciseEntity ce join ValoracionEntity v on v.exercise.id = ce.exercise.id and v.trainer.id = ce.trainer.id where v.user.id = :idCliente and v.trainer.id = :idEntrenador order by ce.exercise.id")
    public List<ClientExerciseEntity> getClientExerciseEntitiesByIdClienteIdTrainerAndHaveReview(Integer idCliente, Integer idEntrenador);

    @Query("select ce from ClientExerciseEntity ce where ce.user.id = :id")
    List<ClientExerciseEntity> getClientExercisesByIdCliente(Integer id);
}
