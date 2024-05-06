package com.example.gymtaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    @GetMapping("/")
    public String menuUsuario(){
        return "userMenu";
    }

    @GetMapping("/entrenamiento")
    public String entrenamientoUsuario(){
        return "entrenamiento";
    }

}
