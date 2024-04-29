package com.example.gymtaw.controller;

import com.example.gymtaw.dao.TypeRepository;
import com.example.gymtaw.entity.Type;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class TypeController {
    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/")
    public String doListar(Model model) {
        List<Type> types = typeRepository.findAll();
        model.addAttribute("lista", types);
        return "listado";
    }
    @GetMapping("/login")
    public String doLogin(Model model) {
        return "login";
    }

    @PostMapping("/verificar")
    public String doVerificar(@RequestParam("txtUsu") String username,
                              @RequestParam("txtPass") String password,
                              Model model) {
        // Perform verification logic here
        // For simplicity, let's assume verification succeeds if username and password are not empty
        if (!username.isEmpty() && !password.isEmpty()) {
            return "redirect:/"; // Redirect to the homepage after successful verification
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Return to the login page with an error message
        }
    }

}
