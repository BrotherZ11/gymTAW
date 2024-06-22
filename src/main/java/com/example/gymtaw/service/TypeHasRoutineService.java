package com.example.gymtaw.service;

import com.example.gymtaw.dao.TypeHasRoutineRepository;
import com.example.gymtaw.dto.TypeHasRoutine;
import com.example.gymtaw.dto.TypeHasRoutineId;
import com.example.gymtaw.entity.TypeHasRoutineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeHasRoutineService extends DTOService<TypeHasRoutine, TypeHasRoutineEntity> {

    @Autowired
    private TypeHasRoutineRepository typeHasRoutineRepository;

    public List<TypeHasRoutine> getRoutinebyType(Integer idRutina){
        List<TypeHasRoutineEntity> tiposRutinaABorrar = typeHasRoutineRepository.getTypeHasRoutineEntitiesByRoutineIdroutine(idRutina);
        return this.entidadesADTO(tiposRutinaABorrar);
    }



}
