package com.example.gymtaw.controller;

//Marta Granado Rodríguez 100%

import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.*;
import com.example.gymtaw.service.*;
import com.example.gymtaw.ui.FiltroEjercicio;
import com.example.gymtaw.ui.FiltroValoracion;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/cliente")
public class ClienteController extends BaseController{

    @Autowired
    RoutineService routineService;

    @Autowired
    RoutineHasSessionService routineHasSessionService;

    @Autowired
    SessionService sessionService;


    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ValoracionService valoracionService;

    @Autowired
    private ClientExerciseService clientExerciseService;
    @Autowired
    private UserService userService;

    @GetMapping("/entrenamientos")
    public String doListar(Model model, HttpSession session) {
        String strTo = "entrenamiento_rutina_cliente";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");

            List<Routine> rutinas = routineService.listarRutinasCliente(usuario.getId());
            model.addAttribute("rutinas", rutinas);

        }
        return strTo;
    }

    @GetMapping("/sesionesSemanales")
    public String getSesionesSemanales(@RequestParam("idRutina") Integer idRutina,
                                       Model model, HttpSession session) {
        String strTo = "cliente_sesiones_semanales";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");

            List<Session> sesiones = sessionService.listarSesionByIdRutina(idRutina);
            model.addAttribute("sesiones", sesiones);

            List<RoutineHasSession> sesionRutina = routineHasSessionService.getSessionsRoutineByIdRoutine(idRutina);
            model.addAttribute("sesionRutina", sesionRutina);

            model.addAttribute("idCliente", usuario.getId());
            model.addAttribute("idRutina", idRutina);
        }
        return strTo;
    }

    @GetMapping("/ejercicio")
    public String getSesion(@RequestParam("idRutina") Integer idRutina,
                            @RequestParam("idSesion") Integer idSesion,
                            Model model, HttpSession session) {
        String strTo = "entrenamiento_sesion_cliente";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");
            List<Session> sesiones = sessionService.buscarSesionesByIdSesion(idSesion);
            model.addAttribute("sesiones", sesiones);

            List<ClientExercise> clientExercises = clientExerciseService.findClientExerciseByIdIdCliente(usuario.getId());
            List<Exercise> ejercicios = exerciseService.getExercisesByIdSession(idSesion);

            List<Valoracion> valoraciones = valoracionService.getValoracionesByUsuario(usuario);

            model.addAttribute("clientExercises", clientExercises);
            model.addAttribute("valoraciones", valoraciones);
            model.addAttribute("usuario",usuario);
            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("idSesion", idSesion);
            model.addAttribute("idRutina", idRutina);

        }
        return strTo;
    }

    @PostMapping("/guardarCompletado")
    public String guardarComplecion(@RequestParam("idRutina") Integer idRutina,
                                    @RequestParam("idSesion") Integer idSesion,
                                    @RequestParam("idEjercicio") Integer idEjercicio,
                                    @RequestParam(value = "done", required = false) Integer done,
                                    Model model, HttpSession session) {

        String strTo="redirect:/home/cliente/ejercicio?idSesion=" + idSesion + "&idRutina=" + idRutina ;
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        }
        if(idSesion==-1){
            strTo="redirect:/home/cliente/valorar";
        }


        Exercise exercise = exerciseService.findByidEjercicio(idEjercicio);

        User usuario = (User) session.getAttribute("usuario");
