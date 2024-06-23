//Gonzalo Muñoz Rubio 90%, David Molina Lopez 10%
package com.example.gymtaw.service;

import com.example.gymtaw.dao.RolRepository;
import com.example.gymtaw.dao.UserHasTrainerRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dto.Routine;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends DTOService<User, UserEntity>{

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RolRepository rolRepository;

    @Autowired
    protected UserHasTrainerRepository userTrainerRepository;

    public User autenticar (String user, Integer password) {
        UserEntity usuario = this.userRepository.autenticacion(user, password);
        if (usuario != null) {
            return usuario.toDTO();
        } else {
            return null;
        }
    }

    public List<User> listarUsuarios() {
        List<UserEntity> usuarios = this.userRepository.findAll();
        return this.entidadesADTO(usuarios);
    }

    public List<User> listarUsuariosPorRol(int rol) {
        List<UserEntity> usuarios = this.userRepository.findUserEntitiesByIdRol(rol);
        return this.entidadesADTO(usuarios);
    }

    public List<User> listarUsuariosPorNombreApellidoDni(String nombre, String apellido, String dni) {
        List<UserEntity> usuarios = this.userRepository.findUserEntitiesByNameSurnameDni(nombre, apellido, dni);
        return this.entidadesADTO(usuarios);
    }

    public List<User> listarClientesPorEntrenador(Integer idEntrenador) {
        List<UserEntity> clientes = this.userRepository.findClientsAssignedToTrainer(idEntrenador);
        return this.entidadesADTO(clientes);
    }

    public User BuscarPorId (Integer id) {
        UserEntity usuario = this.userRepository.findById(id).orElse(null);
        if (usuario != null) {
            return usuario.toDTO();
        } else {
            return null;
        }
    }

    public void borrarUsuario (Integer id) {
        this.userRepository.deleteById(id);
    }


    public void guardarEdicionUsuario(User usuario) {
        UserEntity usuarioEntity = this.userRepository.findById(usuario.getId()).orElse(new UserEntity());
        usuarioEntity.setName(usuario.getName());
        usuarioEntity.setSurname(usuario.getSurname());
        usuarioEntity.setDni(usuario.getDni());
        usuarioEntity.setPhone(usuario.getPhone());
        usuarioEntity.setAge(usuario.getAge());
        usuarioEntity.setGender(usuario.getGender());
        this.userRepository.save(usuarioEntity);
    }

    public void guardarCreacionUsuario(User user) {
        UserEntity usuarioEntity = new UserEntity();
        usuarioEntity.setEmail(user.getEmail());
        usuarioEntity.setPassword(user.getPassword().hashCode());
        usuarioEntity.setName(user.getName());
        usuarioEntity.setSurname(user.getSurname());
        usuarioEntity.setDni(user.getDni());
        usuarioEntity.setPhone(user.getPhone());
        usuarioEntity.setAge(user.getAge());
        usuarioEntity.setGender(user.getGender());
        RolEntity rol = this.rolRepository.findById(user.getRol().getId()).orElse(null);
        usuarioEntity.setIdRol(rol);
        this.userRepository.save(usuarioEntity);
    }

    public List<User> listarEntrenadoresNoAsignados(int idUsuario) {
        List<UserEntity> entrenadores = this.userRepository.findTrainersNotAssignedToClient(idUsuario);
        return this.entidadesADTO(entrenadores);
    }

    public List<User> listarClientesNoAsignados(int idUsuario) {
        List<UserEntity> clientes = this.userRepository.findClientsNotAssignedToTrainer(idUsuario);
        return this.entidadesADTO(clientes);
    }

    public void asignarUsuarios(Integer idUsuario, List<Integer> usuariosAsignar) {
        UserEntity usuario = this.userRepository.findById(idUsuario).orElse(null);
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
    }

    public void desasignarUsuarios(Integer idUsuario, List<Integer> usuariosDeasignar) {
        UserEntity usuario = this.userRepository.findById(idUsuario).orElse(null);

        if (usuario == null) {
            // Usuario no encontrado, no se puede proceder
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario);
        }

        List<UserEntity> usuarios = this.userRepository.findAllById(usuariosDeasignar);

        if (usuarios.isEmpty()) {
            // No hay usuarios a desasignar
            throw new IllegalArgumentException("No se encontraron usuarios para desasignar con los IDs proporcionados");
        }

        if (usuario.getIdRol().getId() == 4) {
            for (UserEntity u : usuarios) {
                UserHasTrainerEntityId utId = new UserHasTrainerEntityId();
                utId.setUserId(usuario.getId());
                utId.setTrainerId(u.getId());
                if (this.userTrainerRepository.existsById(utId)) {
                    this.userTrainerRepository.deleteById(utId);
                } else {
                    // Log or handle case where the association doesn't exist
                    System.out.println("No existe la asociación entre usuario y entrenador para ser eliminada: " + utId);
                }
            }
        } else {
            for (UserEntity u : usuarios) {
                UserHasTrainerEntityId utId = new UserHasTrainerEntityId();
                utId.setUserId(u.getId());
                utId.setTrainerId(usuario.getId());
                if (this.userTrainerRepository.existsById(utId)) {
                    this.userTrainerRepository.deleteById(utId);
                } else {
                    // Log or handle case where the association doesn't exist
                    System.out.println("No existe la asociación entre entrenador y usuario para ser eliminada: " + utId);
                }
            }
        }
    }

    public List<User> listarEntrenadoresAsignados(Integer id) {
        List<UserEntity> entrenadores = this.userRepository.findTrainersAssignedToClient(id);
        return this.entidadesADTO(entrenadores);
    }

    public List<User> listarClientesAsignados(Integer id) {
        List<UserEntity> clientes = this.userRepository.findClientsAssignedToTrainer(id);
        return this.entidadesADTO(clientes);
    }
}
