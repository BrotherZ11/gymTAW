package com.example.gymtaw.controller;

import com.example.gymtaw.dto.*;
import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.service.*;
import com.example.gymtaw.ui.FiltroRutina;
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

    //Redirecciones de la pestaña de rutinas

    @GetMapping("/routine_client")
    public String doRoutineClient (@RequestParam("idCliente") Integer idCliente,
                                   Model model,
                                   HttpSession session) {
        String strTo = "routine_client";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            User usuario = (User) session.getAttribute("usuario");

            User cliente = userService.BuscarPorId(idCliente);
            session.setAttribute("cliente", cliente);

            List<Routine> rutinasCliente = routineService.listarRutinasPorEntrenadorYCliente(usuario.getId(), cliente.getId());
            List<Routine> rutinasSinAsignar = routineService.listarRutinasSinAsignarPorEntrenador(usuario.getId());
            List<Type> tipos = typeService.cogerTipos();

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

            User usuario = (User) session.getAttribute("usuario");
            User cliente = (User) session.getAttribute("cliente");

            List<Routine> rutinasCliente = routineService.listarRutinasFiltradasPorEntrenadorClienteNombreYTipos(usuario.getId(), cliente.getId(), filtro.getNombre(), filtro.getTipos());
            List<Routine> rutinasSinAsignar = routineService.listarRutinasSinAsignarPorEntrenador(usuario.getId());
            List<Type> tipos = typeService.cogerTipos();

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
        User cliente = (User) session.getAttribute("cliente");
        String strTo = "redirect:/home/trainer/routine_client?idCliente="+cliente.getId();

        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            routineService.quitarClienteDeRutina(idRutina);

        }

        return strTo;
    }

    @PostMapping("/asignar_rutina")
    public String doAsignarRutina (@RequestParam("idRutina") int idRutina,
                                   Model model,
                                   HttpSession session) {
        User cliente = (User) session.getAttribute("cliente");
        String strTo = "redirect:/home/trainer/routine_client?idCliente="+cliente.getId();

        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            routineService.asignarClienteEnRutina(idRutina, cliente.getId());

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

            List<RoutineHasSession> sesionesDias = routineHasSessionService.getSessionsRoutineByIdRoutine(idRutina);
            List<Session> sesiones = sessionService.listarSesionByIdRutina(idRutina);
            Routine rutina = routineService.buscarRutina(idRutina);

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

            List<Exercise> ejercicios = exerciseService.getExercisesByIdSession(idSesion);
            List<Exercise> ejerciciosConDatos = exerciseService.findExercisesWithDataByIdSesion(idSesion);
            Routine rutina = routineService.buscarRutina(idRutina);
            Session sesion = sessionService.buscarSesion(idSesion);

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
                                        @RequestParam("idRutina") Integer idRutina,
                                        Model model,
                                        HttpSession session) {
        String strTo = "exercise_client_info";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            User cliente = (User) session.getAttribute("cliente");

            ClientExercise clientExercise = clientExerciseService.findClientExerciseByIdExerciseAndIdCliente(idEjercicio, cliente.getId());
            Exercise exercise = exerciseService.findByidEjercicio(idEjercicio);

            model.addAttribute("ejercicioCliente", clientExercise);
            model.addAttribute("ejercicio", exercise);
            model.addAttribute("idSesion", idSesion);
            model.addAttribute("idRutina", idRutina);
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
                                        @RequestParam(name = "calorias", required = false) Double calorias,
                                        @RequestParam(name = "distancia", required = false) Double distancia,
                                        Model model,
                                        HttpSession session) {
        String strTo = "redirect:/home/trainer/exercise_client?idSesion="+idSesion+"&idRutina="+idRutina;
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {

            User cliente = (User) session.getAttribute("cliente");

            ClientExercise clientExercise = clientExerciseService.saveClientExercise(idEjercicio, cliente.getId(), reps, sets, peso, calorias, distancia);

            model.addAttribute("ejercicio", clientExercise);
        }
        return strTo;
    }

}
