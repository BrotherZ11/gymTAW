package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.entity.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
