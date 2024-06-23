package com.example.gymtaw.service;
//Marta Granado Rodríguez 25%
import com.example.gymtaw.dao.ClientExerciseRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dto.ClientExercise;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.entity.ClientExerciseEntity;
import com.example.gymtaw.entity.ClientExerciseEntityId;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientExerciseService extends DTOService<ClientExercise, ClientExerciseEntity>{

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    UserRepository userRepository;


    public List<ClientExercise> findClientExercisesWithAReviewByIdClientAndIdETrainer(Integer idCliente, Integer idEntrenador){
        List<ClientExerciseEntity> ejerciciosCliente = clientExerciseRepository.getClientExerciseEntitiesByIdClienteIdTrainerAndHaveReview(idCliente, idEntrenador);
        return this.entidadesADTO(ejerciciosCliente);
    }

    public ClientExercise findClientExerciseByIdExerciseIdClienteAndIdEntrenador(Integer idEjercicio, Integer idCliente, Integer idEntrenador){
        ClientExerciseEntityId clientExerciseId = new ClientExerciseEntityId();
        clientExerciseId.setExerciseId(idEjercicio);
        clientExerciseId.setUserId(idCliente);
        clientExerciseId.setTrainerId(idEntrenador);

        ClientExerciseEntity clientExercise = clientExerciseRepository.findById(clientExerciseId).orElse(null);
        if (clientExercise != null) {
            return clientExercise.toDTO();
        } else {
            return null;
        }
    }

    public ClientExercise saveClientExercise(Integer idEjercicio, Integer idCliente, Integer idEntrenador, String reps, String sets, String peso, Double calorias, Double distancia){
        ClientExerciseEntityId clientExerciseId = new ClientExerciseEntityId();
        clientExerciseId.setExerciseId(idEjercicio);
        clientExerciseId.setUserId(idCliente);
        clientExerciseId.setTrainerId(idEntrenador);

        ClientExerciseEntity clientExercise = clientExerciseRepository.findById(clientExerciseId).orElse(null);

        if (clientExercise == null) {
            clientExercise = new ClientExerciseEntity();
        }

        if(calorias==null) calorias=0.0;
        if(distancia==null) distancia=0.0;

        ExerciseEntity ejercicio = exerciseRepository.findById(idEjercicio).orElse(null);
        UserEntity cliente = userRepository.findById(idCliente).orElse(null);
        UserEntity entrenador = userRepository.findById(idEntrenador).orElse(null);

        clientExercise.setExercise(ejercicio);
        clientExercise.setUser(cliente);
        clientExercise.setTrainer(entrenador);
        clientExercise.setId(clientExerciseId);
        clientExercise.setReps(reps);
        clientExercise.setSets(sets);
        clientExercise.setWeight(peso);
        clientExercise.setCalories(calorias);
        clientExercise.setDistance(distancia);
        clientExerciseRepository.save(clientExercise);

        return clientExercise.toDTO();
    }

    public List<ClientExercise> findClientExerciseByIdIdCliente(Integer id) {
        List<ClientExerciseEntity> clientExercises = clientExerciseRepository.getClientExercisesByIdCliente(id);
        return this.entidadesADTO(clientExercises);
    }
}
