package com.example.gymtaw.service;

import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dao.ValoracionRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.dto.Valoracion;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.UserEntity;
import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.entity.ValoracionEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValoracionService extends DTOService<Valoracion, ValoracionEntity>{

    @Autowired
    protected ValoracionRepository valoracionRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ExerciseRepository exerciseRepository;

    public void guardarValoracionNoExiste(User usuario, Integer exerciseId, Integer stars, Exercise exercise){
        ValoracionEntity nuevaValoracion = new ValoracionEntity();
        ValoracionEntityId valoracionEntityId = new ValoracionEntityId();
        UserEntity userEntity = this.userRepository.findById(usuario.getId()).orElse(null);
        ExerciseEntity exerciseEntity = this.exerciseRepository.findById(exerciseId).orElse(null);

        valoracionEntityId.setUserId(usuario.getId());
        valoracionEntityId.setExerciseId(exerciseId);

        nuevaValoracion.setId(valoracionEntityId);
        nuevaValoracion.setUser(userEntity);
        nuevaValoracion.setExercise(exerciseEntity);
        nuevaValoracion.setStars(stars);
        nuevaValoracion.setDone((byte) 1); // Marking the rating as done

        valoracionRepository.save(nuevaValoracion);
    }
}
