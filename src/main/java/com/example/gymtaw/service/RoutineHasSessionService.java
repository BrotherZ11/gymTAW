package com.example.gymtaw.service;

import com.example.gymtaw.dao.RoutineHasSessionRepository;
import com.example.gymtaw.dto.RoutineHasSession;
import com.example.gymtaw.entity.RoutineHasSessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineHasSessionService extends DTOService<RoutineHasSession, RoutineHasSessionEntity> {

   @Autowired
   private RoutineHasSessionRepository routineHasSessionRepository;

    public List<RoutineHasSession> getSessionsRoutineByIdRoutine(Integer idRutina){
        List<RoutineHasSessionEntity> sesiones = this.routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        return this.entidadesADTO(sesiones);
    }

    public void borrarSesionesPorRutina(Integer idRutina){
        List<RoutineHasSessionEntity> sesiones = this.routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        routineHasSessionRepository.deleteAll(sesiones);
    }

    public void actualizarSesiones(Integer idRutina, Set<Integer> sesiones) {
        routineHasSessionRepository.deleteByRoutineId(idRutina);
        sesiones.forEach(sessionId -> {
            RoutineHasSessionEntity entity = new RoutineHasSessionEntity(new RoutineHasSessionEntityId(idRutina, sessionId));
            entity.setSession(sessionRepository.findById(sessionId).orElse(null));
            routineHasSessionRepository.save(entity);
        });
    }
}
