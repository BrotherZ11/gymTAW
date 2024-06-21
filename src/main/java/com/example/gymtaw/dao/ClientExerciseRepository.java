package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ClientExerciseEntity;
import com.example.gymtaw.entity.ClientExerciseEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientExerciseRepository extends JpaRepository<ClientExerciseEntity, ClientExerciseEntityId> {
    
}
