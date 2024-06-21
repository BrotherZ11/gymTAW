package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.ui.FiltroRutina;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/home/trainer")
public class RoutineController extends BaseController{

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TypeHasRoutineRepository typeHasRoutineRepository;

    @Autowired
    TypeHasSessionRepository typeHasSessionRepository;

    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/rutina")
    public String doListar (Model model, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idEntrenador = (Integer) session.getAttribute("idEntrenador");
            List<RoutineEntity> rutinas = routineRepository.getRoutinesbyEntrenador(idEntrenador);
            UserEntity user = userRepository.getById(idEntrenador);
            String rol = user.getIdRol().getType();
            List<TypeEntity> tipos = typeRepository.findAll();
            model.addAttribute("tipos", tipos);
            model.addAttribute("lista", rutinas);
            model.addAttribute("rol", rol);
            model.addAttribute("filtro", new FiltroRutina());
            return "routine_trainer";
        }

    }

    @PostMapping("/filtrar")
    public String doListar (@ModelAttribute("filtro") FiltroRutina filtro, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idEntrenador = (Integer) session.getAttribute("idEntrenador");
            List<RoutineEntity> rutinas = null;
            List<TypeEntity> tipos = typeRepository.findAll();
            UserEntity entrenador = userRepository.findById(idEntrenador).orElse(null);
            String rol = entrenador.getIdRol().getType();

            if(filtro.estaVacioTipos()) rutinas = routineRepository.findByFiltro(filtro.getNombre(), idEntrenador);
            else rutinas = routineRepository.findByFiltroNombreYTipo(filtro.getNombre(), filtro.getTipos(), idEntrenador);

            model.addAttribute("lista", rutinas);
            model.addAttribute("tipos", tipos);
            model.addAttribute("rol", rol);
            model.addAttribute("filtro", filtro);
            return "routine_trainer";
        }

    }

    @GetMapping("/borrar")
    public String doBorrar (@RequestParam("idRutina") Integer idRutina, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            this.routineRepository.deleteById(idRutina);
            return "redirect:/home/trainer/rutina";
        }

    }

    @GetMapping("/ver")
    public String doVer (@RequestParam("idRutina") Integer idRutina, HttpSession session, Model model) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idEntrenador = (Integer) session.getAttribute("idEntrenador");
            List<RoutineHasSessionEntity> sessionRoutineEntities = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
            List<SessionEntity> sessionCompleteEntities = sessionRepository.getSessionsByIdEntrenador(idEntrenador);
            model.addAttribute("listaSesionRutina", sessionRoutineEntities);
            model.addAttribute("listaCompleta", sessionCompleteEntities);
            model.addAttribute("idRutina", idRutina);
            // Guardar idRutina
            session.setAttribute("idRutina", idRutina);
            return "entrenamiento";
        }

    }

    @PostMapping("/guardar_sesiones")
    public String doGuardarRutinas (HttpSession session,
                                    @RequestParam Map<String, String> allParams,
                                    Model model) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idRutina = (Integer) session.getAttribute("idRutina");
            //Borramos las sesiones que habia y los tipos de esas sesiones en la rutina
            List<RoutineHasSessionEntity> sesionesABorrar = routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
            List<TypeHasRoutineEntity> tiposRutinaABorrar = typeHasRoutineRepository.getTypeHasRoutineEntitiesByRoutineIdroutine(idRutina);

            routineHasSessionRepository.deleteAll(sesionesABorrar);
            typeHasRoutineRepository.deleteAll(tiposRutinaABorrar);

            //Creo la lista de tipos de rutina
            Set<TypeEntity> tiposRutina = new HashSet<>();

            RoutineEntity routine = routineRepository.findById(idRutina).orElse(null);

            // Guardar las nuevas sesiones
            for (int i = 1; i <= 7; i++) {
                String sessionIdParam = allParams.get("idSesion" + i);
                if (sessionIdParam != null && !sessionIdParam.equals("-1")) {
                    Integer idSesion = Integer.parseInt(sessionIdParam);
                    RoutineHasSessionEntityId routineHasSessionId = new RoutineHasSessionEntityId();
                    routineHasSessionId.setDay(i);
                    routineHasSessionId.setSessionId(idSesion);
                    routineHasSessionId.setRoutineId(idRutina);

                    SessionEntity sesion = sessionRepository.findById(idSesion).orElse(null);

                    RoutineHasSessionEntity sessionRoutineEntity = new RoutineHasSessionEntity();
                    sessionRoutineEntity.setId(routineHasSessionId);
                    sessionRoutineEntity.setRoutine(routine);
                    sessionRoutineEntity.setSession(sesion);
                    routineHasSessionRepository.save(sessionRoutineEntity);

                    //Añado tipos de las sesiones a la lista para quitar duplicados
                    List<TypeHasSessionEntity> tiposSesion = typeHasSessionRepository.getTypeHasRoutineEntitiesBySessionId(sesion.getId());
                    for(TypeHasSessionEntity tipo : tiposSesion){
                        tiposRutina.add(tipo.getTypeIdtype());
                    }
                }
            }

            //Una vez quitados los repetidos, guardamos los tipos unicos en las sesiones
            for(TypeEntity tipo : tiposRutina){
                TypeHasRoutineEntityId typeHasRoutineEntityId = new TypeHasRoutineEntityId();
                typeHasRoutineEntityId.setRoutineIdroutine(idRutina);
                typeHasRoutineEntityId.setTypeIdtype(tipo.getId());

                TypeHasRoutineEntity typeHasRoutineEntity = new TypeHasRoutineEntity();
                typeHasRoutineEntity.setId(typeHasRoutineEntityId);
                typeHasRoutineEntity.setRoutineIdroutine(routine);
                typeHasRoutineEntity.setTypeIdtype(tipo);
                typeHasRoutineRepository.save(typeHasRoutineEntity);
            }

            // Redirigir a la página de visualización de la rutina
            return "redirect:/home/trainer/ver?idRutina=" + idRutina;
        }

    }

    @GetMapping("/crear")
    public String doNuevo (Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            RoutineEntity rutina = new RoutineEntity();
            rutina.setId(-1);
            model.addAttribute("rutina", rutina);
            return "routine";
        }

    }


    @GetMapping("/editar")
    public String doEditar (@RequestParam("idRutina") Integer idRutina, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            RoutineEntity rutina = this.routineRepository.findById(idRutina).orElse(null);
            model.addAttribute("rutina", rutina);
            return "routine";
        }

    }

    @PostMapping("/guardar")
    public String doGuardar (@RequestParam("idRutina") Integer idRutina,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("descripcion") String descripcion,
                             @RequestParam("fecha") LocalDate fecha,
                             HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/";
        else {
            Integer idEntrenador = (Integer) session.getAttribute("idEntrenador");
            UserEntity entrenador = userRepository.findById(idEntrenador).orElse(null);
            RoutineEntity rutina = this.routineRepository.findById(idRutina).orElse(new RoutineEntity());
            rutina.setName(nombre);
            rutina.setDescription(descripcion);
            rutina.setDate(Date.valueOf(fecha).toLocalDate());
            rutina.setIdtrainer(entrenador);
            this.routineRepository.save(rutina);

            return "redirect:/home/trainer/rutina";
        }

    }
}
