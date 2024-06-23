//David Zarzavilla Borrego
package com.example.gymtaw.entity;

import com.example.gymtaw.dto.DTO;
import com.example.gymtaw.dto.TypeHasSession;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "type_has_session")
public class TypeHasSessionEntity implements DTO<TypeHasSession> {
    @EmbeddedId
    private TypeHasSessionEntityId id;

    @MapsId("typeIdtype")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_idtype", nullable = false)
    private TypeEntity typeIdtype;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    public TypeHasSession toDTO() {
        TypeHasSession typeHasSession = new TypeHasSession();
        typeHasSession.setId(this.id.toDTO());
        typeHasSession.setTypeIdtype(this.typeIdtype.toDTO());
        typeHasSession.setSession(this.session.toDTO());
        return typeHasSession;
    }

}