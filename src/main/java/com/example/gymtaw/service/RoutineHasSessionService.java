//David Zarzavilla Borrego 80%, David Molina Lopez 20%
package com.example.gymtaw.service;

import com.example.gymtaw.dao.RoutineHasSessionRepository;
import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.dto.Routine;
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

   @Autowired
   private SessionRepository sessionRepository;

   @Autowired
   private RoutineRepository routineRepository;

    public List<RoutineHasSession> getSessionsRoutineByIdRoutine(Integer idRutina){
        List<RoutineHasSessionEntity> sesiones = this.routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        return this.entidadesADTO(sesiones);
    }

    public void borrarSesionesPorRutina(Integer idRutina){
        List<RoutineHasSessionEntity> sesiones = this.routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        routineHasSessionRepository.deleteAll(sesiones);
    }


    public void guardarSesionEnRutina(Routine routine, Session session, int dia) {
        RoutineEntity rutina = routineRepository.findById(routine.getId()).orElse(null);
        SessionEntity sesion = sessionRepository.findById(session.getId()).orElse(null);

        RoutineHasSessionEntityId routineHasSessionId = new RoutineHasSessionEntityId();
        routineHasSessionId.setDay(dia);
        routineHasSessionId.setSessionId(sesion.getId());
        routineHasSessionId.setRoutineId(routine.getId());

        RoutineHasSessionEntity sessionRoutineEntity = new RoutineHasSessionEntity();
        sessionRoutineEntity.setId(routineHasSessionId);
        sessionRoutineEntity.setRoutine(rutina);
        sessionRoutineEntity.setSession(sesion);
        routineHasSessionRepository.save(sessionRoutineEntity);
    }
}
