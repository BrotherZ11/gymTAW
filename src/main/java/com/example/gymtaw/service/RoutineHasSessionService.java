package com.example.gymtaw.service;

import com.example.gymtaw.dao.RoutineHasSessionRepository;
import com.example.gymtaw.dto.RoutineHasSession;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.RoutineHasSessionEntity;
import com.example.gymtaw.entity.RoutineHasSessionEntityId;
import com.example.gymtaw.entity.SessionEntity;
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

/*    public void actualizarSesiones(Integer idRutina, Set<Integer> sesiones) {
        routineHasSessionRepository.deleteByRoutineId(idRutina);
        sesiones.forEach(sessionId -> {
            RoutineHasSessionEntity entity = new RoutineHasSessionEntity(new RoutineHasSessionEntityId(idRutina, sessionId));
            entity.setSession(sessionRepository.findById(sessionId).orElse(null));
            routineHasSessionRepository.save(entity);
        });
    }*/

    public void guardarSesionEnRutina(RoutineEntity routine, SessionEntity sesion, int dia) {
        RoutineHasSessionEntityId routineHasSessionId = new RoutineHasSessionEntityId();
        routineHasSessionId.setDay(dia);
        routineHasSessionId.setSessionId(sesion.getId());
        routineHasSessionId.setRoutineId(routine.getId());

        RoutineHasSessionEntity sessionRoutineEntity = new RoutineHasSessionEntity();
        sessionRoutineEntity.setId(routineHasSessionId);
        sessionRoutineEntity.setRoutine(routine);
        sessionRoutineEntity.setSession(sesion);
        routineHasSessionRepository.save(sessionRoutineEntity);
    }
}
