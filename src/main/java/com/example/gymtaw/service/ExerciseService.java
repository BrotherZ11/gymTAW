package com.example.gymtaw.service;
//Marta Granado Rodríguez 50%, David Zarzavilla Borrego 10%, David Molina Lopez 10%, Gonzalo Muñoz Rubio 30%
import com.example.gymtaw.dao.ClientExerciseRepository;
import com.example.gymtaw.dao.ExerciseRepository;
import com.example.gymtaw.dao.TypeRepository;
import com.example.gymtaw.dto.Exercise;
import com.example.gymtaw.entity.ExerciseEntity;
import com.example.gymtaw.entity.TypeEntity;
import com.example.gymtaw.ui.FiltroEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService extends DTOService<Exercise, ExerciseEntity>{

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ClientExerciseRepository clientExerciseRepository;

    @Autowired
    TypeRepository typeRepository;



    public List<Exercise> listarEjerciciosFuerza(){
        List<ExerciseEntity> exer = exerciseRepository.getExerciseFuerza();
        return this.entidadesADTO(exer);
    }


    public List<Exercise> listarEjercicios() {
        List<ExerciseEntity> ejercicios = exerciseRepository.findAll();
        return this.entidadesADTO(ejercicios);

    }

    public List<Exercise> getExercisesByIdSession(Integer idSesion){
        List<ExerciseEntity> exer = exerciseRepository.getExercisesByIdSession(idSesion);
        return this.entidadesADTO(exer);
    }

    public Exercise findByidEjercicio(Integer idEjercicio) {
        ExerciseEntity exer = exerciseRepository.findById(idEjercicio).orElse(null);
        if (exer != null) {
            return exer.toDTO();
        } else {
            return null;
        }
    }

    public List<Exercise> findExercisesWithAReviewByIdClientAndIdEntrenador(Integer idCliente, Integer idEntrenador){
        List<ExerciseEntity> ejercicios = exerciseRepository.getExerciseEntitiesByIdClienteIdEntrenadorAndHaveReview(idCliente, idEntrenador);
        return this.entidadesADTO(ejercicios);
    }

    public List<Exercise> findExercisesWithDataByIdSesion(Integer idSesion){
        List<ExerciseEntity> ejercicios = exerciseRepository.getExercisesByIdSessionWithData(idSesion);
        return this.entidadesADTO(ejercicios);
    }

    public void borrarEjercicio(Integer id) {
        exerciseRepository.deleteById(id);
    }

    public void guardarEdicionEjercicio(Exercise ejercicio) {
        ExerciseEntity exer = this.exerciseRepository.findById(ejercicio.getId()).get();
        exer.setName(ejercicio.getName());
        exer.setDescription(ejercicio.getDescription());
        exer.setVideo(ejercicio.getVideo());
        this.exerciseRepository.save(exer);
    }

    public void guardarCreacionEjercicio(Exercise ejercicio) {
        ExerciseEntity exer = new ExerciseEntity();
        exer.setName(ejercicio.getName());
        exer.setDescription(ejercicio.getDescription());
        exer.setVideo(ejercicio.getVideo());
        TypeEntity type = this.typeRepository.findById(ejercicio.getTypeIdtype().getId()).get();
        exer.setTypeIdtype(type);
        this.exerciseRepository.save(exer);
    }

    public List<Exercise> filtrarEjerciciosPorTipo(Integer idTipo) {
        List<ExerciseEntity> ejercicios = this.exerciseRepository.findExercisesByType(idTipo);
        return this.entidadesADTO(ejercicios);
    }

    public List<Exercise> filtrarEjerciciosPorNombreDescripcionUrl(String nombre, String descripcion, String url) {
        List<ExerciseEntity> ejercicios = this.exerciseRepository.findExercisesByNameDescriptionUrl(nombre, descripcion, url);
        return this.entidadesADTO(ejercicios);
    }
}
