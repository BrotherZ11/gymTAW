package com.example.gymtaw.controller;

import com.example.gymtaw.dto.Rol;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.service.UserService;
import com.example.gymtaw.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController extends BaseController {

    @Autowired
    protected UserService userService;

    @GetMapping("/")
    public String doLogin (Model model, HttpSession session) {
        String strTo = "login";
        if (estaAutenticado(session)) {
            User usuario = (User) session.getAttribute("usuario");
            Rol rol = usuario.getRol();
            if(rol.getType().equals("admin")){
                strTo = "redirect:/users/";
            } else if(rol.getType().equals("crosstraining") || rol.getType().equals("bodybuilding")){
                strTo = "redirect:/home/trainer";
            } else {
                strTo = "redirect:/home/cliente";
            }
        } else {
            model.addAttribute("usuario", new Usuario());
        }
        return strTo;
    }

    @PostMapping("/autenticar")
    public String doAutentica (@ModelAttribute("usuario") Usuario usuario,
                               Model model, HttpSession session) {
        String strTo = "redirect:";
        User user = this.userService.autenticar(usuario.getUser(), usuario.getPassword());

        if (user == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            strTo = this.doLogin(model, session);
        } else {
            Rol rol = user.getRol();
            session.setAttribute("usuario", user);
             if(rol.getType().equals("admin")){
                strTo = "redirect:/users/";
            } else if(rol.getType().equals("crosstraining")){
                strTo = "redirect:/home/trainer";
            } else {
                strTo = "redirect:/home/cliente";
            }
        }
        return strTo;
    }


    @GetMapping("/salir")
    public String doSalir (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
