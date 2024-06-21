package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/home/trainer")
public class SessionController extends BaseController{
    @Autowired
    SessionRepository sessionRepository;

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
            SessionEntity sesion = new SessionEntity();
            sesion.setId(-1);
            List<ExerciseEntity> ejercicios = exerciseRepository.findAll();
            model.addAttribute("sesion", sesion);
            model.addAttribute("ejercicios", ejercicios);
            return "session";
        }

    }

    @PostMapping("/guardar_sesion")
    public String doGuardar(@RequestParam("id") Integer id,
                            @RequestParam("nombre") String nombre,
                            @RequestParam(value = "ejercicioId", required = false) List<Integer> ejercicioIds,
                            @RequestParam Map<String, String> requestParams,
                            HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            Integer idEntrenador = (Integer) session.getAttribute("idEntrenador");

            UserEntity entrenador = userRepository.findById(idEntrenador).orElse(null);
            SessionEntity sesion = this.sessionRepository.findById(id).orElse(null);

            if (sesion == null) {
                sesion = new SessionEntity();
            }

            sesion.setName(nombre);
            sesion.setIdtrainer(entrenador);

            sesion = this.sessionRepository.save(sesion);

            //Creo una lista con los tipos de la sesion
            Set<TypeEntity> tiposSesion = new HashSet<>();

            if (ejercicioIds != null) {
                for (Integer ejercicioId : ejercicioIds) {
                    Integer orden = Integer.parseInt(requestParams.get("orden_" + ejercicioId));
                    ExerciseHasSessionEntityId exerciseHasSessionId = new ExerciseHasSessionEntityId();
                    exerciseHasSessionId.setSessionId(sesion.getId());
                    exerciseHasSessionId.setExerciseId(ejercicioId);
                    exerciseHasSessionId.setOrder(orden);

                    ExerciseEntity exercise = exerciseRepository.findById(ejercicioId).orElse(null);

                    if (!this.exerciseHasSessionRepository.existsById(exerciseHasSessionId)) {
                        ExerciseHasSessionEntity exerciseHasSession = new ExerciseHasSessionEntity();
                        exerciseHasSession.setId(exerciseHasSessionId);
                        exerciseHasSession.setExercise(exercise);
                        exerciseHasSession.setSession(sesion);
                        this.exerciseHasSessionRepository.save(exerciseHasSession);
                    } else {
                        throw new IllegalArgumentException("Ya existe una entrada con el mismo ID.");
                    }

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

            return "redirect:/home/trainer/ver";
        }

    }

    @GetMapping("borrar_sesion")
    public String doBorrar(@RequestParam("id") Integer id, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idRutina =(Integer) session.getAttribute("idRutina");
            this.sessionRepository.deleteById(id);

            return "redirect:/home/trainer/ver?idRutina="+idRutina;
        }


    }
}
