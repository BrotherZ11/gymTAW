package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.dao.RoutineHasSessionRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.RoutineHasSessionEntity;
import com.example.gymtaw.entity.UserEntity;
import com.example.gymtaw.ui.FiltroRutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/home/trainer")
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineHasSessionRepository routineHasSessionRepository;

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
        List<RoutineHasSessionEntity> sessions = routineHasSessionRepository.findSessionsByRoutineId(id);
        if (!sessions.isEmpty()) {
            // Si hay sesiones asociadas a esta rutina, elimínalas primero
            routineHasSessionRepository.deleteAll(sessions);
        }
        this.routineRepository.deleteById(id);
        return "redirect:/home/trainer/rutina?idEntrenador=" + idEntrenador;
    }

    @GetMapping("/crear")
    public String doNuevo (Model model, @RequestParam("idEntrenador") Integer idEntrenador) {
            RoutineEntity rutina = new RoutineEntity();
            rutina.setIdroutine(-1);
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
        rutina.setDate(Date.valueOf(fecha));
        rutina.setUserByIdtrainer(entrenador);
        this.routineRepository.save(rutina);

        return "redirect:/home/trainer/rutina?idEntrenador=" + idEntrenador;
    }
}
