package com.example.gymtaw.service;

import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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
}
