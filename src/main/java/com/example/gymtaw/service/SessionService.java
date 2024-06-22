package com.example.gymtaw.service;

import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.SessionEntity;
import com.example.gymtaw.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService extends DTOService<Session, SessionEntity>{

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> listarSesionByIdRutina(Integer idRutina) {
        List<SessionEntity> sessions = sessionRepository.getSessionsByIdRoutine(idRutina);
        return this.entidadesADTO(sessions);
    }

    public List<Session> buscarSesionesByIdSesion(Integer idSesion) {
        List<SessionEntity> sesiones = sessionRepository.findSessionBySessionId(idSesion);
        return this.entidadesADTO(sesiones);
    }
}
