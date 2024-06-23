//Gonzalo Muñoz Rubio
package com.example.gymtaw.service;

import com.example.gymtaw.dao.RolRepository;
import com.example.gymtaw.dto.Rol;
import com.example.gymtaw.entity.RolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolService extends DTOService<Rol, RolEntity>{
    @Autowired
    protected RolRepository rolRepository;

    public List<Rol> listarRoles() {
        List<RolEntity> roles = this.rolRepository.findAll();
        return this.entidadesADTO(roles);
    }
}
