package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.ui.FiltroRutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/clients")
    public String doListar (@RequestParam("idEntrenador") Integer idEntrenador, Model model) {
        List<UserEntity> clients = clientRepository.getClientesByEntrenador(idEntrenador);
        model.addAttribute("lista", clients);
        model.addAttribute("idEntrenador", idEntrenador);
        return "clients";
    }

    //Redirecciones de la pestaña de rutinas
    @GetMapping("/routine_client")
    public String doRoutineClient (@RequestParam("idEntrenador") Integer idEntrenador,
                                   @RequestParam("idCliente") Integer idCliente,
                                   Model model) {
        List<RoutineEntity> rutinasCliente = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        List<RoutineEntity> rutinasSinAsignar = routineRepository.getRoutinesByIdEntrenadorNoCliente(idEntrenador);
        List<TypeEntity> tipos = typeRepository.findAll();

        model.addAttribute("listaRutinasCliente", rutinasCliente);
        model.addAttribute("listaRutinasSinAsignar", rutinasSinAsignar);
        model.addAttribute("tipos", tipos);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("filtro", new FiltroRutina());
        return "routine_client";
    }

@GetMapping("/quitar_rutina")
    public String doQuitarRutina (@RequestParam("idRutina") Integer idRutina,
                                  @RequestParam("idEntrenador") Integer idEntrenador,
                                  @RequestParam("idCliente") Integer idCliente,
                                  @ModelAttribute("filtro")FiltroRutina filtro,
                                  Model model) {

        Optional<RoutineEntity> rutinaOptional = routineRepository.findById(idRutina);
        if(rutinaOptional.isPresent()){
            RoutineEntity rutina = rutinaOptional.get();
            rutina.setIdclient(null);
            routineRepository.save(rutina);
        }

        //if(filtro.estaVacioTipos()) rutinas = routineRepository.findByFiltro(filtro.getNombre());
        //else rutinas = routineRepository.findByFiltroNombreYTipo(filtro.getNombre(), filtro.getTipos());

        List<RoutineEntity> rutinasCliente = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        List<RoutineEntity> rutinasSinAsignar = routineRepository.getRoutinesByIdEntrenadorNoCliente(idEntrenador);
        List<TypeEntity> tipos = typeRepository.findAll();

        model.addAttribute("listaRutinasCliente", rutinasCliente);
        model.addAttribute("listaRutinasSinAsignar", rutinasSinAsignar);
        model.addAttribute("tipos", tipos);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("filtro", filtro);

        return "routine_client";
    }

@PostMapping("/asignar_rutina")
    public String doAsignarRutina (@RequestParam("idRutina") int idRutina,
                                   @RequestParam("idEntrenador") int idEntrenador,
                                   @RequestParam("idCliente") int idCliente,
                                   @ModelAttribute("filtro")FiltroRutina filtro,
                                   Model model) {

        Optional<RoutineEntity> rutinaOptional = routineRepository.findById(idRutina);
        Optional<UserEntity> clienteOptional = clientRepository.findById(idCliente);
        if(clienteOptional.isPresent() && rutinaOptional.isPresent()){
            RoutineEntity rutina = rutinaOptional.get();
            rutina.setIdclient(clienteOptional.get());
            routineRepository.save(rutina);
        }


        List<RoutineEntity> rutinasCliente = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        List<RoutineEntity> rutinasSinAsignar = routineRepository.getRoutinesByIdEntrenadorNoCliente(idEntrenador);
        List<TypeEntity> tipos = typeRepository.findAll();

        model.addAttribute("listaRutinasCliente", rutinasCliente);
        model.addAttribute("listaRutinasSinAsignar", rutinasSinAsignar);
        model.addAttribute("tipos", tipos);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("filtro", filtro);

        return "routine_client";
    }

    //Redirecciones pestaña de sesiones

    @GetMapping("/session_client")
    public String doSessionClient (@RequestParam("idRutina") Integer idRutina,
                                   @RequestParam("idEntrenador") Integer idEntrenador,
                                   Model model) {
        List<RoutineHasSessionEntity> sesionesDias = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        List<SessionEntity> sesiones = sessionRepository.getSessionsByIdRoutine(idRutina);
        model.addAttribute("listaSesionesDias", sesionesDias);
        model.addAttribute("listaSesiones", sesiones);
        model.addAttribute("idEntrenador", idEntrenador);
        return "session_client";
    }

    //Redirecciones pestaña de ejercicios

    @GetMapping("/exercise_client")
    public String doExerciseClient (@RequestParam("idSesion") Integer idSesion,
                                    Model model) {
        List<ExerciseEntity> ejercicios = exerciseRepository.getExercisesByIdSession(idSesion);
        model.addAttribute("listaEjercicios", ejercicios);
        model.addAttribute("idSesion", idSesion);
        return "exercise_client";
    }
}
