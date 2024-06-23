package com.example.gymtaw.service;

import com.example.gymtaw.dao.ExerciseHasSessionRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.dto.*;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseHasSessionService extends DTOService<ExerciseHasSession, ExerciseHasSessionEntity> {

    @Autowired
    private ExerciseHasSessionRepository exerciseHasSessionRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public void borrarEjerciciosdeSesion(Integer idSesion){
        List<ExerciseHasSessionEntity> ejercicios = this.exerciseHasSessionRepository.getExercisesHasSessionByIdSession(idSesion);
        exerciseHasSessionRepository.deleteAll(ejercicios);
    }

    public void guardarSesionEnRutina(Exercise exercise, Session session, Integer orden) {
        ExerciseEntity ejercicio = exerciseRepository.findById(exercise.getId()).orElse(null);
        SessionEntity sesion = sessionRepository.findById(session.getId()).orElse(null);

        ExerciseHasSessionEntityId exerciseHasSessionId = new ExerciseHasSessionEntityId();
        exerciseHasSessionId.setSessionId(sesion.getId());
        exerciseHasSessionId.setExerciseId(ejercicio.getId());
        exerciseHasSessionId.setOrder(orden);


        ExerciseHasSessionEntity exerciseHasSession = new ExerciseHasSessionEntity();
        exerciseHasSession.setId(exerciseHasSessionId);
        exerciseHasSession.setExercise(ejercicio);
        exerciseHasSession.setSession(sesion);
        this.exerciseHasSessionRepository.save(exerciseHasSession);
    }
}
