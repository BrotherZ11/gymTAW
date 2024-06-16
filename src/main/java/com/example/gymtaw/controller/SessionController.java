package com.example.gymtaw.controller;

import com.example.gymtaw.dao.ExerciseHasSessionRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home/trainer")
public class SessionController {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ExerciseHasSessionRepository exerciseHasSessionRepository;

    @GetMapping("/crearsesion")
    public String doNueva(Model model, @RequestParam("idEntrenador") Integer idEntrenador) {
        SessionEntity sesion = new SessionEntity();
        sesion.setId(-1);
        List<ExerciseEntity> ejercicios = exerciseRepository.findAll();
        model.addAttribute("sesion", sesion);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("ejercicios", ejercicios);
        return "session";
    }

/*    @PostMapping("/guardar_sesion")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("idEntrenador") Integer idEntrenador,
                             @RequestParam(value = "ejercicioId", required = false) List<Integer> ejercicioIds,
                             @RequestParam Map<String, String> requestParams){
        UserEntity entrenador = userRepository.findById(idEntrenador).orElse(null);
        SessionEntity sesion = this.sessionRepository.findById(id).orElse(new SessionEntity());
        sesion.setName(nombre);
        sesion.setUserByIdtrainer(entrenador);
        this.sessionRepository.save(sesion);
        if (ejercicioIds != null) {
            for (Integer ejercicioId : ejercicioIds) {
                Integer orden = Integer.parseInt(requestParams.get("orden_" + ejercicioId));
                ExerciseHasSessionEntity exerciseHasSession = new ExerciseHasSessionEntity();
                exerciseHasSession.setExerciseId(ejercicioId);
                exerciseHasSession.setSessionId(sesion.getId());
                exerciseHasSession.setOrder(orden);
                this.exerciseHasSessionRepository.save(exerciseHasSession);
            }
        }
        return "redirect:/home/trainer/";
    }*/
}
