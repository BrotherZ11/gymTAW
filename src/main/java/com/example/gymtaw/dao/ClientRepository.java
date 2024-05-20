package com.example.gymtaw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClientRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from User u join Trainer_Client on u.id = Trainer_Client.idCliente where Trainer_Client.idEntrenador = :idEntrenador", nativeQuery = true)
    public List<User> getClientesByIdEntrenador(@Param("idEntrenador") Integer idEntrenador);

}
