package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class RoutineHasSessionEntityId implements java.io.Serializable {
    private static final long serialVersionUID = 77386757019376885L;
    @Column(name = "routine_id", nullable = false)
    private Integer routineId;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Column(name = "day", nullable = false)
    private Integer day;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoutineHasSessionEntityId entity = (RoutineHasSessionEntityId) o;
        return Objects.equals(this.sessionId, entity.sessionId) &&
                Objects.equals(this.routineId, entity.routineId) &&
                Objects.equals(this.day, entity.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, routineId, day);
    }

}