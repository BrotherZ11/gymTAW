package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RolRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.RolEntity;
import com.example.gymtaw.entity.UserEntity;
import com.example.gymtaw.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController extends BaseController {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RolRepository rolRepository;

    @GetMapping("/")
    public String doLogin (Model model, HttpSession session) {
        String strTo = "login";
        if (estaAutenticado(session)) {
            UserEntity usuario = (UserEntity) session.getAttribute("usuario");
            RolEntity rol = this.rolRepository.findById(usuario.getIdRol().getId()).get();
            if(rol.getType().equals("admin")){
                strTo = "redirect:/users/";
            } else if(rol.getType().equals("crosstraining") || rol.getType().equals("bodybuilding")){
                strTo = "redirect:/home/trainer";
            }else if(rol.getType().equals("client")) {
                strTo = "redirect:/home/cliente";
            /*else if(usuario.getPermiso() == ...){
                strTo = "redirect:";
            } else {
                strTo = "redirect:";
            }*/
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
        UserEntity user = this.userRepository.autenticacion(usuario.getUser(), usuario.getPassword());
        if (user == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            strTo = this.doLogin(model, session);
        } else {
            RolEntity rol = this.rolRepository.findById(user.getId()).get();
            session.setAttribute("usuario", user);
             if(rol.getType().equals("admin")){
                strTo = "redirect:/users/";
            } else if(rol.getType().equals("cross-training")){
                strTo = "redirect:/home/trainer";
            }
             else if(rol.getType().equals("client")) {
                 strTo = "redirect:/home/cliente";
             }/*else if(usuario.getPermiso() == ...){
                strTo = "redirect:";
            } else {
                strTo = "redirect:";
            }*/
        }
        return strTo;
    }


    @GetMapping("/salir")
    public String doSalir (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
