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
public class TypeHasRoutineEntityId implements java.io.Serializable {
    private static final long serialVersionUID = 6054324422258213997L;
    @Column(name = "type_idtype", nullable = false)
    private Integer typeIdtype;

    @Column(name = "routine_idroutine", nullable = false)
    private Integer routineIdroutine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TypeHasRoutineEntityId entity = (TypeHasRoutineEntityId) o;
        return Objects.equals(this.routineIdroutine, entity.routineIdroutine) &&
                Objects.equals(this.typeIdtype, entity.typeIdtype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineIdroutine, typeIdtype);
    }

}