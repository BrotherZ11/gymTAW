package com.example.gymtaw.ui;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usuario {
    protected String user;
    protected String password;

    public Usuario () {}

    public Usuario (String user, String password) {
        this.user = user;
        this.password = password;
    }

}