//        valoracionService.updateCompletionStatus(idEjercicio, usuario, done);

       valoracionService.buscarOCrearValoracion(exercise, usuario, String.valueOf(done), idEjercicio);

        // Refresh data after updating
        List<Session> sesiones = sessionService.listarSesionByIdRutina(idRutina);
        List<Exercise> ejercicios = exerciseService.getExercisesByIdSession(idSesion);
        List<Valoracion> valoraciones = valoracionService.getValoracionesByUsuario(usuario);

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("valoraciones", valoraciones);


        return strTo;
    }



    @GetMapping("/ejercicioIndividual")
    public String getEjercicioCliente(@RequestParam("idRutina") Integer idRutina,
                                      @RequestParam("idSesion") Integer idSesion,
                                      @RequestParam("idEjercicio") Integer idEjercicio,
                                      @RequestParam("idTrainer") Integer idTrainer,
                                      Model model, HttpSession session) {
        String strTo = "entrenamiento_ejercicio_cliente";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");
            Exercise ejercicio = exerciseService.findByidEjercicio(idEjercicio);
            model.addAttribute("ejercicio", ejercicio);
            ClientExercise clientExercise = clientExerciseService.findClientExerciseByIdExerciseIdClienteAndIdEntrenador(idEjercicio, usuario.getId(), idTrainer);
            model.addAttribute("clientExercise", clientExercise);
            model.addAttribute("idRutina", idRutina);
            model.addAttribute("idSesion", idSesion);

        }
        return strTo;
    }

    @GetMapping("/valorarEjercicio")
    public String valorarDesdeEjercicio(@RequestParam("idRutina") Integer idRutina,
                                        @RequestParam("idEjercicio") Integer idEjercicio,
                                        @RequestParam("idSesion") Integer idSesion,
                                        Model model, HttpSession session) {
        String strTo = "valorarUnEjercicio";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            Exercise ejercicio = exerciseService.findByidEjercicio(idEjercicio);
            model.addAttribute("ejercicio", ejercicio);
            model.addAttribute("idSesion", idSesion);
            model.addAttribute("idRutina", idRutina);
        }

        return strTo;
    }

    @PostMapping("/guardar")
    public String guardarValoracion(@RequestParam("stars") Integer stars,
                                    @RequestParam("exerciseId") Integer exerciseId,
                                    @RequestParam("idSesion") Integer idSesion,
                                    @RequestParam("idRutina") Integer idRutina,
                                    Model model, HttpSession session) {

        String strTo="redirect:/home/cliente/ejercicio?idSesion=" + idSesion + "&idRutina=" + idRutina;
        if(!estaAutenticado(session)){
            strTo="redirect:/";
        }
        if(idSesion==-1){
            strTo="redirect:/home/cliente/valorar";
        }
        User usuario = (User) session.getAttribute("usuario");

        Exercise exercise = exerciseService.findByidEjercicio(exerciseId);

        valoracionService.buscarOCrearValoracionSinDone(exercise, usuario, exerciseId,stars);

        return strTo;
    }

    @GetMapping("/valorar")
    public String todasValoraciones(
                                    Model model, HttpSession session){
        String strTo = "valoracion";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");
            List<Routine> rutinas = routineService.listarRutinasCliente(usuario.getId());
            model.addAttribute("rutinas", rutinas);

            List<User> entrenadores =  userService.listarEntrenadoresAsignados(usuario.getId());

            List<Exercise> ejercicios = exerciseService.findAllExercisesByUsuarioId(usuario.getId());
            List<Valoracion> valoraciones = valoracionService.getValoracionesByUsuario(usuario);

            model.addAttribute("entrenadores", entrenadores);
            model.addAttribute("valoraciones", valoraciones);
            model.addAttribute("ejercicios", ejercicios);
            Integer idSesion=-1;
            Integer idRutina=-1;
            model.addAttribute("idSesion", idSesion);
            model.addAttribute("idRutina", idRutina);
            model.addAttribute("filtro", new FiltroValoracion());
            model.addAttribute("filtroEj", new FiltroEjercicio());
        }
        return strTo;
    }

    @PostMapping("/filtrarValoraciones")
    public String filtrarValoraciones(@ModelAttribute("filtro") FiltroValoracion filtro,
                                      Model model, HttpSession session) {
        String strTo = "valoracion";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");

            List<Exercise> ejercicios = exerciseService.filtrarValoraciones(usuario.getId(), filtro.getStars());

            List<User> entrenadores =  userService.listarEntrenadoresAsignados(usuario.getId());

            List<Valoracion> valoraciones = valoracionService.getValoracionesByUsuario(usuario);

            model.addAttribute("entrenadores", entrenadores);
            model.addAttribute("valoraciones", valoraciones);
            model.addAttribute("idSesion", -1);
            model.addAttribute("idRutina", -1);
            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("filtro", filtro);
            model.addAttribute("filtroEj", new FiltroEjercicio());
        }
        return strTo;
    }

    @PostMapping("/filtrarEjercicio")
    public String filtrarEjercicio(@ModelAttribute("filtroEj") FiltroEjercicio filtroEj,
                                   Model model, HttpSession session) {
        String strTo = "valoracion";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");

            List<Exercise> ejercicios = exerciseService.filtrarEjercicios(filtroEj);
            List<Valoracion> valoraciones = valoracionService.getValoracionesByUsuario(usuario);
            List<User> entrenadores =  userService.listarEntrenadoresAsignados(usuario.getId());
            model.addAttribute("entrenadores", entrenadores);
            model.addAttribute("valoraciones", valoraciones);
            model.addAttribute("idSesion", -1);
            model.addAttribute("idRutina", -1);
            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("filtro", new FiltroValoracion());
            model.addAttribute("filtroEj", filtroEj);
        }
        return strTo;
    }


    @PostMapping("/guardarReview")
    public String guardarReview(@RequestParam("exerciseId") Integer exerciseId,
                                @RequestParam("review") String review,
                                Model model, HttpSession session) {
        String strTo = "redirect:/home/cliente/valorar";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else{
            User usuario = (User) session.getAttribute("usuario");

            valoracionService.guardarReview(usuario, exerciseId, review);


        }

        return strTo;
    }



}
