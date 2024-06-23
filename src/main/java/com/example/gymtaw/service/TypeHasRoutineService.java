package com.example.gymtaw.service;

import com.example.gymtaw.dao.TypeHasRoutineRepository;
import com.example.gymtaw.dto.TypeHasRoutine;
import com.example.gymtaw.dto.TypeHasRoutineId;
import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.TypeEntity;
import com.example.gymtaw.entity.TypeHasRoutineEntity;
import com.example.gymtaw.entity.TypeHasRoutineEntityId;
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

    public void borrarTiposPorRutina(Integer idRutina){
        List<TypeHasRoutineEntity> tipos = this.typeHasRoutineRepository.getTypeHasRoutineEntitiesByRoutineIdroutine(idRutina);
        typeHasRoutineRepository.deleteAll(tipos);
    }

    public void guardarTipoEnRutina(Integer idRutina, TypeEntity tipo, RoutineEntity routine) {
        TypeHasRoutineEntityId typeHasRoutineEntityId = new TypeHasRoutineEntityId();
        typeHasRoutineEntityId.setRoutineIdroutine(idRutina);
        typeHasRoutineEntityId.setTypeIdtype(tipo.getId());

        TypeHasRoutineEntity typeHasRoutineEntity = new TypeHasRoutineEntity();
        typeHasRoutineEntity.setId(typeHasRoutineEntityId);
        typeHasRoutineEntity.setRoutineIdroutine(routine);
        typeHasRoutineEntity.setTypeIdtype(tipo);
        typeHasRoutineRepository.save(typeHasRoutineEntity);
    }


}
