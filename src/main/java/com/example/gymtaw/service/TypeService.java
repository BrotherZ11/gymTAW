package com.example.gymtaw.service;

import com.example.gymtaw.dao.TypeRepository;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.entity.TypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService extends DTOService<Type, TypeEntity> {

    @Autowired
    private TypeRepository typeRepository;

    public List<Type> cogerTipos() {
        List<TypeEntity> typeEntities = typeRepository.findAll();
        return this.entidadesADTO(typeEntities);
    }


}
