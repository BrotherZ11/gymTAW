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

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

    @GetMapping("/clients")
    public String doListar (@RequestParam("idEntrenador") Integer idEntrenador, Model model) {
        List<UserEntity> clients = clientRepository.getClientesByIdEntrenador(idEntrenador);
        model.addAttribute("lista", clients);
        model.addAttribute("idEntrenador", idEntrenador);
        return "clients";
    }

    //Redirecciones de la pestaña de rutinas
    @GetMapping("/routine_client")
    public String doRoutineClient (@RequestParam("idEntrenador") Integer idEntrenador,
                                   @RequestParam("idCliente") Integer idCliente,
                                   Model model) {
        List<RoutineEntity> routineTotalEntities = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        List<RoutineEntity> routineEntities = routineRepository.getRoutinesbyEntrenador(idEntrenador);
        model.addAttribute("listaCompleta", routineEntities);
        model.addAttribute("lista", routineTotalEntities);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("idCliente", idCliente);
        return "routine_client";
    }

/*    @GetMapping("/quitar_rutina")
    public String doQuitarRutina (@RequestParam("idRutina") Integer idRutina,
                                  @RequestParam("idEntrenador") Integer idEntrenador,
                                  @RequestParam("idCliente") Integer idCliente,
                                  Model model) {
        TrainerRoutineEntity trainerRoutineEntity = trainerRoutineRepository.getTrainerRoutine(idEntrenador, idCliente, idRutina);
        trainerRoutineRepository.delete(trainerRoutineEntity);

        List<RoutineEntity> routineTotalEntities = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        List<RoutineEntity> routineEntities = routineRepository.getRoutinesByIdEntrenador(idEntrenador);
        model.addAttribute("listaCompleta", routineEntities);
        model.addAttribute("lista", routineTotalEntities);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("idCliente", idCliente);
        return "routine_client";
    }*/

