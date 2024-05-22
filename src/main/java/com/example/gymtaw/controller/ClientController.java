package com.example.gymtaw.controller;

import com.example.gymtaw.dao.ClientRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.SessionEntity;
import com.example.gymtaw.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home/trainer")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @GetMapping("/clients")
    public String doListar (@RequestParam("idEntrenador") Integer idEntrenador, Model model) {
        List<UserEntity> clients = clientRepository.getClientesByIdEntrenador(idEntrenador);
        model.addAttribute("lista", clients);
        model.addAttribute("idEntrenador", idEntrenador);
        return "clients";
    }
    @GetMapping("/routine_client")
    public String doRoutineClient (@RequestParam("idEntrenador") Integer idEntrenador,
                                   @RequestParam("idCliente") Integer idCliente,
                                   Model model) {
        List<RoutineEntity> routineEntities = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        model.addAttribute("lista", routineEntities);
        return "routine_client";
    }

    @GetMapping("/session_client")
    public String doSessionClient (@RequestParam("idRutina") Integer idRutina,
                                   Model model) {
        List<SessionEntity> sessionEntities = sessionRepository.getSessionsByIdRoutine(idRutina);
        model.addAttribute("lista", sessionEntities);
        return "session_client";
    }

    @GetMapping("/exercise_client")
    public String doExerciseClient (@RequestParam("idSesion") Integer idSesion,
                                   Model model) {
        List<ExerciseEntity> exerciseEntities = exerciseRepository.getExercisesByIdSession(idSesion);
        model.addAttribute("lista", exerciseEntities);
        return "exercise_client";
    }

}
