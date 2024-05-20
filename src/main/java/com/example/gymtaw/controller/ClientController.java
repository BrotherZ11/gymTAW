package com.example.gymtaw.controller;

import com.example.gymtaw.dao.ClientRepository;
import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.entity.Routine;
import com.example.gymtaw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home/trainer")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoutineRepository routineRepository;

    @GetMapping("/clients")
    public String doListar (@RequestParam("idEntrenador") Integer idEntrenador, Model model) {
        List<User> clients = clientRepository.getClientesByIdEntrenador(idEntrenador);
        model.addAttribute("lista", clients);
        return "clients";
    }

    @GetMapping("/routine_client")
    public String doRoutineClient (@RequestParam("idEntrenador") Integer idEntrenador,
                                   @RequestParam("idCliente") Integer idCliente,
                                   Model model) {
        List<Routine> routines = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        model.addAttribute("lista", routines);
        return "routine_client";
    }

}
