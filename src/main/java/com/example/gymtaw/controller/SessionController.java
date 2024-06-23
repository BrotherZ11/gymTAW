package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.ExerciseHasSession;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.service.ExerciseHasSessionService;
import com.example.gymtaw.service.ExerciseService;
import com.example.gymtaw.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/home/trainer")
public class SessionController extends BaseController{
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ExerciseHasSessionRepository exerciseHasSessionRepository;

    @Autowired
    TypeHasSessionRepository typeHasSessionRepository;

    @Autowired
    ExerciseHasSessionService exerciseHasSessionService;


    @GetMapping("/crear_sesion")
    public String doNueva(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            User usuario = (User) session.getAttribute("usuario");
            Integer idRutina = (Integer) session.getAttribute("idRutina");
            List<Exercise> ejercicios;
            if(usuario.getRol().getType().equals("bodybuilding")){
                ejercicios = exerciseService.listarEjerciciosFuerza();
            }else{
                ejercicios = exerciseService.listarEjercicios();
            }
            model.addAttribute("ejercicioOrdenMap", Map.of());
            model.addAttribute("sesion", new Session());
            model.addAttribute("idRutina", idRutina);
            model.addAttribute("ejercicios", ejercicios);
            return "session";
        }

    }
    @GetMapping("/editar_sesion")
    public String doEditar(Model model, HttpSession session, @RequestParam("idSesion") Integer idSesion) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            User usuario = (User) session.getAttribute("usuario");
            Integer idRutina = (Integer) session.getAttribute("idRutina");
            Session sesion = this.sessionService.buscarSesion(idSesion);
            List<ExerciseHasSessionEntity> ejerciciosEnSesion = exerciseHasSessionRepository.findBySessionId(idSesion);
            List<Exercise> ejercicios;
            Map<Integer, Integer> ejercicioOrdenMap = new HashMap<>();
            for (ExerciseHasSessionEntity ejercicioEnSesion : ejerciciosEnSesion) {
                ejercicioOrdenMap.put(ejercicioEnSesion.getId().getExerciseId(), ejercicioEnSesion.getId().getOrder());
            }
            if(usuario.getRol().getType().equals("bodybuilding")){
                ejercicios = exerciseService.listarEjerciciosFuerza();
            }else{
                ejercicios = exerciseService.listarEjercicios();
            }

            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("sesion", sesion);
            model.addAttribute("idRutina", idRutina);
            model.addAttribute("ejercicioOrdenMap", ejercicioOrdenMap);
            return "session";
        }
    }

    @PostMapping("/guardar_sesion")
    public String doGuardar(@RequestParam("idSesion") Integer idSesion,
                            @RequestParam("nombre") String nombre,
                            @RequestParam(value = "ejercicioId", required = false) List<Integer> ejercicioIds,
                            @RequestParam Map<String, String> requestParams,
                            HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            User usuario = (User) session.getAttribute("usuario");
            Integer idRutina = (Integer) session.getAttribute("idRutina");

            this.sessionService.guardarSesion(usuario,idSesion,nombre, ejercicioIds, requestParams);
            return "redirect:/home/trainer/ver?idRutina=" + idRutina;
        }

    }

    @GetMapping("borrar_sesion")
    public String doBorrar(@RequestParam("idSesion") Integer idSesion, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idRutina =(Integer) session.getAttribute("idRutina");
            this.sessionService.borrarSesion(idSesion);

            return "redirect:/home/trainer/ver?idRutina=" + idRutina;
        }

    }

    @GetMapping("/ver_sesion")
    public String doVer(@RequestParam("idSesion") Integer idSesion, HttpSession session, Model model){
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idRutina =(Integer) session.getAttribute("idRutina");
            String nombreSesion = sessionService.obtenerNombreSesion(idSesion);
            List<ExerciseHasSession> ejercicios = exerciseHasSessionService.listarOrdenado(idSesion);

            model.addAttribute("listaEjercicios", ejercicios);
            model.addAttribute("nombreSesion", nombreSesion);
            model.addAttribute("idRutina", idRutina);
            return "verSesion";
        }
    }
}
