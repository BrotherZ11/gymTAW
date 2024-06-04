package com.example.gymtaw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "type_has_session", schema = "gymtaw", catalog = "")
@IdClass(TypeHasSessionEntityPK.class)
public class TypeHasSessionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_idtype", nullable = false)
    private Integer typeIdtype;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "session_id", nullable = false)
    private Integer sessionId;
    @ManyToOne
    @JoinColumn(name = "type_idtype", referencedColumnName = "idtype", nullable = false)
    private TypeEntity typeByTypeIdtype;
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
    private SessionEntity sessionBySessionId;

    public Integer getTypeIdtype() {
        return typeIdtype;
    }

    public void setTypeIdtype(Integer typeIdtype) {
        this.typeIdtype = typeIdtype;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeHasSessionEntity that = (TypeHasSessionEntity) o;
        return Objects.equals(typeIdtype, that.typeIdtype) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeIdtype, sessionId);
    }

    public TypeEntity getTypeByTypeIdtype() {
        return typeByTypeIdtype;
    }

    public void setTypeByTypeIdtype(TypeEntity typeByTypeIdtype) {
        this.typeByTypeIdtype = typeByTypeIdtype;
    }

    public SessionEntity getSessionBySessionId() {
        return sessionBySessionId;
    }

    public void setSessionBySessionId(SessionEntity sessionBySessionId) {
        this.sessionBySessionId = sessionBySessionId;
    }
}
