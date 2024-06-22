package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RolRepository;
import com.example.gymtaw.dao.UserHasTrainerRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.entity.RolEntity;
import com.example.gymtaw.entity.UserEntity;
import com.example.gymtaw.entity.UserHasTrainerEntity;
import com.example.gymtaw.entity.UserHasTrainerEntityId;
import com.example.gymtaw.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    UserHasTrainerRepository userTrainerRepository;

    @GetMapping("/")
    public String doListarUsuarios (Model model, HttpSession session) {
        String strTo = "/listadoUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            List<UserEntity> usuarios = userRepository.findAll();
            List<RolEntity> roles = rolRepository.findAll();
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("filtro", new Filtro());
            model.addAttribute("rols", roles);
        }

        return strTo;
    }

    @PostMapping("/filtrar")
    public String filtrarUsuarios(@ModelAttribute("filtro") Filtro filtro, HttpSession session, Model model){
        String strTo = "listadoUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        }
        List<UserEntity> usuarios = new ArrayList<>();
        if(filtro.noFilter()){
            usuarios = userRepository.findAll();
        } else {
            if(filtro.getIdRol() != 0){
                usuarios = userRepository.findUserEntitiesByIdRol(filtro.getIdRol());
            } else {
                usuarios = userRepository.findUserEntitiesByNameSurnameDni(filtro.getNombre(), filtro.getApellido(), filtro.getDni());
            }
        }
        List<RolEntity> rols = rolRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("filtro", filtro);
        model.addAttribute("rols", rols);

        return strTo;
    }

    @GetMapping("/borrar")
    public String borrarUsuario (@RequestParam("id") Integer id, HttpSession session) {
        String strTo = "redirect:/users/";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        }
        UserEntity usuario = userRepository.findById(id).get();
        this.userRepository.delete(usuario);
        return strTo;
    }

    @GetMapping("/editarUsuario")
    public String editarUsuario (@RequestParam("id") Integer id,Model model, HttpSession session) {
        String strTo = "editarUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            UserEntity usuario = userRepository.findById(id).get();
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
        UserEntity userEntity = new UserEntity();
        userEntity.setId(-1);
        model.addAttribute("usuario", userEntity);
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
            UserEntity usuario = this.userRepository.findById(id).orElse(new UserEntity());
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
                                     @RequestParam("rol") int rol,
                                     HttpSession session) {

        String strTo = "redirect:/users/";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            UserEntity usuario = userRepository.findById(id).orElse(new UserEntity());
            usuario.setEmail(gmail);
            usuario.setName(nombre);
            usuario.setSurname(apellido);
            usuario.setAge(edad);
            usuario.setPhone(telefono);
            usuario.setGender(genero);
            usuario.setPassword(contrasena);
            usuario.setDni(dni);
            RolEntity rolEntity = rolRepository.findById(rol).orElse(new RolEntity());
            usuario.setIdRol(rolEntity);

            this.userRepository.save(usuario);
        }
        return strTo;
    }

    @GetMapping("/asignar")
    public String asignar(@RequestParam("id") int idUsuario, Model model, HttpSession session){
        UserEntity usuario = userRepository.findById(idUsuario).get();
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        List<UserEntity> usuarios;
        if(usuario.getIdRol().getId() == 4){
            usuarios = userRepository.findTrainersNotAssignedToUser(usuario.getId());
        } else {
            usuarios = userRepository.findClientsNotAssignedToTrainer(usuario.getId());
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuariosAsignacion", usuarios);
        return "asignarView";
    }

    @PostMapping("/realizarAsignacion")
    public String doAsignacion (@RequestParam("idUsuario") Integer idUsuario, @RequestParam("idsUsuariosAsignar") List<Integer> usuariosAsignar, Model model, HttpSession session){
        UserEntity usuario = userRepository.findById(idUsuario).get();
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        List<UserEntity> usuarios = userRepository.findAllById(usuariosAsignar);
        if(usuario.getIdRol().getId()== 4){
            for(UserEntity u : usuarios){
                UserHasTrainerEntity ut = new UserHasTrainerEntity();
                UserHasTrainerEntityId utId = new UserHasTrainerEntityId();
                utId.setUserId(usuario.getId());
                utId.setTrainerId(u.getId());
                ut.setId(utId);
                ut.setUser(usuario);
                ut.setTrainer(u);
                this.userTrainerRepository.save(ut);
            }
        } else {
            for(UserEntity u : usuarios){
                UserHasTrainerEntity ut = new UserHasTrainerEntity();
                UserHasTrainerEntityId utId = new UserHasTrainerEntityId();
                utId.setUserId(u.getId());
                utId.setTrainerId(usuario.getId());
                ut.setId(utId);
                ut.setUser(u);
                ut.setTrainer(usuario);
                this.userTrainerRepository.save(ut);
            }
        }
        return "redirect:/users/";
    }

    @GetMapping("/desasignar")
    public String desasignar(@RequestParam("id") int idUsuario, Model model, HttpSession session){
        UserEntity usuario = userRepository.findById(idUsuario).get();
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        List<UserEntity> usuarios;
        if(usuario.getIdRol().getId()== 4){
            usuarios = userRepository.findTrainersAssignedToUser(usuario.getId());
        } else {
            usuarios = userRepository.findClientsAssignedToTrainer(usuario.getId());
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuariosDesasignacion", usuarios);

        return "desasignarView";
    }

    @PostMapping("/realizarDesasignacion")
    public String doDesasignacion (@RequestParam("idUsuario") Integer idUsuario, @RequestParam("idsUsuariosDesasignar") List<Integer> usuariosDeasignar, Model model, HttpSession session){
        UserEntity usuario = userRepository.findById(idUsuario).get();
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        List<UserEntity> usuarios = userRepository.findAllById(usuariosDeasignar);
        if(usuario.getIdRol().getId() == 4){
            for(UserEntity u : usuarios){
                UserHasTrainerEntityId utId = new UserHasTrainerEntityId();
                utId.setUserId(usuario.getId());
                utId.setTrainerId(u.getId());
                UserHasTrainerEntity ut = userTrainerRepository.findById(utId).get();
                userTrainerRepository.delete(ut);
            }
        } else {
            for(UserEntity u : usuarios){
                UserHasTrainerEntityId utId = new UserHasTrainerEntityId();
                utId.setUserId(u.getId());
                utId.setTrainerId(usuario.getId());
                UserHasTrainerEntity ut = userTrainerRepository.findById(utId).get();
                userTrainerRepository.delete(ut);
            }
        }
        return "redirect:/users/";
    }
}
