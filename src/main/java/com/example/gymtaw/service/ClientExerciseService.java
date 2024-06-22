package com.example.gymtaw.service;

import com.example.gymtaw.dao.ClientExerciseRepository;
import com.example.gymtaw.dto.ClientExercise;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.entity.ClientExerciseEntity;
import com.example.gymtaw.entity.ExerciseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientExerciseService extends DTOService<ClientExercise, ClientExerciseEntity>{

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    public List<ClientExercise> getClientExercisesByIdEjercicio(Integer idEjercicio) {
        List<ClientExerciseEntity> clientExercises = clientExerciseRepository.getClientExercisesByIdEjercicio(idEjercicio);
        return this.entidadesADTO(clientExercises);
    }
}
