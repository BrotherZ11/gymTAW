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

    public List<RoutineHasSession> cogersesionesderutina(Integer idRutina){
        List<RoutineHasSessionEntity> sesiones = this.routineHasSessionRepository.getSessionsRoutineByIdRoutine(idRutina);
        return this.entidadesADTO(sesiones);
    }

/*    public void borrarRutina(List<RoutineHasSession> sesionesABorrar) {
        routineHasSessionRepository.deleteAll(sesionesABorrar);
    }*/
}
