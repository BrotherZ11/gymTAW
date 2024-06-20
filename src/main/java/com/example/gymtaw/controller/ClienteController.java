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

import java.util.List;

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
    UserRepository userRepository;

    @Autowired
    ValoracionRepository valoracionRepository;

    @GetMapping("/entrenamientos")
    public String doListar(@RequestParam("idCliente") Integer idCliente, Model model) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(idCliente);
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("idCliente", idCliente);
        return "entrenamiento_rutina_cliente";
    }

    @GetMapping("/sesionesSemanales")
    public String getSesionesSemanales(@RequestParam("idRutina") Integer idRutina, Model model) {
        List<SessionEntity> sesiones = sessionRepository.getSessionsByIdRoutine(idRutina);
        model.addAttribute("sesiones", sesiones);
        List<RoutineHasSessionEntity> sesionRutina = routineHasSessionRepository.findSessionsByRoutineId(idRutina);
        model.addAttribute("sesionRutina", sesionRutina);
        return "cliente_sesiones_semanales";
    }

    @GetMapping("/ejercicio")
    public String getSesion(@RequestParam("idSesion") Integer idSesion, Model model) {
        List<SessionEntity> sesiones = sessionRepository.findSessionBySessionId(idSesion);
        model.addAttribute("sesiones", sesiones);
        List<ExerciseEntity> ejercicios = exerciseHasSessionRepository.getExercisesByIdSession(idSesion);
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("idSesion", idSesion);
        return "entrenamiento_sesion_cliente";
    }

    @PostMapping("/guardarCompletado")
    public String guardarComplecion(@RequestParam("idSesion") Integer idSesion,
                                    @RequestParam("idEjercicio") Integer idEjercicio,
                                    @RequestParam("idCliente") Integer idCliente,
                                    @RequestParam(value = "done", required = false) String done,
                                    Model model) {
        // Find the exercise by id
        ExerciseEntity exercise = exerciseRepository.findById(idEjercicio).orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + idEjercicio));

        // Find or create the valoracion entity for this exercise
        List<ValoracionEntity> val = exercise.getValoracions();
        boolean valoracionExists = false;
        if (val != null) {
            for (ValoracionEntity v : val) {
                if (v.getUser().getId().equals(idCliente)) {
                    if ("1".equals(done)) {
                        v.setDone((byte) 1); // Marking as done
                    } else {
                        v.setDone((byte) 0); // Marking as not done
                    }
                    valoracionRepository.save(v);
                    valoracionExists = true;
                    break;
                }
            }
        }

        if (!valoracionExists && "1".equals(done)) {
            ValoracionEntity newValoracion = new ValoracionEntity();
            ValoracionEntityId valoracionId = new ValoracionEntityId();
            valoracionId.setUserId(idCliente);
            valoracionId.setExerciseId(idEjercicio);
            newValoracion.setId(valoracionId);
            newValoracion.setUser(userRepository.findById(idCliente).orElseThrow(() -> new IllegalArgumentException("Invalid user Id")));
            newValoracion.setExercise(exercise);
            newValoracion.setDone((byte) 1);
            valoracionRepository.save(newValoracion);
        }

        // Redirect back to the session page
        return "redirect:/home/cliente/ejercicio?idSesion=" + idSesion;
    }



    @GetMapping("/ejercicioIndividual")
    public String getEjercicioCliente(@RequestParam("idEjercicio") Integer idEjercicio, Model model) {
        ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("ejercicio", ejercicio);
        List<ClientExerciseEntity> clientExercise = clientExerciseRepository.getClientExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("clientExercise", clientExercise);
        return "entrenamiento_ejercicio_cliente";
    }

    @GetMapping("/valorarEjercicio")
    public String valorarDesdeEjercicio(@RequestParam("idEjercicio") Integer idEjercicio, @RequestParam("idCliente") Integer idCliente, @RequestParam("idSesion") Integer idSesion, Model model) {
        ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("ejercicio", ejercicio);
        ValoracionEntity nuevaValoracion = new ValoracionEntity();
        ValoracionEntityId nuevaValoracionId = new ValoracionEntityId();
        nuevaValoracion.setId(nuevaValoracionId);
        model.addAttribute("nuevaValoracion", nuevaValoracion);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("idSesion", idSesion); // Add idSesion to the model
        return "valorarUnEjercicio";
    }


    @PostMapping("/guardar")
    public String guardarValoracion(@RequestParam("stars") Integer stars,
                                    @RequestParam("idCliente") Integer userId,
                                    @RequestParam("exerciseId") Integer exerciseId,
                                    @RequestParam("idSesion") Integer idSesion, // Ensure this parameter is here
                                    Model model) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        ExerciseEntity exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + exerciseId));

        // Find or create the valoracion entity for this exercise
        List<ValoracionEntity> val = exercise.getValoracions();
        boolean valoracionExists = false;
        if (val != null) {
            for (ValoracionEntity v : val) {
                if (v.getUser().getId().equals(userId)) {
                    v.setStars(stars);  // Set the rating
                    valoracionRepository.save(v);
                    valoracionExists = true;
                    break;
                }
            }
        }

        if (!valoracionExists) {
            ValoracionEntity nuevaValoracion = new ValoracionEntity();
            ValoracionEntityId valoracionEntityId = new ValoracionEntityId();
            valoracionEntityId.setUserId(userId);
            valoracionEntityId.setExerciseId(exerciseId);

            nuevaValoracion.setId(valoracionEntityId);
            nuevaValoracion.setUser(user);
            nuevaValoracion.setExercise(exercise);
            nuevaValoracion.setStars(stars);
            nuevaValoracion.setDone((byte) 1); // Marking the rating as done

            valoracionRepository.save(nuevaValoracion);
        }

        // Redirect back to the session page
        return "redirect:/home/cliente/ejercicio?idSesion=" + idSesion;
    }





}
