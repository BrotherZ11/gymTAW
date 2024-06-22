package com.example.gymtaw.service;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.Routine;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.dto.User;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Routine> listarRutinas(Integer trainerId) {
        List<RoutineEntity> routines = routineRepository.getRoutinesbyEntrenador(trainerId);
        return this.entidadesADTO(routines);
    }

    public List<Routine> listarRutinas(String nombre, Integer id) {
        List<RoutineEntity> routines = this.routineRepository.findByFiltro(nombre, id);
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


}
