package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.Session;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "session")
public class SessionEntity implements DTO<Session> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idtrainer", nullable = false)
    private UserEntity idtrainer;

    @OneToMany(mappedBy = "session")
    private Set<ExerciseHasSessionEntity> exerciseHasSessions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "session")
    private Set<RoutineHasSessionEntity> routineHasSessions = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "sessions")
    private Set<TypeEntity> types = new LinkedHashSet<>();

    public Session toDTO(){
        Session session = new Session();
        session.setId(this.getId());
        session.setName(this.getName());
        session.setExerciseHasSessions(this.getExerciseHasSessions());
        session.setRoutineHasSessions(this.getRoutineHasSessions());
        session.setTypes(this.getTypes());
        session.setTrainerId(this.getIdtrainer().getId());
        return session;
    }
}
