package com.example.gymtaw.controller;

import com.example.gymtaw.dto.*;
import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/trainer")
public class ClientController extends BaseController{

    @Autowired
    RoutineService routineService;

    @Autowired
    SessionService sessionService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    RoutineHasSessionService routineHasSessionService;

    @Autowired
    TypeService typeService;

    @Autowired
    UserService userService;

    @Autowired
    ClientExerciseService clientExerciseService;

    @Autowired
    ValoracionService valoracionService;

    @GetMapping("/clients")
    public String doListar (Model model, HttpSession session) {
        String strTo = "clients";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            User usuario = (User) session.getAttribute("usuario");
            List<User> clients = userService.listarClientesPorEntrenador(usuario.getId());
            model.addAttribute("lista", clients);
            session.removeAttribute("cliente");
        }
        return strTo;
    }

    //Redirecciones de la pestaña de valoraciones

    @GetMapping("/valoraciones_client")
    public String doValoracionesClient(@RequestParam("idCliente") Integer idCliente,
                                       Model model,
                                       HttpSession session) {
        String strTo = "valoraciones_client";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            User cliente = userService.BuscarPorId(idCliente);

            List<Exercise> ejercicios = exerciseService.findExercisesWithAReviewByIdClient(idCliente);
            List<ClientExercise> ejerciciosCliente = clientExerciseService.findClientExercisesWithAReviewByIdClient(idCliente);
            List<Valoracion> valoraciones = valoracionService.findValoracionEntitiesByIdClient(idCliente);

            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("ejerciciosCliente", ejerciciosCliente);
            model.addAttribute("valoraciones", valoraciones);
            model.addAttribute("cliente", cliente);

        }
        return strTo;
    }

    /*
    //Redirecciones de la pestaña de rutinas
    @GetMapping("/routine_client")
    public String doRoutineClient (@RequestParam("idCliente") Integer idCliente,
                                   Model model,
                                   HttpSession session) {
        String strTo = "routine_client";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

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
        }
        return strTo;
    }

    @PostMapping("/routine_client_filtrar")
    public String doRoutineClientFiltrar (@ModelAttribute("filtro") FiltroRutina filtro,
                                   Model model,
                                   HttpSession session) {
        String strTo = "routine_client";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

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
        }
        return strTo;
    }

@GetMapping("/quitar_rutina")
    public String doQuitarRutina (@RequestParam("idRutina") Integer idRutina,
                                  Model model,
                                  HttpSession session) {
        UserEntity cliente = (UserEntity) session.getAttribute("cliente");
        String strTo = "redirect:/home/trainer/routine_client?idCliente="+cliente.getId();

        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            Optional<RoutineEntity> rutinaOptional = routineRepository.findById(idRutina);
            if (rutinaOptional.isPresent()) {
                RoutineEntity rutina = rutinaOptional.get();
                rutina.setIdclient(null);
                routineRepository.save(rutina);
            }
        }

        return strTo;
    }

@PostMapping("/asignar_rutina")
    public String doAsignarRutina (@RequestParam("idRutina") int idRutina,
                                   Model model,
                                   HttpSession session) {
        UserEntity cliente = (UserEntity) session.getAttribute("cliente");
        String strTo = "redirect:/home/trainer/routine_client?idCliente="+cliente.getId();

        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            Optional<RoutineEntity> rutinaOptional = routineRepository.findById(idRutina);
            Optional<UserEntity> clienteOptional = clientRepository.findById(cliente.getId());
            if (clienteOptional.isPresent() && rutinaOptional.isPresent()) {
                RoutineEntity rutina = rutinaOptional.get();
                rutina.setIdclient(clienteOptional.get());
                routineRepository.save(rutina);
            }
        }

        return strTo;
    }

    //Redirecciones pestaña de sesiones

    @GetMapping("/session_client")
    public String doSessionClient (@RequestParam("idRutina") Integer idRutina,
                                   Model model,
                                   HttpSession session) {
        String strTo = "session_client";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            List<RoutineHasSessionEntity> sesionesDias = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
            List<SessionEntity> sesiones = sessionRepository.getSessionsByIdRoutine(idRutina);
            RoutineEntity rutina = routineRepository.findById(idRutina).orElse(null);
            model.addAttribute("listaSesionesDias", sesionesDias);
            model.addAttribute("listaSesiones", sesiones);
            model.addAttribute("idRutina", idRutina);
            model.addAttribute("nombreRutina", rutina.getName());

        }
        return strTo;
    }

    //Redirecciones pestaña de ejercicios

    @GetMapping("/exercise_client")
    public String doExerciseClient (@RequestParam("idSesion") Integer idSesion,
                                    @RequestParam("idRutina") Integer idRutina,
                                    Model model,
                                    HttpSession session) {
        String strTo = "exercise_client";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            List<ExerciseEntity> ejercicios = exerciseRepository.getExercisesByIdSession(idSesion);
            List<ExerciseEntity> ejerciciosConDatos = exerciseRepository.getExercisesByIdSessionWithData(idSesion);
            RoutineEntity rutina = routineRepository.findById(idRutina).orElse(null);
            SessionEntity sesion = sessionRepository.findById(idSesion).orElse(null);

            model.addAttribute("listaEjercicios", ejercicios);
            model.addAttribute("listaEjerciciosConDatos", ejerciciosConDatos);
            model.addAttribute("idSesion", idSesion);
            model.addAttribute("idRutina", idRutina);
            model.addAttribute("nombreRutina", rutina.getName());
            model.addAttribute("nombreSesion", sesion.getName());
        }
        return strTo;
    }

    @GetMapping("/exercise_client_info")
    public String doExerciseClientInfo (@RequestParam("idEjercicio") Integer idEjercicio,
                                        @RequestParam("idSesion") Integer idSesion,
                                        Model model,
                                        HttpSession session) {
        String strTo = "exercise_client_info";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            UserEntity cliente = (UserEntity) session.getAttribute("cliente");

            ClientExerciseEntityId clientExerciseId = new ClientExerciseEntityId();
            clientExerciseId.setExerciseId(idEjercicio);
            clientExerciseId.setUserId(cliente.getId());

            ClientExerciseEntity clientExercise = clientExerciseRepository.findById(clientExerciseId).orElse(null);
            ExerciseEntity exercise = exerciseRepository.findById(idEjercicio).orElse(null);

            model.addAttribute("ejercicioCliente", clientExercise);
            model.addAttribute("ejercicio", exercise);
            model.addAttribute("idSesion", idSesion);
        }
        return strTo;
    }

    @PostMapping("/guardar_ejercicio")
    public String doExerciseClientInfo (@RequestParam("idEjercicio") Integer idEjercicio,
                                        @RequestParam("idSesion") Integer idSesion,
                                        @RequestParam("idRutina") Integer idRutina,
                                        @RequestParam("reps") String reps,
                                        @RequestParam("sets") String sets,
                                        @RequestParam("peso") String peso,
                                        @RequestParam("calorias") Double calorias,
                                        @RequestParam("distancia") Double distancia,
                                        Model model,
                                        HttpSession session) {
        String strTo = "redirect:/home/trainer/exercise_client?idSesion="+idSesion+"&idRutina="+idRutina;
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            UserEntity cliente = (UserEntity) session.getAttribute("cliente");

            ClientExerciseEntityId clientExerciseId = new ClientExerciseEntityId();
            clientExerciseId.setExerciseId(idEjercicio);
            clientExerciseId.setUserId(cliente.getId());

            ClientExerciseEntity clientExercise = clientExerciseRepository.findById(clientExerciseId).orElse(null);

            if (clientExercise == null) {
                clientExercise = new ClientExerciseEntity();
            }

            clientExercise.setExercise(exerciseRepository.findById(idEjercicio).orElse(null));
            clientExercise.setUser(cliente);
            clientExercise.setId(clientExerciseId);
            clientExercise.setReps(reps);
            clientExercise.setSets(sets);
            clientExercise.setWeight(peso);
            clientExercise.setCalories(calorias);
            clientExercise.setDistance(distancia);

            clientExerciseRepository.save(clientExercise);

            model.addAttribute("ejercicio", clientExercise);
        }
        return strTo;
    }
     */
}
