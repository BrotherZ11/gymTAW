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
public class TypeHasSessionEntityId implements java.io.Serializable {
    private static final long serialVersionUID = 2182906600405427279L;
    @Column(name = "type_idtype", nullable = false)
    private Integer typeIdtype;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TypeHasSessionEntityId entity = (TypeHasSessionEntityId) o;
        return Objects.equals(this.sessionId, entity.sessionId) &&
                Objects.equals(this.typeIdtype, entity.typeIdtype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, typeIdtype);
    }

}