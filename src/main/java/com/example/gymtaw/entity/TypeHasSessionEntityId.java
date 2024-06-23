package com.example.gymtaw.entity;

import com.example.gymtaw.dto.TypeHasSessionId;
import com.example.gymtaw.dto.DTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TypeHasSessionEntityId implements Serializable, DTO<TypeHasSessionId> {
    private static final long serialVersionUID = -7244177501383681571L;
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

    public TypeHasSessionId toDTO() {
        TypeHasSessionId typeHasSessionId = new TypeHasSessionId();
        typeHasSessionId.setTypeIdtype(this.getTypeIdtype());
        typeHasSessionId.setSessionId(this.getSessionId());
        return typeHasSessionId;
    }

}