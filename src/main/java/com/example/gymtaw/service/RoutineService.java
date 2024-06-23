package com.example.gymtaw.service;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.Routine;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoutineService extends DTOService<Routine, RoutineEntity>{

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private RoutineHasSessionRepository routineHasSessionRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeHasRoutineRepository typeHasRoutineRepository;

    @Autowired
    private TypeHasSessionRepository typeHasSessionRepository;

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

    public List<Routine> listarRutinas(String nombre, Integer id) {
        List<RoutineEntity> routines = this.routineRepository.findByFiltro(nombre, id);
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

/*    public List<Routine> listarRutinas(String nombre, Integer id, Set<Integer> types) {
        List<RoutineEntity> routines = this.routineRepository.findByFiltro(nombre, id, types);
        return this.entidadesADTO(routines);
    }*/

    public Routine buscarRutina(Integer id) {
        RoutineEntity routine = routineRepository.findById(id).orElse(null);
        if (routine != null) {
            return routine.toDTO();
        } else {
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

/*    public void actualizarSesionesRutina(Integer idRutina, Set<Integer> sesiones, Set<Integer> tipos) {
        routineSessionService.actualizarSesiones(idRutina, sesiones);
        routineTypeService.actualizarTipos(idRutina, tipos);
    }*/


}
