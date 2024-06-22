package com.example.gymtaw.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    @GetMapping("/trainer")
    public String doHomeTrainer (Model model, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        else{
            return "home_trainer";
        }

    }

    @GetMapping("/cliente")
    public String doHomeCliente (Model model, HttpSession session) {
        if(!estaAutenticado(session)) return  "redirect:/";
        return "home_client";
    }

}
