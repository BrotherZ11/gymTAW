package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.*;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.service.ExerciseService;
import com.example.gymtaw.service.RoutineHasSessionService;
import com.example.gymtaw.service.RoutineService;
import com.example.gymtaw.service.SessionService;
import com.example.gymtaw.ui.FiltroEjercicio;
import com.example.gymtaw.ui.FiltroValoracion;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/cliente")
public class ClienteController extends BaseController{

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineService routineService;

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

    @Autowired
    RoutineHasSessionService routineHasSessionService;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    ExerciseHasSessionRepository exerciseHasSessionRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValoracionRepository valoracionRepository;

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

            List<Session> sesiones = sessionService.buscarSesionesByIdSesion(idSesion);
            model.addAttribute("sesiones", sesiones);

            List<Exercise> ejercicios = exerciseService.getExercisesByIdSession(idSesion);

            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("idSesion", idSesion);
            model.addAttribute("idRutina", idRutina);

        }
        return strTo;
    }

//    @PostMapping("/guardarCompletado")
//    public String guardarComplecion(@RequestParam("idRutina") Integer idRutina,
//                                    @RequestParam("idSesion") Integer idSesion,
//                                    @RequestParam("idEjercicio") Integer idEjercicio,
//                                    @RequestParam(value = "done", required = false) String done,
//                                    Model model, HttpSession session) {
//
//        String strTo="redirect:/home/cliente/ejercicio?idSesion=" + idSesion + "&idRutina=" + idRutina ;
//        if(!estaAutenticado(session)){
//            strTo = "redirect:/";
//        }
//        if(idSesion==-1){
//            strTo="redirect:/home/cliente/valorar";
//        }
//        ExerciseEntity exercise = exerciseRepository.findById(idEjercicio).orElse(new ExerciseEntity());
//        UserEntity usuario = (UserEntity) session.getAttribute("usuario");
//        // Busca o crea la valoracion para el ejercicio
//        List<ValoracionEntity> val = exercise.getValoracions();
//        boolean valoracionExists = false;
//        if (val != null) {
//            for (ValoracionEntity v : val) {
//                if (v.getUser().getId().equals(usuario.getId())) {
//                    if ("1".equals(done)) {
//                        v.setDone((byte) 1); // Marca como completado
//                    } else {
//                        v.setDone((byte) 0); // Marca como NO completado
//                    }
//                    valoracionRepository.save(v);
//                    valoracionExists = true;
//                    break;
//                }
//            }
//        }
//
//        if (!valoracionExists && "1".equals(done)) {
//            ValoracionEntity newValoracion = new ValoracionEntity();
//            ValoracionEntityId valoracionId = new ValoracionEntityId();
//            valoracionId.setUserId(usuario.getId());
//            valoracionId.setExerciseId(idEjercicio);
//            newValoracion.setId(valoracionId);
//            newValoracion.setUser(userRepository.findById(usuario.getId()).orElse(new UserEntity()));
//            newValoracion.setExercise(exercise);
//            newValoracion.setDone((byte) 1);
//            valoracionRepository.save(newValoracion);
//        }
//
//        return strTo;
//    }
//
//
//
//    @GetMapping("/ejercicioIndividual")
//    public String getEjercicioCliente(@RequestParam("idRutina") Integer idRutina,
//                                      @RequestParam("idSesion") Integer idSesion,
//                                      @RequestParam("idEjercicio") Integer idEjercicio
//
//                                        , Model model, HttpSession session) {
//        String strTo = "entrenamiento_ejercicio_cliente";
//        if(!estaAutenticado(session)){
//            strTo = "redirect:/";
//        } else{
//            ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
//            model.addAttribute("ejercicio", ejercicio);
//            List<ClientExerciseEntity> clientExercise = clientExerciseRepository.getClientExercisesByIdEjercicio(idEjercicio);
//            model.addAttribute("clientExercise", clientExercise);
//            model.addAttribute("idRutina", idRutina);
//            model.addAttribute("idSesion", idSesion);
//
//        }
//        return strTo;
//    }
//
//    @GetMapping("/valorarEjercicio")
//    public String valorarDesdeEjercicio(@RequestParam("idRutina") Integer idRutina,
//                                        @RequestParam("idEjercicio") Integer idEjercicio,
//                                        @RequestParam("idSesion") Integer idSesion,
//                                        Model model, HttpSession session) {
//        String strTo = "valorarUnEjercicio";
//        if(!estaAutenticado(session)){
//            strTo = "redirect:/";
//        } else{
//            ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
//            model.addAttribute("ejercicio", ejercicio);
//            model.addAttribute("idSesion", idSesion);
//            model.addAttribute("idRutina", idRutina);
//        }
//
//        return strTo;
//    }
//
//    @PostMapping("/guardar")
//    public String guardarValoracion(@RequestParam("stars") Integer stars,
//                                    @RequestParam("exerciseId") Integer exerciseId,
//                                    @RequestParam("idSesion") Integer idSesion,
//                                    @RequestParam("idRutina") Integer idRutina,
//                                    Model model, HttpSession session) {
//
//        String strTo="redirect:/home/cliente/ejercicio?idSesion=" + idSesion + "&idRutina=" + idRutina;
//        if(!estaAutenticado(session)){
//            strTo="redirect:/";
//        }
//        if(idSesion==-1){
//            strTo="redirect:/home/cliente/valorar";
//        }
//        UserEntity usuario = (UserEntity) session.getAttribute("usuario");
//
//        ExerciseEntity exercise = exerciseRepository.findById(exerciseId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + exerciseId));
//
//        // Find or create the valoracion entity for this exercise
//        List<ValoracionEntity> val = exercise.getValoracions();
//        boolean valoracionExists = false;
//        if (val != null) {
//            for (ValoracionEntity v : val) {
//                if (v.getUser().getId().equals(usuario.getId())) {
//                    v.setStars(stars);  // Set the rating
//                    valoracionRepository.save(v);
//                    valoracionExists = true;
//                    break;
//                }
//            }
//        }
//
//        if (!valoracionExists) {
//            ValoracionEntity nuevaValoracion = new ValoracionEntity();
//            ValoracionEntityId valoracionEntityId = new ValoracionEntityId();
//            valoracionEntityId.setUserId(usuario.getId());
//            valoracionEntityId.setExerciseId(exerciseId);
//
//            nuevaValoracion.setId(valoracionEntityId);
//            nuevaValoracion.setUser(usuario);
//            nuevaValoracion.setExercise(exercise);
//            nuevaValoracion.setStars(stars);
//            nuevaValoracion.setDone((byte) 1); // Marking the rating as done
//
//            valoracionRepository.save(nuevaValoracion);
//        }
//
//
//        return strTo;
//    }
//
//    @GetMapping("/valorar")
//    public String todasValoraciones(
//                                    Model model, HttpSession session){
//        String strTo = "valoracion";
//        if(!estaAutenticado(session)){
//            strTo = "redirect:/";
//        } else{
//            UserEntity usuario = (UserEntity) session.getAttribute("usuario");
//            List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(usuario.getId());
//            model.addAttribute("rutinas", rutinas);
//
//
//            List<Integer> exerciseIntegersByClientId = this.clientExerciseRepository.findExerciseIdByClientId(usuario.getId());
//
//            ExerciseEntity ejercicio = new ExerciseEntity();
//            List<ExerciseEntity> ejercicios = new ArrayList<>();
//            for(Integer id : exerciseIntegersByClientId){
//                ejercicio = this.exerciseRepository.getExercisesByIdEjercicio(id);
//                ejercicios.add(ejercicio);
//            }
//            model.addAttribute("ejercicios", ejercicios);
//            Integer idSesion=-1;
//            Integer idRutina=-1;
//            model.addAttribute("idSesion", idSesion);
//            model.addAttribute("idRutina", idRutina);
//            model.addAttribute("filtro", new FiltroValoracion());
//            model.addAttribute("filtroEj", new FiltroEjercicio());
//        }
//        return strTo;
//    }
//
//    @PostMapping("/filtrarValoraciones")
//    public String filtrarValoraciones(@ModelAttribute("filtro") FiltroValoracion filtro,
//                                      Model model, HttpSession session) {
//        String strTo = "valoracion";
//        if(!estaAutenticado(session)){
//            strTo = "redirect:/";
//        } else{
//            UserEntity usuario = (UserEntity) session.getAttribute("usuario");
//            List<Integer> exerciseIds = clientExerciseRepository.findExerciseIdByClientId(usuario.getId());
//            List<ExerciseEntity> ejercicios = new ArrayList<>();
//
//            // Iteramos por cada ejercicio para buscar el que tenga la valoracion que buscamos.
//            for (Integer exerciseId : exerciseIds) {
//                ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(exerciseId);
//                if (ejercicio != null) {
//                    List<ValoracionEntity> valoraciones = ejercicio.getValoracions();
//                    boolean addEjercicioALista = true;
//
//                    // Vemos si el ejercicio tiene la misma valoracion que el filtro
//                    if (filtro.getStars() != null && filtro.getStars() > 0) {
//                        addEjercicioALista = false; // Empieza asumiendo que no tiene la valoracion buscada
//
//                        for (ValoracionEntity valoracion : valoraciones) {
//                            if (valoracion.getUser().getId().equals(usuario.getId()) && valoracion.getDone() == 1) {
//                                if (valoracion.getStars() != null && valoracion.getStars() == filtro.getStars()) {
//                                    addEjercicioALista = true; // Ha encontrado la valoracion
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    if (addEjercicioALista) {
//                        ejercicios.add(ejercicio);
//                    }
//                }
//            }
//            model.addAttribute("ejercicios", ejercicios);
//            model.addAttribute("filtro", filtro);
//            model.addAttribute("filtroEj", new FiltroEjercicio());
//        }
//        return strTo;
//    }
//
//    @PostMapping("/filtrarEjercicio")
//    public String filtrarEjercicio(@ModelAttribute("filtroEj") FiltroEjercicio filtroEj,
//                                   Model model, HttpSession session) {
//        String strTo = "valoracion";
//        if(!estaAutenticado(session)){
//            strTo = "redirect:/";
//        } else{
//
//            List<ExerciseEntity> ejercicios = new ArrayList<>();
//            if (filtroEj.getNombre() != null && !filtroEj.getNombre().isEmpty()) {
//                ejercicios = exerciseRepository.findExercisesByName(filtroEj.getNombre());
//            }
//            model.addAttribute("ejercicios", ejercicios);
//
//            model.addAttribute("filtro", new FiltroValoracion());
//            model.addAttribute("filtroEj", filtroEj);
//        }
//        return strTo;
//    }
//
//
//    @PostMapping("/guardarReview")
//    public String guardarReview(@RequestParam("exerciseId") Integer exerciseId,
//                                @RequestParam("review") String review,
//                                Model model, HttpSession session) {
//        String strTo = "redirect:/home/cliente/valorar";
//        if(!estaAutenticado(session)){
//            strTo = "redirect:/";
//        } else{
//            UserEntity usuario = (UserEntity) session.getAttribute("usuario");
//            ValoracionEntityId valoracionId = new ValoracionEntityId();
//            valoracionId.setUserId(usuario.getId());
//            valoracionId.setExerciseId(exerciseId);
//
//            Optional<ValoracionEntity> valoracionEntity = this.valoracionRepository.findById(valoracionId);
//            if (valoracionEntity.isPresent()) {
//                ValoracionEntity valoracion = valoracionEntity.get();
//                valoracion.setReview(review);
//                valoracionRepository.save(valoracion);
//            }
//        }
//
//        return strTo;
//    }



}
