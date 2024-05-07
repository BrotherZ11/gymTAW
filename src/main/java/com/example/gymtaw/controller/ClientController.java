package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.entity.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    RoutineRepository routineRepository;
    @GetMapping("/")
    public String menuUsuario(){
        return "userMenu";
    }

    @GetMapping("/entrenamiento")
    public String entrenamientoUsuario(Model model){
        List<Routine> lista =this.routineRepository.findAll();
        model.addAttribute("rutinas",lista);
        return "entrenamiento";
    }

}
