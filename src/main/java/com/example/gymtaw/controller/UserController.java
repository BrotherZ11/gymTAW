package com.example.gymtaw.controller;

import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/usuarios")
    public String doListarUsuarios (Model model) {
        List<User> usuarios = userRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "listadoUsuario";
    }
}
