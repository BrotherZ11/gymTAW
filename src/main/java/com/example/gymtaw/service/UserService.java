package com.example.gymtaw.service;

import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends DTOService<User, UserEntity>{

    @Autowired
    protected UserRepository userRepository;
    public User autenticar (String user, String password) {
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
}
