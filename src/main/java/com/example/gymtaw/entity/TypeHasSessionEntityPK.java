package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class TypeHasSessionEntityPK implements Serializable {
    @Column(name = "type_idtype", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeIdtype;
    @Column(name = "session_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;

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
        TypeHasSessionEntityPK that = (TypeHasSessionEntityPK) o;
        return Objects.equals(typeIdtype, that.typeIdtype) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeIdtype, sessionId);
    }
}
