//Gonzalo Muñoz Rubio

package com.example.gymtaw.dao;

import com.example.gymtaw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u where u.email = :user and u.password = :pwd")
    UserEntity autenticacion (@Param("user") String user, @Param("pwd")String pwd);

    @Query("select u from UserEntity u where u.idRol.id = :rol")
    List<UserEntity> findUserEntitiesByIdRol(@Param("rol") int rol);

    @Query("select u from UserEntity u where u.name like concat('%', :nombre, '%') and u.surname like concat('%', :apellido, '%') and u.dni like concat('%', :dni, '%')")
    List<UserEntity> findUserEntitiesByNameSurnameDni(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("dni") String dni);

    @Query("select u from UserEntity u where (u.idRol.id = 1 or u.idRol.id = 2) and " +
            "u.id not in (select ut.trainer.id from UserHasTrainerEntity ut where ut.user.id = :userId)")
    List<UserEntity> findTrainersNotAssignedToUser(@Param("userId") Integer userId);

    @Query("select u from UserEntity u where u.idRol.id = 4 and " +
     "u.id not  in (select ut.user.id from UserHasTrainerEntity ut where ut.trainer.id = :trainerId)")
    List<UserEntity> findClientsNotAssignedToTrainer(@Param("trainerId") int trainerId);

    @Query("select u from UserEntity u where (u.idRol.id = 1 or u.idRol.id = 2) and " +
            "u.id in (select ut.trainer.id from UserHasTrainerEntity ut where ut.user.id = :userId)")
    List<UserEntity> findTrainersAssignedToUser(@Param("userId") int userId);

    @Query("select u from UserEntity u where u.idRol.id = 4 and " +
            "u.id in (select ut.user.id from UserHasTrainerEntity ut where ut.trainer.id = :trainerId)")
    List<UserEntity> findClientsAssignedToTrainer(@Param("trainerId") int trainerId);
}
