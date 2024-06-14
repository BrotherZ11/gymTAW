package com.example.gymtaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/trainer")
    public String doHomeTrainer (@RequestParam("idEntrenador") Integer idEntrenador, Model model) {
        model.addAttribute("idEntrenador", idEntrenador);
        return "home_trainer";
    }

    @GetMapping("/client")
    public String doHomeCliente (@RequestParam("idCliente") Integer idCliente, Model model) {
        model.addAttribute("idCliente", idCliente);
        return "home_client";
    }

}
