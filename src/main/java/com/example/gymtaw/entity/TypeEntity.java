package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "type")
public class TypeEntity implements DTO<Type> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtype", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @OneToMany(mappedBy = "typeIdtype")
    private Set<ExerciseEntity> exercises = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "type_has_routine",
            joinColumns = @JoinColumn(name = "type_idtype"),
            inverseJoinColumns = @JoinColumn(name = "routine_idroutine")
    )
    private Set<RoutineEntity> routines = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "type_has_session",
            joinColumns = @JoinColumn(name = "type_idtype"),
            inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private Set<SessionEntity> sessions = new LinkedHashSet<>();

    public Type toDTO(){
        Type type = new Type();
        type.setId(this.getId());
        type.setName(this.getName());
        return type;
    }

}
