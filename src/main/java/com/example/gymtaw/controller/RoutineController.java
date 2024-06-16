package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.dao.RoutineHasSessionRepository;
import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.RoutineHasSessionEntity;
import com.example.gymtaw.entity.SessionEntity;
import com.example.gymtaw.entity.UserEntity;
import com.example.gymtaw.ui.FiltroRutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home/trainer")
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/rutina")
    public String doListar (Model model, @RequestParam("idEntrenador") Integer idEntrenador) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesbyEntrenador(idEntrenador);
        model.addAttribute("lista", rutinas);
        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("filtro", new FiltroRutina());
        return "routine_trainer";
    }

    @PostMapping("/filtrar")
    public String doListar (@ModelAttribute("filtro") FiltroRutina filtro, Model model) {
        List<RoutineEntity> rutinas = routineRepository.findByFiltro(filtro.getNombre());
        model.addAttribute("lista", rutinas);
        model.addAttribute("filtro", filtro);
        return "routine_trainer";
    }

    @GetMapping("/borrar")
    public String doBorrar (@RequestParam("id") Integer id, @RequestParam("idEntrenador") Integer idEntrenador) {
        this.routineRepository.deleteById(id);
        return "redirect:/home/trainer/rutina?idEntrenador=" + idEntrenador;
    }

    @GetMapping("/ver")
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

   /* @PostMapping("/guardar_sesiones")
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
                sessionRoutineEntity.setRoutineEntityIdroutine(idRutina);
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
    }*/

    @GetMapping("/crear")
    public String doNuevo (Model model, @RequestParam("idEntrenador") Integer idEntrenador) {
            RoutineEntity rutina = new RoutineEntity();
            rutina.setId(-1);
            model.addAttribute("rutina", rutina);
            model.addAttribute("idEntrenador", idEntrenador);
        return "routine";
    }


    @GetMapping("/editar")
    public String doEditar (@RequestParam("id") Integer id, Model model,  @RequestParam("idEntrenador") Integer idEntrenador) {
        RoutineEntity rutina = this.routineRepository.findById(id).orElse(null);
        model.addAttribute("rutina", rutina);
        model.addAttribute("idEntrenador", idEntrenador);
        return "routine";
    }

    @PostMapping("/guardar")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("descripcion") String descripcion,
                             @RequestParam("fecha") LocalDate fecha,
                             @RequestParam("idEntrenador") Integer idEntrenador){
        UserEntity entrenador = userRepository.findById(idEntrenador).orElse(null);
        RoutineEntity rutina = this.routineRepository.findById(id).orElse(new RoutineEntity());
        rutina.setName(nombre);
        rutina.setDescription(descripcion);
        rutina.setDate(Date.valueOf(fecha).toLocalDate());
        rutina.setIdtrainer(entrenador);
        this.routineRepository.save(rutina);

        return "redirect:/home/trainer/rutina?idEntrenador=" + idEntrenador;
    }
}
