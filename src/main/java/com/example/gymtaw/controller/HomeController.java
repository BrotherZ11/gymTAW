package com.example.gymtaw.controller;

import com.example.gymtaw.entity.Routine;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/trainer")
    public String doHomeTrainer (@RequestParam("idEntrenador") Integer idEntrenador, Model model) {
        model.addAttribute("idEntrenador", idEntrenador);
        return "home_trainer";
    }

}
