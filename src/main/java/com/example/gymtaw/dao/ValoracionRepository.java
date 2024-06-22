package com.example.gymtaw.dao;

import com.example.gymtaw.entity.ValoracionEntity;
import com.example.gymtaw.entity.ValoracionEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ValoracionRepository extends JpaRepository<ValoracionEntity, ValoracionEntityId> {
    @Query("select v from ValoracionEntity v where v.user.id = :idCliente order by v.exercise.id")
    List<ValoracionEntity> findValoracionEntitiesByIdCliente(Integer idCliente);
}
