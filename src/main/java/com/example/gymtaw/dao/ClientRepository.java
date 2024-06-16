package com.example.gymtaw.dao;

import com.example.gymtaw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClientRepository extends JpaRepository<UserEntity, Integer> {

    /*@Query(value = "select * from User u join Trainer_Client on u.id = Trainer_Client.idCliente where Trainer_Client.idEntrenador = :idEntrenador", nativeQuery = true)
    public List<UserEntity> getClientesByIdEntrenador(@Param("idEntrenador") Integer idEntrenador);*/

    @Query("select u from UserEntity u join UserHasTrainerEntity ut on u.id = ut.userEntity.id where ut.userEntity.id = :idEntrenador")
    public List<UserEntity> getClientesByEntrenador(@Param("idEntrenador") int idEntrenador);

}
