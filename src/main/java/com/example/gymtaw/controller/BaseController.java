//Gonzalo Muñoz Rubio 100%
package com.example.gymtaw.controller;


import jakarta.servlet.http.HttpSession;

public class BaseController {
    protected boolean estaAutenticado(HttpSession session){return session.getAttribute("usuario") != null;}
}
