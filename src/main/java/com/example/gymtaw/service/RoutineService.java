package com.example.gymtaw.service;
//Marta Granado Rodríguez
import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.Routine;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RoutineService extends DTOService<Routine, RoutineEntity>{

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    RoutineHasSessionService routineHasSessionService;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    TypeHasRoutineService typeHasRoutineService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeService typeService;

    @Autowired
    SessionService sessionService;

    @Autowired
    private TypeRepository typeRepository;

    public List<Routine> listarRutinasEntrenador(Integer trainerId) {
        List<RoutineEntity> routines = routineRepository.getRoutinesbyEntrenador(trainerId);
        return this.entidadesADTO(routines);
    }

    public List<Routine> listarRutinasCliente(Integer clienteId) {
        List<RoutineEntity> rutinas = routineRepository.getRoutinesByClient(clienteId);
        return this.entidadesADTO(rutinas);
    }

    public List<Routine> filtrarRutinas(String nombre, Integer id) {
        List<RoutineEntity> routines = this.routineRepository.findByFiltro(nombre, id);
        return this.entidadesADTO(routines);
    }
    public List<Routine> filtrarRutinasPorTipos(String nombre,  Set<Integer> tipos, Integer id) {
        Set<TypeEntity> tiposEntity = new HashSet<>();
        for(Integer idTipo : tipos){
            TypeEntity tipeEntity = typeRepository.findById(idTipo).orElse(null);
            tiposEntity.add(tipeEntity);
        }
        List<RoutineEntity> routines = this.routineRepository.findByFiltroNombreYTipo(nombre, tiposEntity, id);
        return this.entidadesADTO(routines);
    }

    public List<Routine> listarRutinasPorEntrenadorYCliente(Integer idEntrenador, Integer idCliente){
        List<RoutineEntity> routines = routineRepository.getRoutinesByIdEntrenadorAndIdCliente(idEntrenador, idCliente);
        return this.entidadesADTO(routines);
    }

    public List<Routine> listarRutinasSinAsignarPorEntrenador(Integer idEntrenador){
        List<RoutineEntity> routines = routineRepository.getRoutinesByIdEntrenadorNoCliente(idEntrenador);
        return this.entidadesADTO(routines);
    }

    public List<Routine> listarRutinasFiltradasPorEntrenadorClienteNombreYTipos(Integer idEntrenador,
                                                                                Integer idCliente,
                                                                                String nombre,
                                                                                Set<Integer> tipos){
        Set<TypeEntity> tiposEntity = new HashSet<>();
        for(Integer idTipo : tipos){
            TypeEntity tipeEntity = typeRepository.findById(idTipo).orElse(null);
            tiposEntity.add(tipeEntity);
        }
        List<RoutineEntity> routines = routineRepository.getRoutinesByIdEntrenadorAndIdClienteFiltroNombreYTipos(idEntrenador, idCliente, nombre, tiposEntity);
        return this.entidadesADTO(routines);
    }

    public Routine buscarRutina(Integer id) {
        RoutineEntity routine = routineRepository.findById(id).orElse(null);
        if (routine != null) {
            return routine.toDTO();
        } else {
            return null;
        }
    }

    public String obtenerNombreRutina(Integer idRutina){
        RoutineEntity rutina = routineRepository.findById(idRutina).orElse(null);
        if (rutina != null) {
            return rutina.getName();
        }else{
            return null;
        }
    }

    public void borrarRutina(Integer id) {
        routineRepository.deleteById(id);
    }

    public void quitarClienteDeRutina(Integer idRutina){
        RoutineEntity rutina = routineRepository.findById(idRutina).orElse(null);
        rutina.setIdclient(null);
        routineRepository.save(rutina);
    }

    public void asignarClienteEnRutina(Integer idRutina, Integer idCliente){
        RoutineEntity rutina = routineRepository.findById(idRutina).orElse(null);
        UserEntity cliente= userRepository.findById(idCliente).orElse(null);
        rutina.setIdclient(cliente);
        routineRepository.save(rutina);
    }


    public void guardarRutina(Routine rutina, Integer idEntrenador) {
        UserEntity trainer = userRepository.findById(idEntrenador).orElse(null);
        RoutineEntity routine = new RoutineEntity();
        routine.setId(rutina.getId());
        routine.setName(rutina.getName());
        routine.setDescription(rutina.getDescription());
        routine.setDate(rutina.getDate());
        routine.setIdtrainer(trainer);
        routineRepository.save(routine);
    }

    public void actualizarSesionesRutina(Integer idRutina, Map<String, String> allParams) {
        routineHasSessionService.borrarSesionesPorRutina(idRutina);
        typeHasRoutineService.borrarTiposPorRutina(idRutina);

        Set<Type> tiposRutina = new HashSet<>();

        Routine routine = buscarRutina(idRutina);

        for (int i = 1; i <= 7; i++) {
            String sessionIdParam = allParams.get("idSesion" + i);
            if (sessionIdParam != null && !sessionIdParam.equals("-1")) {
                Integer idSesion = Integer.parseInt(sessionIdParam);
                Session sesion = sessionService.buscarSesion(idSesion);

                routineHasSessionService.guardarSesionEnRutina(routine, sesion, i);

                List<Type> tiposSesion = typeService.getTypesBySessionId(sesion.getId());
                tiposRutina.addAll(tiposSesion);
            }
        }

        for (Type tipo : tiposRutina) {
            typeHasRoutineService.guardarTipoEnRutina(tipo, routine);
        }
    }
}

