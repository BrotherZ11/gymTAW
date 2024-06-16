package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.ui.FiltroRutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExerciseSessionRepository exerciseSessionRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ValoracionRepository valoracionRepository;



    @GetMapping("/trainer/rutina")
    public String doListar (Model model, @RequestParam("idEntrenador") Integer idEntrenador) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesbyEntrenador(idEntrenador);
        model.addAttribute("lista", rutinas);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("filtro", new FiltroRutina());
        return "routine_trainer";
    }

    @PostMapping("/trainer/filtrar")
    public String doListar (@ModelAttribute("filtro") FiltroRutina filtro, Model model) {
        List<RoutineEntity> rutinas = routineRepository.findByFiltro(filtro.getNombre());
        model.addAttribute("lista", rutinas);
        model.addAttribute("filtro", filtro);
        return "routine_trainer";
    }

    @GetMapping("/trainer/borrar")
    public String doBorrar (@RequestParam("id") Integer id, @RequestParam("idEntrenador") Integer idEntrenador) {
        List<RoutineHasSessionEntity> sessions = routineHasSessionRepository.findSessionsByRoutineId(id);
        if (!sessions.isEmpty()) {
            // Si hay sesiones asociadas a esta rutina, elimínalas primero
            routineHasSessionRepository.deleteAll(sessions);
        }
        this.routineRepository.deleteById(id);
        return "redirect:/home/trainer/rutina?idEntrenador=" + idEntrenador;
    }

    @GetMapping("/trainer/ver")
    public String doVer (@RequestParam("id") Integer idRutina, @RequestParam("idEntrenador") Integer idEntrenador, Model model) {
         List<RoutineHasSessionEntity> sessionRoutineEntities = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        List<SessionEntity> sessionEntities = sessionRepository.getSessionsByIdRoutine(idRutina);
        List<SessionEntity> sessionCompleteEntities = sessionRepository.getSessionsByIdEntrenador(idEntrenador);
        model.addAttribute("lista", sessionEntities);
        model.addAttribute("listaSesionRutina", sessionRoutineEntities);
        model.addAttribute("listaCompleta", sessionCompleteEntities);
        model.addAttribute("idRutina", idRutina);
        model.addAttribute("idEntrenador", idEntrenador);
        return "entrenamiento";
    }

    @PostMapping("/trainer/guardar_sesiones")
    public String doGuardarRutinas (@RequestParam("idRutina") Integer idRutina,
                                    @RequestParam("idEntrenador") Integer idEntrenador,
                                    @RequestParam Map<String, String> allParams,
                                    Model model) {
        List<RoutineHasSessionEntity> sesionesABorrar = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);

        routineHasSessionRepository.deleteAll(sesionesABorrar);

        // Guardar las nuevas sesiones
        for (int i = 1; i <= 7; i++) {
            String sessionIdParam = allParams.get("idSesion" + i);
            if (sessionIdParam != null && !sessionIdParam.equals("-1")) {
                Integer idSesion = Integer.parseInt(sessionIdParam);
                RoutineHasSessionEntity sessionRoutineEntity = new RoutineHasSessionEntity();
                sessionRoutineEntity.setDay(i);
                sessionRoutineEntity.setRoutineIdroutine(idRutina);
                sessionRoutineEntity.setSessionId(idSesion);
                sessionRoutineEntity.setRoutineByRoutineIdroutine(routineRepository.findById(idRutina).orElse(null));
                sessionRoutineEntity.setSessionBySessionId(sessionRepository.findById(idSesion).orElse(null));
                routineHasSessionRepository.save(sessionRoutineEntity);
            }
        }
        // Recargar los datos para mostrarlos nuevamente en la vista
        List<RoutineHasSessionEntity> sessionRoutineEntities = routineHasSessionRepository.findSessionsByRoutineId(idRutina);
        List<SessionEntity> sessionEntities = sessionRepository.getSessionsByIdRoutine(idRutina);
        List<SessionEntity> sessionCompleteEntities = sessionRepository.getSessionsByIdEntrenador(idEntrenador);
        model.addAttribute("lista", sessionEntities);
        model.addAttribute("listaSesionRutina", sessionRoutineEntities);
        model.addAttribute("listaCompleta", sessionCompleteEntities);
        model.addAttribute("idRutina", idRutina);
        model.addAttribute("idEntrenador", idEntrenador);
        return "entrenamiento";
    }

    @GetMapping("/trainer/crear")
    public String doNuevo (Model model, @RequestParam("idEntrenador") Integer idEntrenador) {
            RoutineEntity rutina = new RoutineEntity();
            rutina.setIdroutine(-1);
            model.addAttribute("rutina", rutina);
            model.addAttribute("idEntrenador", idEntrenador);
        return "routine";
    }


    @GetMapping("/trainer/editar")
    public String doEditar (@RequestParam("id") Integer id, Model model,  @RequestParam("idEntrenador") Integer idEntrenador) {
        RoutineEntity rutina = this.routineRepository.findById(id).orElse(null);
        model.addAttribute("rutina", rutina);
        model.addAttribute("idEntrenador", idEntrenador);
        return "routine";
    }

    @PostMapping("/trainer/guardar")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("descripcion") String descripcion,
                             @RequestParam("fecha") LocalDate fecha,
                             @RequestParam("idEntrenador") Integer idEntrenador){
        UserEntity entrenador = userRepository.findById(idEntrenador).orElse(null);
        RoutineEntity rutina = this.routineRepository.findById(id).orElse(new RoutineEntity());
        rutina.setName(nombre);
        rutina.setDescription(descripcion);
        rutina.setDate(Date.valueOf(fecha));
        rutina.setUserByIdtrainer(entrenador);
        this.routineRepository.save(rutina);

        return "redirect:/home/trainer/rutina?idEntrenador=" + idEntrenador;
    }

    @GetMapping("/cliente/rutinas")
    public String getRoutinesForClient(@RequestParam("idCliente") Integer idCliente, Model model) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(idCliente);
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("idCliente", idCliente);
        return "entrenamiento_rutina_cliente";
    }

    @GetMapping("/cliente/sesiones")
    public String getSesionesForClient(@RequestParam("idRutina") Integer idRutina, Model model) {
        List<SessionEntity> sesiones = sessionRepository.getSessionsByIdRoutine(idRutina);
        model.addAttribute("sesiones", sesiones);
        List<ExerciseEntity> ejercicios = new ArrayList<>();

        for(SessionEntity s : sesiones){
            ejercicios = exerciseRepository.getExercisesByIdSession(s.getId());

        }
        model.addAttribute("ejercicios",ejercicios);
        return "entrenamiento_sesion_cliente";
    }

    @GetMapping("/client/valorar")
    public String getValoraciones(@RequestParam("idCliente") Integer idCliente, Model model) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(idCliente);
        List<SessionEntity> sesiones = new ArrayList<>();
        List<ExerciseEntity> ejercicios = new ArrayList<>();
        for(RoutineEntity r : rutinas){
            sesiones = sessionRepository.getSessionsByIdRoutine(r.getIdroutine());
        }
        for(SessionEntity s : sesiones){
            ejercicios = exerciseRepository.getExercisesByIdSession(s.getId());
        }
        model.addAttribute("rutinas",rutinas);
        model.addAttribute("sesiones",sesiones);
        model.addAttribute("ejercicios",ejercicios);

        List<ValoracionEntity> valoraciones = new ArrayList<>();
        for(ExerciseEntity e : ejercicios){
            valoraciones = (List<ValoracionEntity>) e.getValoracionsById();
        }
        model.addAttribute("valoraciones", valoraciones);


        return "valoracion";
    }

    @PostMapping("/guardarValoracion")
    public String doGuardarValoracion(@RequestParam("estrellas") Integer estrellas,
                                      @RequestParam("idCliente") Integer idCliente,
                                      @RequestParam("idEjercicio") Integer idEjercicio){
        ValoracionEntity valoracion = valoracionRepository.getValoracionByExercise(idEjercicio);
        valoracion.setStars(estrellas);
        valoracionRepository.save(valoracion);
        return "redirect:/home/client/valorar?idCliente=" + idCliente;

    }

    @GetMapping("/cliente/ejercicio")
    public String getEjercicioCliente(@RequestParam("idEjercicio") Integer idEjercicio, Model model) {
        ExerciseEntity ejercicio = exerciseRepository.getExerciseByExerciseId(idEjercicio);
        model.addAttribute("ejercicio",ejercicio);
        return "entrenamiento_ejercicio_cliente";
    }



//    @GetMapping("/valoraciones")
//    public String getValoracionesForClient(@RequestParam("idExercise") Integer idExercise, Model model) {
//        List<ValoracionEntity> valoraciones = valoracionRepository.findByExerciseId(idExercise);
//        model.addAttribute("valoraciones", valoraciones);
//        model.addAttribute("idExercise", idExercise);
//        return "valoraciones";
//    }







}
