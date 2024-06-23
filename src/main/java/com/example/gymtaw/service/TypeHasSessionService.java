// David Zarzavilla Borrego
package com.example.gymtaw.service;

import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.dao.TypeHasSessionRepository;
import com.example.gymtaw.dao.TypeRepository;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.dto.TypeHasSession;
import com.example.gymtaw.entity.SessionEntity;
import com.example.gymtaw.entity.TypeEntity;
import com.example.gymtaw.entity.TypeHasSessionEntity;
import com.example.gymtaw.entity.TypeHasSessionEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeHasSessionService extends DTOService<TypeHasSession, TypeHasSessionEntity> {

    @Autowired
    private TypeHasSessionRepository typeHasSessionRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TypeRepository typeRepository;

    public void borrarTiposPorSesion(Integer idSesion){
        List<TypeHasSessionEntity> tipos = this.typeHasSessionRepository.getTypeHasRoutineEntitiesBySessionId(idSesion);
        typeHasSessionRepository.deleteAll(tipos);
    }

    public void guardarTipoEnSesion(Type type, Session session){
        SessionEntity sesion = sessionRepository.findById(session.getId()).orElse(null);
        TypeEntity tipo = typeRepository.findById(type.getId()).orElse(null);

        TypeHasSessionEntityId typeHasSessionEntityId = new TypeHasSessionEntityId();
        typeHasSessionEntityId.setSessionId(session.getId());
        typeHasSessionEntityId.setTypeIdtype(type.getId());

        TypeHasSessionEntity typeHasSessionEntity = new TypeHasSessionEntity();
        typeHasSessionEntity.setId(typeHasSessionEntityId);
        typeHasSessionEntity.setSession(sesion);
        typeHasSessionEntity.setTypeIdtype(tipo);
        typeHasSessionRepository.save(typeHasSessionEntity);
    }
}
