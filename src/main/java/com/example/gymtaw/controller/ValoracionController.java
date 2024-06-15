//package com.example.gymtaw.controller;
//
//import com.example.gymtaw.dao.ExerciseRepository;
//import com.example.gymtaw.dao.UserRepository;
//import com.example.gymtaw.dao.ValoracionRepository;
//import com.example.gymtaw.entity.ValoracionEntity;
//import com.example.gymtaw.entity.UserEntity;
//import com.example.gymtaw.entity.ExerciseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/home/cliente")
//public class ValoracionController {
//
//    @Autowired
//    private ValoracionRepository valoracionRepository;
//
//    @Autowired
//    private ExerciseRepository exerciseRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/valoraciones")
//    public String listValoraciones(@RequestParam("idCliente") Integer idCliente, Model model) {
//        List<ExerciseEntity> ejercicios = exerciseRepository.getEjerciciosByUserId(idCliente);
//        List<ValoracionEntity> valoraciones = valoracionRepository.findByExerciseId(idCliente);
//        model.addAttribute("valoraciones", valoraciones);
//        model.addAttribute("ejercicios",ejercicios);
//        return "valoraciones";
//    }
//
//    @PostMapping("/rateExercise")
//    public String rateExercise(@RequestParam("userId") Integer userId,
//                               @RequestParam("exerciseId") Integer exerciseId,
//                               @RequestParam("stars") Integer stars,
//                               @RequestParam(value = "review", required = false) String review) {
//        ValoracionEntity valoracion = new ValoracionEntity();
//        valoracion.setUserId(userId);
//        valoracion.setExerciseId(exerciseId);
//        valoracion.setStars(stars);
//        valoracion.setReview(review);
//        valoracion.setDone((byte) 1);
//
//        valoracionRepository.save(valoracion);
//
//        return "redirect:/home/cliente/ejercicios?id=" + exerciseId;
//    }
//}
