package com.example.gymtaw.dao;

import com.example.gymtaw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClientRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u join UserHasTrainerEntity ut on u.id = ut.user.id where ut.trainer.id= :idEntrenador")
    public List<UserEntity> getClientesByEntrenador(@Param("idEntrenador") int idEntrenador);

}
