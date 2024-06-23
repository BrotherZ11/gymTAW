//Gonzalo Muñoz Rubio

package com.example.gymtaw.controller;

import com.example.gymtaw.dao.RolRepository;
import com.example.gymtaw.dao.UserHasTrainerRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.Rol;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.*;
import com.example.gymtaw.service.ExerciseService;
import com.example.gymtaw.service.RolService;
import com.example.gymtaw.service.TypeService;
import com.example.gymtaw.service.UserService;
import com.example.gymtaw.ui.Filtro;
import com.example.gymtaw.ui.FiltroEjercicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class AdminController extends BaseController{
    @Autowired
    protected UserService userService;
    @Autowired
    protected RolService rolService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    TypeService typeService;

    @GetMapping("/")
    public String doListarUsuarios (Model model, HttpSession session) {
        String strTo = "/listadoUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            List<User> usuarios = userService.listarUsuarios();
            List<Rol> roles = rolService.listarRoles();
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
        List<User> usuarios = new ArrayList<>();
        if(filtro.noFilter()){
            usuarios = userService.listarUsuarios();
        } else {
            if(filtro.getIdRol() != 0){
                usuarios = userService.listarUsuariosPorRol(filtro.getIdRol());
            } else {
                usuarios = userService.listarUsuariosPorNombreApellidoDni(filtro.getNombre(), filtro.getApellido(), filtro.getDni());
            }
        }
        List<Rol> rols = rolService.listarRoles();
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
        this.userService.borrarUsuario(id);

        return strTo;
    }

    @GetMapping("/editarUsuario")
    public String editarUsuario (@RequestParam("id") Integer id,Model model, HttpSession session) {
        String strTo = "editarUsuario";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            User usuario = userService.BuscarPorId(id);
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
        User usuario = new User();
        List<Rol> rols = rolService.listarRoles();
        model.addAttribute("usuario", usuario);
        model.addAttribute("rols", rols);
        return strTo;
    }

    @PostMapping("/guardarEdicion")
    public String doGuardarEdicion (@ModelAttribute("usuario") User usuario, HttpSession session) {
        String strTo = "redirect:/users/";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            this.userService.guardarEdicionUsuario(usuario);
        }
        return strTo;
    }

    @PostMapping("/guardarCreacion")
    public String doGuardarCreacion (@ModelAttribute("usuario") User usuario, HttpSession session) {

        String strTo = "redirect:/users/";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            this.userService.guardarCreacionUsuario(usuario);

        }
        return strTo;
    }

    @GetMapping("/asignar")
    public String asignar(@RequestParam("id") int idUsuario, Model model, HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        User usuario = this.userService.BuscarPorId(idUsuario);
        List<User> usuarios;
        if(usuario.getRol().getId() == 4){
            usuarios = this.userService.listarEntrenadoresNoAsignados(idUsuario);
        } else {
            usuarios = this.userService.listarClientesNoAsignados(idUsuario);
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuariosAsignacion", usuarios);
        return "asignarView";
    }

    @PostMapping("/realizarAsignacion")
    public String doAsignacion (@RequestParam("idUsuario") Integer idUsuario, @RequestParam("idsUsuariosAsignar") List<Integer> usuariosAsignar, Model model, HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        this.userService.asignarUsuarios(idUsuario, usuariosAsignar);
        return "redirect:/users/";
    }

    @GetMapping("/desasignar")
    public String desasignar(@RequestParam("id") int idUsuario, Model model, HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        User usuario = this.userService.BuscarPorId(idUsuario);
        List<User> usuarios;
        if(usuario.getRol().getId()== 4){
            usuarios = userService.listarEntrenadoresAsignados(usuario.getId());
        } else {
            usuarios = userService.listarClientesAsignados(usuario.getId());
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuariosDesasignacion", usuarios);

        return "desasignarView";
    }

    @PostMapping("/realizarDesasignacion")
    public String doDesasignacion (@RequestParam("idUsuario") Integer idUsuario, @RequestParam("idsUsuariosDesasignar") List<Integer> usuariosDeasignar, Model model, HttpSession session){
        if(!estaAutenticado(session)){
            return "redirect:/";
        }
        this.userService.desasignarUsuarios(idUsuario, usuariosDeasignar);
        return "redirect:/users/";
    }

    @GetMapping("/ejercicios")
    public String doListarEjercicios(Model model, HttpSession session){
        String strTo = "listadoEjercicios";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            List<Exercise> ejercicios = exerciseService.listarEjercicios();
            List<Type> tipos = typeService.cogerTipos();
            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("filtro", new FiltroEjercicio());
            model.addAttribute("tipos", tipos);
        }
        return strTo;
    }

    @GetMapping("/borrarEjercicio")
    public String borrarEjercicio (@RequestParam("id") Integer id, HttpSession session) {
        String strTo = "redirect:/users/ejercicios";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        }
        this.exerciseService.borrarEjercicio(id);
        return strTo;
    }

    @GetMapping("/editarEjercicio")
    public String editarEjercicio (@RequestParam("id") Integer id,Model model, HttpSession session) {
        String strTo = "editarEjercicio";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            Exercise ejercicio = exerciseService.findByidEjercicio(id);
            model.addAttribute("ejercicio", ejercicio);
        }
        return strTo;
    }

    @GetMapping("/crearEjercicio")
    public String crearEjercicio (Model model, HttpSession session) {
        String strTo = "crearEjercicio";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        }
        Exercise ejercicio = new Exercise();
        List<Type> tipos = typeService.cogerTipos();
        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("tipos", tipos);
        return strTo;
    }

    @PostMapping("/guardarEdicionEjercicio")
    public String doGuardarEdicionEjercicio (@ModelAttribute("ejercicio") Exercise ejercicio, HttpSession session) {
        String strTo = "redirect:/users/ejercicios";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            this.exerciseService.guardarEdicionEjercicio(ejercicio);
        }
        return strTo;
    }

    @PostMapping("/guardarCreacionEjercicio")
    public String doGuardarCreacionEjercicio (@ModelAttribute("ejercicio") Exercise ejercicio, HttpSession session) {
        String strTo = "redirect:/users/ejercicios";
        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        } else {
            this.exerciseService.guardarCreacionEjercicio(ejercicio);
        }
        return strTo;
    }

    @PostMapping("/filtrarEjercicios")
    public String doFiltrarEjerciciosPorTipo(@ModelAttribute("filtro") FiltroEjercicio filtro, Model model, HttpSession session){
        String strTo = "listadoEjercicios";
        if(!estaAutenticado(session)){
            strTo = "redirect:/";
        } else {
            List<Exercise> ejercicios = new ArrayList<>();
            if(filtro.getIdTipo() != null){
                ejercicios = exerciseService.filtrarEjerciciosPorTipo(filtro.getIdTipo());
            } else {
                ejercicios = exerciseService.filtrarEjerciciosPorNombreDescripcionUrl(filtro.getNombre(), filtro.getDescripcion(), filtro.getURL());
            }

            List<Type> tipos = typeService.cogerTipos();
            model.addAttribute("ejercicios", ejercicios);
            model.addAttribute("filtro", filtro);
            model.addAttribute("tipos", tipos);
        }
        return strTo;
    }
}
