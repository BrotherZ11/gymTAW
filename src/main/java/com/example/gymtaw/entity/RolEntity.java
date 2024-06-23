package com.example.gymtaw.entity;
//Gonzalo Muñoz Rubio
import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class RolEntity implements DTO<Rol> {
    @Id
    @Column(name = "id_rol", nullable = false)
    private Integer id;

    @Column(name = "type", length = 20)
    private String type;

    @OneToMany(mappedBy = "idRol")
    private Set<UserEntity> users = new LinkedHashSet<>();

    public Rol toDTO() {
        Rol rol = new Rol();
        rol.setId(this.id);
        rol.setType(this.type);

        Set<Integer> users = new LinkedHashSet<>();
        this.users.forEach(user -> users.add(user.getId()));
        rol.setUsers(users);

        return rol;
    }
}