/*    @PostMapping("/asignar_rutina")
    public String doAsignarRutina (@RequestParam("idRutina") int idRutina,
                                    @RequestParam("idEntrenador") int idEntrenador,
                                    @RequestParam("idCliente") int idCliente,
                                    Model model) {

        TrainerRoutineEntity newTrainerRoutineEntity = new TrainerRoutineEntity();
        newTrainerRoutineEntity.setRoutineIdroutine(idRutina);
        newTrainerRoutineEntity.setTrainerId(idEntrenador);
        newTrainerRoutineEntity.setUserId(idCliente);
        trainerRoutineRepository.save(newTrainerRoutineEntity);

        List<RoutineEntity> routineTotalEntities = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        List<RoutineEntity> routineEntities = routineRepository.getRoutinesByIdEntrenador(idEntrenador);
        model.addAttribute("listaCompleta", routineEntities);
        model.addAttribute("lista", routineTotalEntities);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("idCliente", idCliente);
        return "routine_client";
    }*/

    //Redirecciones pestaña de sesiones

    @GetMapping("/session_client")
    public String doSessionClient (@RequestParam("idRutina") Integer idRutina,
                                   @RequestParam("idEntrenador") Integer idEntrenador,
                                   Model model) {
        List<RoutineHasSessionEntity> sessionRoutineEntities = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        List<SessionEntity> sessionEntities = sessionRepository.getSessionsByIdRoutine(idRutina);
        List<SessionEntity> sessionCompleteEntities = sessionRepository.getSessionsByIdEntrenador(idEntrenador);
        model.addAttribute("lista", sessionEntities);
        model.addAttribute("listaSesionRutina", sessionRoutineEntities);
        model.addAttribute("listaCompleta", sessionCompleteEntities);
        model.addAttribute("idRutina", idRutina);
        model.addAttribute("idEntrenador", idEntrenador);
        return "session_client";
    }

    @PostMapping("/guardar_sesiones")
    public String doGuardarRutinas (@RequestParam("idRutina") Integer idRutina,
                                    @RequestParam("idEntrenador") Integer idEntrenador,
                                    @RequestParam("idSesion1") Integer idSesion1,
                                    @RequestParam("idSesion2") Integer idSesion2,
                                    @RequestParam("idSesion3") Integer idSesion3,
                                    @RequestParam("idSesion4") Integer idSesion4,
                                    @RequestParam("idSesion5") Integer idSesion5,
                                    @RequestParam("idSesion6") Integer idSesion6,
                                    @RequestParam("idSesion7") Integer idSesion7,
                                   Model model) {
        List<RoutineHasSessionEntity> sesionesABorrar = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);

        routineHasSessionRepository.deleteAll(sesionesABorrar);

        for(int i = 1; i <= 7; i++){
            Integer idSesion;
            switch (i) {
                case 1:
                    idSesion = idSesion1;
                    break;
                case 2:
                    idSesion = idSesion2;
                    break;
                case 3:
                    idSesion = idSesion3;
                    break;
                case 4:
                    idSesion = idSesion4;
                    break;
                case 5:
                    idSesion = idSesion5;
                    break;
                case 6:
                    idSesion = idSesion6;
                    break;
                case 7:
                    idSesion = idSesion7;
                    break;
                default:
                    idSesion = -1;
            }
            RoutineHasSessionEntity sessionRoutineEntity = new RoutineHasSessionEntity();
            if(idSesion != -1){
                sessionRoutineEntity.setDay(i);
                sessionRoutineEntity.setRoutineIdroutine(idRutina);
                sessionRoutineEntity.setSessionId(idSesion);
                routineHasSessionRepository.save(sessionRoutineEntity);
            }
        }

        List<RoutineHasSessionEntity> sessionRoutineEntities = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        List<SessionEntity> sessionEntities = sessionRepository.getSessionsByIdRoutine(idRutina);
        List<SessionEntity> sessionCompleteEntities = sessionRepository.getSessionsByIdEntrenador(idEntrenador);
        model.addAttribute("lista", sessionEntities);
        model.addAttribute("listaSesionRutina", sessionRoutineEntities);
        model.addAttribute("listaCompleta", sessionCompleteEntities);
        model.addAttribute("idRutina", idRutina);
        model.addAttribute("idEntrenador", idEntrenador);
        return "session_client";
    }

    //Redirecciones pestaña de ejercicios

    @GetMapping("/exercise_client")
    public String doExerciseClient (@RequestParam("idSesion") Integer idSesion,
                                    Model model) {
        List<ExerciseEntity> exerciseEntities = exerciseRepository.getExercisesByIdSession(idSesion);
        List<ExerciseEntity> exerciseCompleteEntities = exerciseRepository.findAll();
        model.addAttribute("lista", exerciseEntities);
        model.addAttribute("listaCompleta", exerciseCompleteEntities);
        model.addAttribute("idSesion", idSesion);
        return "exercise_client";
    }

    /*
    @GetMapping("/quitar_rutina")
    public String dob (@RequestParam("idSesion") Integer idSesion,
                       Model model) {
        TrainerRoutineEntity trainerRoutineEntity = trainerRoutineRepository.getTrainerRoutine(idEntrenador, idCliente, idRutina);
        trainerRoutineRepository.delete(trainerRoutineEntity);

        List<ExerciseEntity> exerciseEntities = exerciseRepository.getExercisesByIdSession(idSesion);
        List<ExerciseEntity> exerciseCompleteEntities = exerciseRepository.findAll();
        model.addAttribute("lista", exerciseEntities);
        model.addAttribute("listaCompleta", exerciseCompleteEntities);
        model.addAttribute("idSesion", idSesion);
        return "exercise_client";
    }

    @PostMapping("/asignar_rutina")
    public String doa (@RequestParam("idSesion") Integer idSesion,
                       Model model) {

        TrainerRoutineEntity newTrainerRoutineEntity = new TrainerRoutineEntity();
        newTrainerRoutineEntity.setRoutineIdroutine(idRutina);
        newTrainerRoutineEntity.setTrainerId(idEntrenador);
        newTrainerRoutineEntity.setUserId(idCliente);
        trainerRoutineRepository.save(newTrainerRoutineEntity);

        List<ExerciseEntity> exerciseEntities = exerciseRepository.getExercisesByIdSession(idSesion);
        List<ExerciseEntity> exerciseCompleteEntities = exerciseRepository.findAll();
        model.addAttribute("lista", exerciseEntities);
        model.addAttribute("listaCompleta", exerciseCompleteEntities);
        model.addAttribute("idSesion", idSesion);
        return "exercise_client";
    }

    */


}
