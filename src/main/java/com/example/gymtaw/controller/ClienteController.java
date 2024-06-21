package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.ui.FiltroValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/cliente")
public class ClienteController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ExerciseHasSessionRepository exerciseHasSessionRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValoracionRepository valoracionRepository;

    @GetMapping("/entrenamientos")
    public String doListar(@RequestParam("idCliente") Integer idCliente,
                           Model model) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(idCliente);
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("idCliente", idCliente);
        return "entrenamiento_rutina_cliente";
    }

    @GetMapping("/sesionesSemanales")
    public String getSesionesSemanales(@RequestParam("idCliente") Integer idCliente,
                                       @RequestParam("idRutina") Integer idRutina,
                                       Model model) {
        List<SessionEntity> sesiones = sessionRepository.getSessionsByIdRoutine(idRutina);
        model.addAttribute("sesiones", sesiones);
        List<RoutineHasSessionEntity> sesionRutina = routineHasSessionRepository.findSessionsByRoutineId(idRutina);
        model.addAttribute("sesionRutina", sesionRutina);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("idRutina", idRutina);
        return "cliente_sesiones_semanales";
    }

    @GetMapping("/ejercicio")
    public String getSesion(@RequestParam("idRutina") Integer idRutina,
                            @RequestParam("idSesion") Integer idSesion,
                            @RequestParam("idCliente") Integer idCliente,
                            Model model) {
        List<SessionEntity> sesiones = sessionRepository.findSessionBySessionId(idSesion);
        model.addAttribute("sesiones", sesiones);
        List<ExerciseEntity> ejercicios = exerciseHasSessionRepository.getExercisesByIdSession(idSesion);
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("idSesion", idSesion);
        model.addAttribute("idRutina", idRutina);
        model.addAttribute("idCliente", idCliente);

        return "entrenamiento_sesion_cliente";
    }

    @PostMapping("/guardarCompletado")
    public String guardarComplecion(@RequestParam("idRutina") Integer idRutina,
                                    @RequestParam("idSesion") Integer idSesion,
                                    @RequestParam("idEjercicio") Integer idEjercicio,
                                    @RequestParam("idCliente") Integer idCliente,
                                    @RequestParam(value = "done", required = false) String done,
                                    Model model) {

        String strTo="redirect:/home/cliente/ejercicio?idSesion=" + idSesion + "&idRutina=" + idRutina + "&idCliente=" + idCliente;
        if(idSesion==-1){
            strTo="redirect:/home/cliente/valorar?idCliente=" + idCliente;
        }

        ExerciseEntity exercise = exerciseRepository.findById(idEjercicio).orElse(new ExerciseEntity());

        // Busca o crea la valoracion para el ejercicio
        List<ValoracionEntity> val = exercise.getValoracions();
        boolean valoracionExists = false;
        if (val != null) {
            for (ValoracionEntity v : val) {
                if (v.getUser().getId().equals(idCliente)) {
                    if ("1".equals(done)) {
                        v.setDone((byte) 1); // Marca como completado
                    } else {
                        v.setDone((byte) 0); // Marca como NO completado
                    }
                    valoracionRepository.save(v);
                    valoracionExists = true;
                    break;
                }
            }
        }

        if (!valoracionExists && "1".equals(done)) {
            ValoracionEntity newValoracion = new ValoracionEntity();
            ValoracionEntityId valoracionId = new ValoracionEntityId();
            valoracionId.setUserId(idCliente);
            valoracionId.setExerciseId(idEjercicio);
            newValoracion.setId(valoracionId);
            newValoracion.setUser(userRepository.findById(idCliente).orElse(new UserEntity()));
            newValoracion.setExercise(exercise);
            newValoracion.setDone((byte) 1);
            valoracionRepository.save(newValoracion);
        }

        return strTo;
    }



    @GetMapping("/ejercicioIndividual")
    public String getEjercicioCliente(@RequestParam("idRutina") Integer idRutina,
                                      @RequestParam("idSesion") Integer idSesion,
                                      @RequestParam("idEjercicio") Integer idEjercicio,
                                      @RequestParam("idCliente") Integer idCliente
                                        , Model model) {
        ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("ejercicio", ejercicio);
        List<ClientExerciseEntity> clientExercise = clientExerciseRepository.getClientExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("clientExercise", clientExercise);
        model.addAttribute("idRutina", idRutina);
        model.addAttribute("idSesion", idSesion);
        model.addAttribute("idCliente", idCliente);
        return "entrenamiento_ejercicio_cliente";
    }

    @GetMapping("/valorarEjercicio")
    public String valorarDesdeEjercicio(@RequestParam("idRutina") Integer idRutina,
                                        @RequestParam("idEjercicio") Integer idEjercicio,
                                        @RequestParam("idCliente") Integer idCliente,
                                        @RequestParam("idSesion") Integer idSesion,
                                        Model model) {
        ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(idEjercicio);
        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("idSesion", idSesion); // Add idSesion to the model
        model.addAttribute("idRutina", idRutina);
        return "valorarUnEjercicio";
    }

    @PostMapping("/guardar")
    public String guardarValoracion(@RequestParam("stars") Integer stars,
                                    @RequestParam("idCliente") Integer idCliente,
                                    @RequestParam("exerciseId") Integer exerciseId,
                                    @RequestParam("idSesion") Integer idSesion,
                                    @RequestParam("idRutina") Integer idRutina,
                                    Model model) {
        String strTo="redirect:/home/cliente/ejercicio?idSesion=" + idSesion + "&idRutina=" + idRutina + "&idCliente=" + idCliente;
        if(idSesion==-1){
            strTo="redirect:/home/cliente/valorar?idCliente=" + idCliente;
        }
        UserEntity user = userRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + idCliente));
        ExerciseEntity exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + exerciseId));

        // Find or create the valoracion entity for this exercise
        List<ValoracionEntity> val = exercise.getValoracions();
        boolean valoracionExists = false;
        if (val != null) {
            for (ValoracionEntity v : val) {
                if (v.getUser().getId().equals(idCliente)) {
                    v.setStars(stars);  // Set the rating
                    valoracionRepository.save(v);
                    valoracionExists = true;
                    break;
                }
            }
        }

        if (!valoracionExists) {
            ValoracionEntity nuevaValoracion = new ValoracionEntity();
            ValoracionEntityId valoracionEntityId = new ValoracionEntityId();
            valoracionEntityId.setUserId(idCliente);
            valoracionEntityId.setExerciseId(exerciseId);

            nuevaValoracion.setId(valoracionEntityId);
            nuevaValoracion.setUser(user);
            nuevaValoracion.setExercise(exercise);
            nuevaValoracion.setStars(stars);
            nuevaValoracion.setDone((byte) 1); // Marking the rating as done

            valoracionRepository.save(nuevaValoracion);
        }


        return strTo;
    }

    @GetMapping("/valorar")
    public String todasValoraciones(Model model, @RequestParam("idCliente") Integer idCliente){

        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(idCliente);
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("idCliente", idCliente);


        List<Integer> exerciseIntegersByClientId = this.clientExerciseRepository.findExerciseIdByClientId(idCliente);

        ExerciseEntity ejercicio = new ExerciseEntity();
        List<ExerciseEntity> ejercicios = new ArrayList<>();
        for(Integer id : exerciseIntegersByClientId){
            ejercicio = this.exerciseRepository.getExercisesByIdEjercicio(id);
            ejercicios.add(ejercicio);
        }
        model.addAttribute("ejercicios", ejercicios);
        Integer idSesion=-1;
        Integer idRutina=-1;
        model.addAttribute("idSesion", idSesion);
        model.addAttribute("idRutina", idRutina);


        return "valoracion";
    }

    @PostMapping("/filtrarValoraciones")
    public String filtrarValoraciones(@ModelAttribute("filtro") FiltroValoracion filtro,
                                      @RequestParam("idCliente") Integer idCliente,
                                      Model model) {

        List<Integer> exerciseIds = clientExerciseRepository.findExerciseIdByClientId(idCliente);
        List<ExerciseEntity> ejercicios = new ArrayList<>();

        // Iteramos por cada ejercicio para buscar el que tenga la valoracion que buscamos.
        for (Integer exerciseId : exerciseIds) {
            ExerciseEntity ejercicio = exerciseRepository.getExercisesByIdEjercicio(exerciseId);
            if (ejercicio != null) {
                List<ValoracionEntity> valoraciones = ejercicio.getValoracions();
                boolean addEjercicioALista = true;

                // Vemos si el ejercicio tiene la misma valoracion que el filtro
                if (filtro.getStars() != null && filtro.getStars() > 0) {
                    addEjercicioALista = false; // Empieza asumiendo que no tiene la valoracion buscada

                    for (ValoracionEntity valoracion : valoraciones) {
                        if (valoracion.getUser().getId().equals(idCliente) && valoracion.getDone() == 1) {
                            if (valoracion.getStars() != null && valoracion.getStars() == filtro.getStars()) {
                                addEjercicioALista = true; // Ha encontrado la valoracion
                                break;
                            }
                        }
                    }
                }
                if (addEjercicioALista) {
                    ejercicios.add(ejercicio);
                }
            }
        }

        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("filtro", filtro);

        return "valoracion";
    }


    @PostMapping("/guardarReview")
    public String guardarReview(@RequestParam("idCliente") Integer idCliente,
                                @RequestParam("userId") Integer userId,
                                @RequestParam("exerciseId") Integer exerciseId,
                                @RequestParam("review") String review,
                                Model model) {
        ValoracionEntityId valoracionId = new ValoracionEntityId();
        valoracionId.setUserId(userId);
        valoracionId.setExerciseId(exerciseId);

        Optional<ValoracionEntity> valoracionEntity = this.valoracionRepository.findById(valoracionId);
        if (valoracionEntity.isPresent()) {
            ValoracionEntity valoracion = valoracionEntity.get();
            valoracion.setReview(review);
            valoracionRepository.save(valoracion);
        }
        return "redirect:/home/cliente/valorar?idCliente=" + idCliente;
    }



}
