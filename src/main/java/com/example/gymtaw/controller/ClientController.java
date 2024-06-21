package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.ui.Filtro;
import com.example.gymtaw.ui.FiltroRutina;
import com.example.gymtaw.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    @GetMapping("/clients")
    public String doListar (Model model, HttpSession session) {
        UserEntity usuario = (UserEntity) session.getAttribute("usuario");
        List<UserEntity> clients = clientRepository.getClientesByEntrenador(usuario.getId());
        model.addAttribute("lista", clients);
        session.removeAttribute("cliente");
        return "clients";
    }

    //Redirecciones de la pestaña de rutinas
    @GetMapping("/routine_client")
    public String doRoutineClient (@RequestParam("idCliente") Integer idCliente,
                                   Model model,
                                   HttpSession session) {

        UserEntity usuario = (UserEntity) session.getAttribute("usuario");

        UserEntity cliente = userRepository.findById(idCliente).orElse(null);
        session.setAttribute("cliente", cliente);

        List<RoutineEntity> rutinasCliente = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(usuario.getId(), cliente.getId());
        List<RoutineEntity> rutinasSinAsignar = routineRepository.getRoutinesByIdEntrenadorNoCliente(usuario.getId());
        List<TypeEntity> tipos = typeRepository.findAll();

        model.addAttribute("listaRutinasCliente", rutinasCliente);
        model.addAttribute("listaRutinasSinAsignar", rutinasSinAsignar);
        model.addAttribute("tipos", tipos);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("filtro", new FiltroRutina());
        return "routine_client";
    }

    @PostMapping("/routine_client_filtrar")
    public String doRoutineClientFiltrar (@ModelAttribute("filtro") FiltroRutina filtro,
                                   Model model,
                                   HttpSession session) {

        UserEntity usuario = (UserEntity) session.getAttribute("usuario");
        UserEntity cliente = (UserEntity) session.getAttribute("cliente");

        List<RoutineEntity> rutinasCliente = routineRepository.getRoutinesByIdEntrenadorAndIdClienteFiltroNombreYTipos(usuario.getId(), cliente.getId(), filtro.getNombre(), filtro.getTipos());
        List<RoutineEntity> rutinasSinAsignar = routineRepository.getRoutinesByIdEntrenadorNoCliente(usuario.getId());
        List<TypeEntity> tipos = typeRepository.findAll();

        model.addAttribute("listaRutinasCliente", rutinasCliente);
        model.addAttribute("listaRutinasSinAsignar", rutinasSinAsignar);
        model.addAttribute("tipos", tipos);
        model.addAttribute("idCliente", cliente.getId());
        model.addAttribute("filtro", filtro);
        return "routine_client";
    }

@GetMapping("/quitar_rutina")
    public String doQuitarRutina (@RequestParam("idRutina") Integer idRutina,
                                  Model model,
                                  HttpSession session) {

        UserEntity cliente = (UserEntity) session.getAttribute("cliente");

        Optional<RoutineEntity> rutinaOptional = routineRepository.findById(idRutina);
        if(rutinaOptional.isPresent()){
            RoutineEntity rutina = rutinaOptional.get();
            rutina.setIdclient(null);
            routineRepository.save(rutina);
        }

        return "redirect:/home/trainer/routine_client?idCliente="+cliente.getId();
    }

@PostMapping("/asignar_rutina")
    public String doAsignarRutina (@RequestParam("idRutina") int idRutina,
                                   Model model,
                                   HttpSession session) {

        UserEntity cliente = (UserEntity) session.getAttribute("cliente");

        Optional<RoutineEntity> rutinaOptional = routineRepository.findById(idRutina);
        Optional<UserEntity> clienteOptional = clientRepository.findById(cliente.getId());
        if(clienteOptional.isPresent() && rutinaOptional.isPresent()){
            RoutineEntity rutina = rutinaOptional.get();
            rutina.setIdclient(clienteOptional.get());
            routineRepository.save(rutina);
        }

        return "redirect:/home/trainer/routine_client?idCliente="+cliente.getId();
    }

    //Redirecciones pestaña de sesiones

    @GetMapping("/session_client")
    public String doSessionClient (@RequestParam("idRutina") Integer idRutina,
                                   Model model,
                                   HttpSession session) {
        List<RoutineHasSessionEntity> sesionesDias = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        List<SessionEntity> sesiones = sessionRepository.getSessionsByIdRoutine(idRutina);
        model.addAttribute("listaSesionesDias", sesionesDias);
        model.addAttribute("listaSesiones", sesiones);
        return "session_client";
    }

    //Redirecciones pestaña de ejercicios

    @GetMapping("/exercise_client")
    public String doExerciseClient (@RequestParam("idSesion") Integer idSesion,
                                    Model model,
                                    HttpSession session) {
        List<ExerciseEntity> ejercicios = exerciseRepository.getExercisesByIdSession(idSesion);
        List<ExerciseEntity> ejerciciosConDatos = exerciseRepository.getExercisesByIdSessionWithData(idSesion);

        model.addAttribute("listaEjercicios", ejercicios);
        model.addAttribute("listaEjerciciosConDatos", ejerciciosConDatos);
        model.addAttribute("idSesion", idSesion);
        return "exercise_client";
    }

    @GetMapping("/exercise_client_info")
    public String doExerciseClientInfo (@RequestParam("idEjercicio") Integer idEjercicio,
                                    Model model,
                                    HttpSession session) {

        UserEntity cliente = (UserEntity) session.getAttribute("cliente");

        ClientExerciseEntityId clientExerciseId = new ClientExerciseEntityId();
        clientExerciseId.setExerciseId(idEjercicio);
        clientExerciseId.setUserId(cliente.getId());

        ClientExerciseEntity clientExercise = clientExerciseRepository.findById(clientExerciseId).orElse(null);

        model.addAttribute("ejercicio", clientExercise);
        return "exercise_client_info";
    }
}
