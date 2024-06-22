package com.example.gymtaw.controller;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.*;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.service.*;
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
    RoutineService routineService;

    @Autowired
    RoutineHasSessionService routineHasSessionService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    TypeHasRoutineRepository typeHasRoutineRepository;

    @Autowired
    TypeHasSessionRepository typeHasSessionRepository;

    @Autowired
    TypeService typeService;

    @GetMapping("/rutina")
    public String doListar (Model model, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            User usuario = (User) session.getAttribute("usuario");
            List<Routine> rutinas = routineService.listarRutinasEntrenador(usuario.getId());
            User user = userService.BuscarPorId(usuario.getId());
            String rol = user.getRol().getType();
            List<Type> tipos = typeService.cogerTipos();
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
            User usuario = (User) session.getAttribute("usuario");

            List<Routine> rutinas = null;
            List<Type> tipos = typeService.cogerTipos();
            User entrenador = userService.BuscarPorId(usuario.getId());
            String rol = entrenador.getRol().getType();

            if(filtro.estaVacioTipos()) rutinas = routineService.listarRutinas(filtro.getNombre(), usuario.getId());

            //else rutinas = routineRepository.findByFiltroNombreYTipo(filtro.getNombre(), filtro.getTipos(), usuario.getId());

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
            this.routineService.borrarRutina(idRutina);
            return "redirect:/home/trainer/rutina";
        }

    }

    @GetMapping("/ver")
    public String doVer (@RequestParam("idRutina") Integer idRutina, HttpSession session, Model model) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            User usuario = (User) session.getAttribute("usuario");
            List<RoutineHasSession> sessionRoutineEntities = routineHasSessionService.getSessionsRoutineByIdRoutine(idRutina);
            List<Session> sessionCompleteEntities = sessionService.buscarSesionesByEntrenador(usuario.getId());
            model.addAttribute("listaSesionRutina", sessionRoutineEntities);
            model.addAttribute("listaCompleta", sessionCompleteEntities);
            model.addAttribute("idRutina", idRutina);

            // Guardar idRutina
            session.setAttribute("idRutina", idRutina);
            return "entrenamiento";
        }

    }

    /*@PostMapping("/guardar_sesiones")
    public String doGuardarRutinas (HttpSession session,
                                    @RequestParam Map<String, String> allParams,
                                    Model model) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            Integer idRutina = (Integer) session.getAttribute("idRutina");
            //Borramos las sesiones que habia y los tipos de esas sesiones en la rutina
            List<RoutineHasSession> sesionesABorrar = routineHasSessionService.getSessionsRoutineByIdRoutine(idRutina);
            List<TypeHasRoutineEntity> tiposRutinaABorrar = typeHasRoutineRepository.getTypeHasRoutineEntitiesByRoutineIdroutine(idRutina);

            //routineHasSessionService.deleteAll(sesionesABorrar);
            typeHasRoutineRepository.deleteAll(tiposRutinaABorrar);

            //Creo la lista de tipos de rutina
            Set<TypeEntity> tiposRutina = new HashSet<>();

            Routine routine = routineService.buscarRutina(idRutina);

            // Guardar las nuevas sesiones
            for (int i = 1; i <= 7; i++) {
                String sessionIdParam = allParams.get("idSesion" + i);
                if (sessionIdParam != null && !sessionIdParam.equals("-1")) {
                    Integer idSesion = Integer.parseInt(sessionIdParam);
                    RoutineHasSessionEntityId routineHasSessionId = new RoutineHasSessionEntityId();
                    routineHasSessionId.setDay(i);
                    routineHasSessionId.setSessionId(idSesion);
                    routineHasSessionId.setRoutineId(idRutina);

                    SessionEntity sesion = sessionService.buscarSesionesByIdSesion(idSesion);

                    RoutineHasSessionEntity sessionRoutineEntity = new RoutineHasSessionEntity();
                    sessionRoutineEntity.setId(routineHasSessionId);
                    // sessionRoutineEntity.setRoutine(routine);
                    sessionRoutineEntity.setSession(sesion);
                   // routineHasSessionRepository.save(sessionRoutineEntity);

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
               // typeHasRoutineEntity.setRoutineIdroutine(routine);
                typeHasRoutineEntity.setTypeIdtype(tipo);
                typeHasRoutineRepository.save(typeHasRoutineEntity);
            }

            // Redirigir a la página de visualización de la rutina
            return "redirect:/home/trainer/ver?idRutina=" + idRutina;
        }

    }*/

    @GetMapping("/crear")
    public String doNuevo (Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            model.addAttribute("rutina", new Routine());
            return "routine";
        }

    }


    @GetMapping("/editar")
    public String doEditar (@RequestParam("idRutina") Integer idRutina, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/";
        else{
            Routine rutina = this.routineService.buscarRutina(idRutina);
            model.addAttribute("rutina", rutina);
            return "routine";
        }

    }

    @PostMapping("/guardar")
    public String doGuardar (@ModelAttribute("rutina") Routine rutina,
                             HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/";
        else {
            User usuario = (User) session.getAttribute("usuario");
            this.routineService.guardarRutina(rutina, usuario.getId());

            return "redirect:/home/trainer/rutina";
        }

    }
}
