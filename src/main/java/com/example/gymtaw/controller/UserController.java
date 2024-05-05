package com.example.gymtaw.controller;

import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String doListarUsuarios (Model model, HttpSession session) {
        String strTo = "/listadoUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            List<User> usuarios = userRepository.findAll();
            model.addAttribute("usuarios", usuarios);
        }

        return "listadoUsuario";
    }

    @GetMapping("/borrar")
    public String borrarUsuario (@RequestParam("id") Integer id, HttpSession session) {
        String strTo = "redirect:/users/";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        }
        userRepository.deleteById(id);
        return strTo;
    }

    @GetMapping("/editar")
    public String editarUsuario (@RequestParam("id") Integer id,Model model, HttpSession session) {
        String strTo = "usuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            User usuario = userRepository.findById(id).get();
            model.addAttribute("usuario", usuario);
        }
        return strTo;
    }

    @PostMapping("/guardar")
    public String doGuardar (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("apellido") String apellido,
                             @RequestParam("edad") short edad,
                             @RequestParam("telefono") String telefono,
                             @RequestParam("genero") String genero,
                             HttpSession session) {

        String strTo = "redirect:/users/";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            User usuario = this.userRepository.findById(id).orElse(new User());
            usuario.setName(nombre);
            usuario.setSurname(apellido);
            usuario.setAge((int) edad);
            usuario.setPhone(telefono);
            usuario.setGender(genero);
            this.userRepository.save(usuario);
        }

        return strTo;
    }
}
