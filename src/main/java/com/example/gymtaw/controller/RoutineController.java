package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.entity.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @GetMapping("/rutina_bodybuilding")
    public String doListar (Model model) {
        List<Routine> routines = routineRepository.findAll();
        model.addAttribute("lista", routines);
        return "routine_bodybuilding";
    }

    @GetMapping("/borrar")
    public String doBorrar (@RequestParam("id") Integer id) {
        this.routineRepository.deleteById(id);
        return "redirect:/";
    }



    @GetMapping("/editar")
    public String doEditar (@RequestParam("id") Integer id, Model model) {
        Routine routines = this.routineRepository.findById(id).orElse(null);
        model.addAttribute("rutina", routines);

        return "routine_bodybuilding";
    }

    @PostMapping("/guardar")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("descripcion") String descripcion,
                             @RequestParam("fecha") LocalDate fecha){

        Routine routines = this.routineRepository.findById(id).orElse(new Routine());
        routines.setName(nombre);
        routines.setDescription(descripcion);
        routines.setDate(fecha);

        return "redirect:/";
    }
}
