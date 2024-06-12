package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RolRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.Rol;
import com.example.gymtaw.entity.User;
import com.example.gymtaw.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RolRepository rolRepository;

    @GetMapping("/")
    public String doListarUsuarios (Model model, HttpSession session) {
        String strTo = "/listadoUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            List<User> usuarios = userRepository.findAll();
            model.addAttribute("usuarios", usuarios);
            List<String> listaRoles = new ArrayList<>();

        }

        return strTo;
    }

    @GetMapping("/filtrar")
    public String doFiltrarUsuarios (Model model, HttpSession session, @RequestParam("nombreRol")String rol){
        String strTo = "listadoUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            List<User> usuarios = rolRepository.findByRol(rol);
            model.addAttribute("usuarios", usuarios);
        }
        return strTo;
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

    @GetMapping("/editarUsuario")
    public String editarUsuario (@RequestParam("id") Integer id,Model model, HttpSession session) {
        String strTo = "editarUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            User usuario = userRepository.findById(id).get();
            model.addAttribute("usuario", usuario);
        }
        return strTo;
    }

    @GetMapping("/crearUsuario")
    public String crearUsuario (Model model, HttpSession session) {
        String strTo = "crearUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        }
        User user = new User();
        user.setId(-1);
        model.addAttribute("usuario", user);
        return strTo;
    }

    @PostMapping("/guardarEdicion")
    public String doGuardarEdicion (@RequestParam("id") Integer id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("apellido") String apellido,
                             @RequestParam("edad") Integer edad,
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
            usuario.setAge(edad);
            usuario.setPhone(telefono);
            usuario.setGender(genero);
            this.userRepository.save(usuario);
        }
        return strTo;
    }

    @PostMapping("/guardarCreacion")
    public String doGuardarCreacion (@RequestParam("id") Integer id,
                             @RequestParam("gmail") String gmail,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("apellido") String apellido,
                             @RequestParam("edad") Integer edad,
                             @RequestParam("telefono") String telefono,
                             @RequestParam("genero") String genero,
                             @RequestParam("contrasena") String contrasena,
                             @RequestParam("dni") String dni,
                             @RequestParam("rol") String rol,
                             HttpSession session) {

        String strTo = "redirect:/users/";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            User usuario = userRepository.findById(id).orElse(new User());
            usuario.setEmail(gmail);
            usuario.setName(nombre);
            usuario.setSurname(apellido);
            usuario.setAge(edad);
            usuario.setPhone(telefono);
            usuario.setGender(genero);
            usuario.setPassword(contrasena);
            usuario.setDni(dni);

            Rol rolUsuario = new Rol();
            rolUsuario.setUser(usuario);
            rolUsuario.setId(usuario.getId());
            rolUsuario.setType(rol);

            this.userRepository.save(usuario);
            this.rolRepository.save(rolUsuario);
        }
        return strTo;
    }
}
