package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.dao.SessionRoutineRepository;
import com.example.gymtaw.entity.Routine;
import com.example.gymtaw.entity.SessionRoutine;
import com.example.gymtaw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    SessionRoutineRepository sessionRoutineRepository;

    @GetMapping("/")
    public String doListar (Model model) {
        List<Routine> rutinas = routineRepository.findAll();
        model.addAttribute("lista", rutinas);
        model.addAttribute("filtro", new Filtro());
        return "routine_bodybuilding";
    }

    @PostMapping("/filtrar")
    public String doListar(@ModelAttribute("filtro") Filtro filtro, Model model){
        List<Routine> rutinas = routineRepository.findByFiltro(filtro.getNombre());
        model.addAttribute("lista", rutinas);
        model.addAttribute("filtro", filtro);
        return "routine_bodybuilding";
    }

    @GetMapping("/borrar")
    public String doBorrar (@RequestParam("id") Integer id) {
        List<SessionRoutine> sessions = sessionRoutineRepository.findSessionsByRoutineId(id);
        if (!sessions.isEmpty()) {
            // Si hay sesiones asociadas a esta rutina, elimínalas primero
            sessionRoutineRepository.deleteAll(sessions);
        }
        this.routineRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/crear")
    public String doNuevo (Model model) {
            Routine rutina = new Routine();
            rutina.setId(-1);
            model.addAttribute("rutina", rutina);

        return "routine";
    }


    @GetMapping("/editar")
    public String doEditar (@RequestParam("id") Integer id, Model model) {
        Routine rutina = this.routineRepository.findById(id).orElse(null);
        model.addAttribute("rutina", rutina);

        return "routine";
    }

    @PostMapping("/guardar")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("descripcion") String descripcion,
                             @RequestParam("fecha") LocalDate fecha){

        Routine rutina = this.routineRepository.findById(id).orElse(new Routine());
        rutina.setName(nombre);
        rutina.setDescription(descripcion);
        rutina.setDate(fecha);
        this.routineRepository.save(rutina);

        return "redirect:/";
    }
}
