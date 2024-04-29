package com.example.gymtaw.controller;

import com.example.gymtaw.dao.TypeRepository;
import com.example.gymtaw.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TypeController {
    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/")
    public String doListar (Model model) {
        List<Type> types = typeRepository.findAll();
        model.addAttribute("lista", types);
        return "listado";
    }
}
