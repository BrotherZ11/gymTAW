package com.example.gymtaw.service;
//Marta Granado Rodríguez 40%
import com.example.gymtaw.dao.SessionRepository;
import com.example.gymtaw.dao.UserRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionService extends DTOService<Session, SessionEntity>{

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseHasSessionService exerciseHasSessionService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private TypeHasSessionService typeHasSessionService;

    public Session buscarSesion(Integer idSesion){
        SessionEntity sesion = sessionRepository.findById(idSesion).orElse(null);
        if (sesion != null) {
            return sesion.toDTO();
        } else {
            return null;
        }
    }

    public String obtenerNombreSesion(Integer idSesion){
        SessionEntity sesion = sessionRepository.findById(idSesion).orElse(null);
        if (sesion != null) {
            return sesion.getName();
        }else{
            return null;
        }
    }

    public List<Session> listarSesionByIdRutina(Integer idRutina) {
        List<SessionEntity> sessions = sessionRepository.getSessionsByIdRoutine(idRutina);
        return this.entidadesADTO(sessions);
    }

    public List<Session> buscarSesionesByIdSesion(Integer idSesion) {
        List<SessionEntity> sesiones = sessionRepository.findSessionBySessionId(idSesion);
        return this.entidadesADTO(sesiones);
    }

    public List<Session> buscarSesionesByEntrenador(Integer idEntrenador) {
        List<SessionEntity> sesiones = sessionRepository.getSessionsByIdEntrenador(idEntrenador);
        return this.entidadesADTO(sesiones);
    }

    public void borrarSesion(Integer idSesion) {sessionRepository.deleteById(idSesion);}

    public void guardarSesion(User usuario, Integer idSesion, String nombre,List<Integer> ejercicioIds, Map<String, String> requestParams) {
        UserEntity entrenador = userRepository.findById(usuario.getId()).orElse(null);
        SessionEntity sesion = new SessionEntity();
        sesion.setName(nombre);
        sesion.setIdtrainer(entrenador);
        this.sessionRepository.save(sesion);
        Session nueva = this.buscarSesion(sesion.getId());
        this.actualizarEjerciciosSesion(nueva, ejercicioIds, requestParams);
    }

    public void actualizarEjerciciosSesion(Session sesion,List<Integer> ejercicioIds, Map<String, String> requestParams){
        exerciseHasSessionService.borrarEjerciciosdeSesion(sesion.getId());
        typeHasSessionService.borrarTiposPorSesion(sesion.getId());
        if (ejercicioIds != null) {
            //Creo una lista con los tipos de la sesion
            Set<Type> tiposSesion = new HashSet<>();

            for (Integer ejercicioId : ejercicioIds) {
                Integer orden = Integer.parseInt(requestParams.get("orden_" + ejercicioId));
                Exercise ejercicio = exerciseService.findByidEjercicio(ejercicioId);

                exerciseHasSessionService.guardarSesionEnRutina(ejercicio,sesion, orden);

                //Añado tipos de la sesion a la lista para quitar duplicados
                tiposSesion.add(ejercicio.getTypeIdtype());
            }

            //Una vez quitados los repetidos, guardamos los tipos unicos en las sesiones
            for(Type tipo : tiposSesion){
                typeHasSessionService.guardarTipoEnSesion(tipo,sesion);


            }

        }
    }
}
