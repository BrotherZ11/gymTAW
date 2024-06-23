// David Zarzavilla Borrego 50%, David Molina Lopez 50%
package com.example.gymtaw.dao;

import com.example.gymtaw.entity.RoutineEntity;
import com.example.gymtaw.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Integer> {

    @Query("select r from RoutineEntity r where r.idtrainer.id = :idEntrenador and r.idclient.id = :idCliente")
    public List<RoutineEntity> getRoutinesByIdEntrenadorAndIdCliente(@Param("idEntrenador") Integer idEntrenador,
            @Param("idCliente") Integer idCliente);

    @Query("SELECT r FROM RoutineEntity r WHERE r.idclient.id = :idCliente")
    List<RoutineEntity> getRoutinesByClient(@Param("idCliente") Integer idCliente);

    @Query("select r from RoutineEntity r where r.idtrainer.id = :idEntrenador and r.idclient.id = :idCliente and r.name like concat('%', :nombre, '%') and not exists (select t from TypeEntity t where t in :tipos and t not in elements(r.types))")
    public List<RoutineEntity> getRoutinesByIdEntrenadorAndIdClienteFiltroNombreYTipos(
            @Param("idEntrenador") Integer idEntrenador,
            @Param("idCliente") Integer idCliente,
            @Param("nombre") String nombre,
            @Param("tipos") Set<TypeEntity> tipos);

    @Query("select r from RoutineEntity r where r.idtrainer.id = :idEntrenador and r.idclient IS NULL")
    public List<RoutineEntity> getRoutinesByIdEntrenadorNoCliente(@Param("idEntrenador") Integer idEntrenador);

    @Query("select r from RoutineEntity r where r.idtrainer.id = :idEntrenador")
    public List<RoutineEntity> getRoutinesbyEntrenador(@Param("idEntrenador") Integer idEntrenador);

    @Query("select r from RoutineEntity r where r.name like concat('%', :filtro, '%')  and r.idtrainer.id = :idEntrenador")
    public List<RoutineEntity> findByFiltro(@Param("filtro") String filtro,
            @Param("idEntrenador") Integer idEntrenador);

    @Query("select r from RoutineEntity r where r.name like concat('%', :nombre, '%') and not exists (select t from TypeEntity t where t in :tipos and t not in elements(r.types)) and r.idtrainer.id = :idEntrenador")
    public List<RoutineEntity> findByFiltroNombreYTipo(@Param("nombre") String nombre,
            @Param("tipos") Set<TypeEntity> tipos, @Param("idEntrenador") Integer idEntrenador);
}
