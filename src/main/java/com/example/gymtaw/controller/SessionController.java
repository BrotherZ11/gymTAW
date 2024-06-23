package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
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
    UserRepository userRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ExerciseHasSessionRepository exerciseHasSessionRepository;

    @Autowired
    TypeHasSessionRepository typeHasSessionRepository;


    @GetMapping("/crear_sesion")
    public String doNueva(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idRutina = (Integer) session.getAttribute("idRutina");
            SessionEntity sesion = new SessionEntity();
            sesion.setId(-1);
            List<ExerciseEntity> ejercicios = exerciseRepository.findAll();
            Map<Integer, Integer> ejercicioOrdenMap = new HashMap<>();
            model.addAttribute("ejercicioOrdenMap", ejercicioOrdenMap);
            model.addAttribute("sesion", sesion);
            model.addAttribute("idRutina", idRutina);
            model.addAttribute("ejercicios", ejercicios);
            return "session";
        }

    }
    @GetMapping("/editar_sesion")
    public String doEditar(Model model, HttpSession session, @RequestParam("idSesion") Integer idSesion) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            Integer idRutina = (Integer) session.getAttribute("idRutina");
            SessionEntity sesion = this.sessionRepository.findById(idSesion).orElse(null);
            List<ExerciseHasSessionEntity> ejerciciosEnSesion = exerciseHasSessionRepository.findBySessionId(idSesion);

            Map<Integer, Integer> ejercicioOrdenMap = new HashMap<>();
            for (ExerciseHasSessionEntity ejercicioEnSesion : ejerciciosEnSesion) {
                ejercicioOrdenMap.put(ejercicioEnSesion.getId().getExerciseId(), ejercicioEnSesion.getId().getOrder());
            }
            List<ExerciseEntity> ejercicios = exerciseRepository.findAll();

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
            UserEntity usuario = (UserEntity) session.getAttribute("usuario");
            Integer idRutina = (Integer) session.getAttribute("idRutina");
            UserEntity entrenador = userRepository.findById(usuario.getId()).orElse(null);
            SessionEntity sesion = (idSesion != null) ? this.sessionRepository.findById(idSesion).orElse(new SessionEntity()) : new SessionEntity();

            sesion.setName(nombre);
            sesion.setIdtrainer(entrenador);
            sesion = this.sessionRepository.save(sesion);

            if (ejercicioIds != null) {
                //Creo una lista con los tipos de la sesion
                Set<TypeEntity> tiposSesion = new HashSet<>();

                for (Integer ejercicioId : ejercicioIds) {
                    Integer orden = Integer.parseInt(requestParams.get("orden_" + ejercicioId));
                    ExerciseHasSessionEntityId exerciseHasSessionId = new ExerciseHasSessionEntityId();
                    exerciseHasSessionId.setSessionId(sesion.getId());
                    exerciseHasSessionId.setExerciseId(ejercicioId);
                    exerciseHasSessionId.setOrder(orden);

                    ExerciseEntity exercise = exerciseRepository.findById(ejercicioId).orElse(null);

                    ExerciseHasSessionEntity exerciseHasSession = new ExerciseHasSessionEntity();
                    exerciseHasSession.setId(exerciseHasSessionId);
                    exerciseHasSession.setExercise(exercise);
                    exerciseHasSession.setSession(sesion);
                    this.exerciseHasSessionRepository.save(exerciseHasSession);

                    //Añado tipos de la sesion a la lista para quitar duplicados
                    tiposSesion.add(exercise.getTypeIdtype());
                }

                //Una vez quitados los repetidos, guardamos los tipos unicos en las sesiones
                for(TypeEntity tipo : tiposSesion){
                    TypeHasSessionEntityId typeHasSessionEntityId = new TypeHasSessionEntityId();
                    typeHasSessionEntityId.setSessionId(sesion.getId());
                    typeHasSessionEntityId.setTypeIdtype(tipo.getId());

                    TypeHasSessionEntity typeHasSessionEntity = new TypeHasSessionEntity();
                    typeHasSessionEntity.setId(typeHasSessionEntityId);
                    typeHasSessionEntity.setSession(sesion);
                    typeHasSessionEntity.setTypeIdtype(tipo);
                    typeHasSessionRepository.save(typeHasSessionEntity);
                }

            }

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
}
