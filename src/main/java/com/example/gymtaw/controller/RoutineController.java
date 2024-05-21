package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.dao.SessionRoutineRepository;
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.SessionRoutineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/trainer")
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    SessionRoutineRepository sessionRoutineRepository;

    @GetMapping("/rutina")
    public String doListar (Model model) {
        List<RoutineEntity> rutinas = routineRepository.findAll();
        model.addAttribute("lista", rutinas);
        return "routine_trainer";
    }

    @GetMapping("/borrar")
    public String doBorrar (@RequestParam("id") Integer id) {
        List<SessionRoutineEntity> sessions = sessionRoutineRepository.findSessionsByRoutineId(id);
        if (!sessions.isEmpty()) {
            // Si hay sesiones asociadas a esta rutina, elimínalas primero
            sessionRoutineRepository.deleteAll(sessions);
        }
        this.routineRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/crear")
    public String doNuevo (Model model) {
            RoutineEntity rutina = new RoutineEntity();
            rutina.setIdroutine(-1);
            model.addAttribute("rutina", rutina);

        return "routine";
    }


    @GetMapping("/editar")
    public String doEditar (@RequestParam("id") Integer id, Model model) {
        RoutineEntity rutina = this.routineRepository.findById(id).orElse(null);
        model.addAttribute("rutina", rutina);

        return "routine";
    }

    @PostMapping("/guardar")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("descripcion") String descripcion,
                             @RequestParam("fecha") LocalDate fecha){

        RoutineEntity rutina = this.routineRepository.findById(id).orElse(new RoutineEntity());
        rutina.setName(nombre);
        rutina.setDescription(descripcion);
        rutina.setDate(Date.valueOf(fecha));
        this.routineRepository.save(rutina);

        return "redirect:/";
    }
}
