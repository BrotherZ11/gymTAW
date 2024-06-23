//David Zarzavilla Borrego 100%
package com.example.gymtaw.dto;

import lombok.Data;

@Data
public class TypeHasSession {
    private TypeHasSessionId id;
    private Type typeIdtype;
    private Session session;
}
