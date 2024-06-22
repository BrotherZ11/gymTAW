package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ClientExerciseEntity;
import com.example.gymtaw.entity.ClientExerciseEntityId;
import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientExerciseRepository extends JpaRepository<ClientExerciseEntity, ClientExerciseEntityId> {
    @Query("select ce from ClientExerciseEntity ce join ValoracionEntity v on v.exercise.id = ce.exercise.id where v.user.id = :idCliente order by ce.exercise.id")
    public List<ClientExerciseEntity> getClientExerciseEntitiesByIdClienteAndHaveReview(Integer idCliente);
}
