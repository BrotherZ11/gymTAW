package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.entity.RoutineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home/client")
public class ClienteController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    RoutineRepository sessionRoutineRepository;

    @GetMapping("/entrenamientos")
    public String doListar(@RequestParam("idCliente") Integer idCliente, Model model) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(idCliente);
        model.addAttribute("rutinas", rutinas); // Make sure this name matches the JSP
        model.addAttribute("idCliente", idCliente);
        return "entrenamiento_rutina_cliente";
    }
}
