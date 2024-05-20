package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RolRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.Rol;
import com.example.gymtaw.entity.User;
import com.example.gymtaw.ui.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            User usuario = (User) session.getAttribute("usuario");
            Optional<Rol> rol = this.rolRepository.findById(usuario.getId());
            if(rol.isPresent() && rol.get().getType().equals("admin")){
                strTo = "redirect:/users/";
            } else if(rol.isPresent() && (rol.get().getType().equals("cross-training") || rol.get().getType().equals("bodybuilding"))){
                strTo = "redirect:/home/trainer?idEntrenador="+usuario.getId();
            } /*else if(usuario.getPermiso() == ...){
                strTo = "redirect:";
            } else {
                strTo = "redirect:";
            }*/
        } else {
            model.addAttribute("usuario", new Usuario());
        }
        return strTo;
    }

    @PostMapping("/autenticar")
    public String doAutentica (@ModelAttribute("usuario") Usuario usuario,
                               Model model, HttpSession session) {
        String strTo = "redirect:";
        User user = this.userRepository.autenticacion(usuario.getUser(), usuario.getPassword());
        if (user == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            strTo = this.doLogin(model, session);
        } else {
            Rol rol = this.rolRepository.findById(user.getId()).orElse(null);
            session.setAttribute("usuario", user);
             if(rol != null && rol.getType().equals("admin")){
                strTo = "redirect:/users/";
            } else if(rol != null && rol.getType().equals("cross-training")){
                strTo = "redirect:/home/trainer?idEntrenador="+user.getId();
            } /*else if(usuario.getPermiso() == ...){
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
