package com.example.gymtaw.service;

import com.example.gymtaw.dao.RoutineRepository;
import com.example.gymtaw.dao.TypeHasRoutineRepository;
import com.example.gymtaw.dao.TypeRepository;
import com.example.gymtaw.dto.Routine;
import com.example.gymtaw.dto.Type;
import com.example.gymtaw.dto.TypeHasRoutine;
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

    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private TypeRepository typeRepository;

    public List<TypeHasRoutine> getRoutinebyType(Integer idRutina){
        List<TypeHasRoutineEntity> tiposRutinaABorrar = typeHasRoutineRepository.getTypeHasRoutineEntitiesByRoutineIdroutine(idRutina);
        return this.entidadesADTO(tiposRutinaABorrar);
    }

    public void borrarTiposPorRutina(Integer idRutina){
        List<TypeHasRoutineEntity> tipos = this.typeHasRoutineRepository.getTypeHasRoutineEntitiesByRoutineIdroutine(idRutina);
        typeHasRoutineRepository.deleteAll(tipos);
    }

    public void guardarTipoEnRutina(Type type, Routine routine) {
        RoutineEntity rutina = routineRepository.findById(routine.getId()).orElse(null);
        TypeEntity tipo = typeRepository.findById(type.getId()).orElse(null);

        TypeHasRoutineEntityId typeHasRoutineEntityId = new TypeHasRoutineEntityId();
        typeHasRoutineEntityId.setRoutineIdroutine(routine.getId());
        typeHasRoutineEntityId.setTypeIdtype(type.getId());

        TypeHasRoutineEntity typeHasRoutineEntity = new TypeHasRoutineEntity();
        typeHasRoutineEntity.setId(typeHasRoutineEntityId);
        typeHasRoutineEntity.setRoutineIdroutine(rutina);
        typeHasRoutineEntity.setTypeIdtype(tipo);
        typeHasRoutineRepository.save(typeHasRoutineEntity);
    }


}
