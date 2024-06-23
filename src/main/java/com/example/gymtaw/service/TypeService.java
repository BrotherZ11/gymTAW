// David Zarzavilla Borrego
package com.example.gymtaw.service;

import com.example.gymtaw.dao.TypeHasRoutineRepository;
import com.example.gymtaw.dao.TypeHasSessionRepository;
import com.example.gymtaw.dao.TypeRepository;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.entity.TypeEntity;
import com.example.gymtaw.entity.TypeHasSessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService extends DTOService<Type, TypeEntity> {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeHasSessionRepository typeHasSessionRepository;

    public List<Type> cogerTipos() {
        List<TypeEntity> typeEntities = typeRepository.findAll();
        return this.entidadesADTO(typeEntities);
    }

    public List<Type> getTypesBySessionId(Integer sessionId) {
        List<TypeHasSessionEntity> typeSessions = typeHasSessionRepository.getTypeHasRoutineEntitiesBySessionId(sessionId);
        return typeSessions.stream()
                .map(typeSession -> typeSession.getTypeIdtype().toDTO())
                .collect(Collectors.toList());
    }


}
