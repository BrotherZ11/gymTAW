/*
package com.example.gymtaw.service;

import com.example.gymtaw.dao.*;
import com.example.gymtaw.dto.Routine;
import com.example.gymtaw.dto.Session;
import com.example.gymtaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineService {

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

    public List<Routine> getRoutinesByTrainer(Integer trainerId) {
        List<RoutineEntity> routines = routineRepository.getRoutinesbyEntrenador(trainerId);
        return this.entidadesADTO(lista);
    }

    public Routine getRoutineById(Integer id) {
        RoutineEntity routine = routineRepository.findById(id).orElse(null);
        return routine != null ? toDTO(routine) : null;
    }

    public void deleteRoutineById(Integer id) {
        routineRepository.deleteById(id);
    }

    public void saveRoutine(Routine routineDTO) {
        UserEntity trainer = userRepository.findById(routineDTO.getTrainerId()).orElse(null);
        RoutineEntity routine = new RoutineEntity();
        routine.setId(routineDTO.getId());
        routine.setName(routineDTO.getName());
        routine.setDescription(routineDTO.getDescription());
        routine.setDate(routineDTO.getDate());
        routine.setIdtrainer(trainer);
        routineRepository.save(routine);
    }


}
*/
