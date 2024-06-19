package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/cliente")
public class ClienteController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ExerciseHasSessionRepository exerciseHasSessionRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    @Autowired
    ValoracionRepository valoracionRepository;

    @GetMapping("/entrenamientos")
    public String doListar(@RequestParam("idCliente") Integer idCliente, Model model) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(idCliente);
        model.addAttribute("rutinas", rutinas); // Make sure this name matches the JSP
        model.addAttribute("idCliente", idCliente);
        return "entrenamiento_rutina_cliente";
    }

    @GetMapping("/sesionesSemanales")
    public String getSesionesSemanales(@RequestParam("idRutina") Integer idRutina, Model model) {
        List<SessionEntity> sesiones = this.sessionRepository.getSessionsByIdRoutine(idRutina);
        model.addAttribute("sesiones", sesiones);
        List<RoutineHasSessionEntity> sesionRutina = this.routineHasSessionRepository.findSessionsByRoutineId(idRutina);
        model.addAttribute("sesionRutina", sesionRutina);
        return "cliente_sesiones_semanales";
    }

    @GetMapping("/ejercicio")
    public String getSesion(@RequestParam("idSesion") Integer idSesion, Model model) {
        List<SessionEntity> sesiones = sessionRepository.findSessionBySessionId(idSesion);
        model.addAttribute("sesiones", sesiones);
        List<ExerciseEntity> ejercicios = this.exerciseHasSessionRepository.getExercisesByIdSession(idSesion);
        model.addAttribute("ejercicios", ejercicios);
        return "entrenamiento_sesion_cliente";

    }


    @GetMapping("/ejercicioIndividual")
    public String getEjercicioCliente(@RequestParam("idEjercicio") Integer idEjercicio, Model model) {
        ExerciseEntity ejercicio = this.exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("ejercicio", ejercicio);
        List<ClientExerciseEntity> clientExercise = this.clientExerciseRepository.getClientExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("clientExercise", clientExercise);

        return "entrenamiento_ejercicio_cliente";
    }

    @GetMapping("/valorar")
    public String valorarDesdeEjercicio(@RequestParam("idEjercicio") Integer idEjercicio, Model model) {
        ExerciseEntity ejercicio = this.exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("ejercicio", ejercicio);

        return "valorarUnEjercicio";
    }


}
