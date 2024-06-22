<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.gymtaw.dto.Session" %>
<%@ page import="com.example.gymtaw.dto.Exercise" %>
<%@ page import="com.example.gymtaw.dto.User" %>
<%@ page import="com.example.gymtaw.dto.Valoracion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Session> sesiones = (List<Session>) request.getAttribute("sesiones");
    List<Exercise> ejercicios = (List<Exercise>) request.getAttribute("ejercicios");
    List<Valoracion> valoraciones = (List<Valoracion>) request.getAttribute("valoraciones");
    Integer idSesion = (Integer) request.getAttribute("idSesion");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    User usuario= (User) request.getAttribute("usuario");
    Integer idCliente = usuario.getId();
%>
<html>
<head>
    <title>Sesiones</title>
</head>
<body>
<a href="http://localhost:8080/home/cliente/sesionesSemanales?idRutina=<%= idRutina%>">Volver atrás</a>
<h1>Sesiones del Cliente</h1>

<% if (sesiones != null) { %>
<% for (Session s : sesiones) { %>
<h2><%= s.getName() %></h2>
<% if (ejercicios != null) { %>
<table border="1">
    <tr>
        <th>Nombre ejercicio</th>
        <th>Descripcion</th>
        <th>Video</th>
        <th>Completado</th>
        <th>Valoración</th>
        <th></th>
    </tr>
    <% for (Exercise e : ejercicios) { %>
    <tr>
        <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= e.getId() %>&idRutina=<%=idRutina%>&idSesion=<%=idSesion%>"><%= e.getName() %></a></td>
        <td><%= e.getDescription() != null ? e.getDescription() : "N/A" %></td>
        <td><%= e.getVideo() != null ? e.getVideo() : "N/A" %></td>
        <td align="center">
            <%
                boolean isDone = false;

                if (valoraciones != null) {
                    for (Valoracion v : valoraciones) {
                        if (v.getDone() == 1 && v.getUser().getId().equals(idCliente) && v.getExercise().getId().equals(e.getId())) {
                            isDone = true;
                            break;  // Exit loop once match is found
                        }
                    }
                }
            %>
            <form action="/home/cliente/guardarCompletado" method="post">
                <input type="hidden" name="idEjercicio" value="<%= e.getId() %>" />
                <input type="hidden" name="idSesion" value="<%= idSesion %>" />
                <input type="hidden" name="idRutina" value="<%= idRutina %>" />
                <input type="checkbox" name="done" value="1" <% if (isDone) { %>checked disabled<% } %> />
                <% if (!isDone) { %>
                <button type="submit">Guardar</button>
                <% } %>
            </form>
        </td>
        <%
            boolean valorado = false;
            Integer estrellas = null;

            if (valoraciones != null) {
                for (Valoracion v : valoraciones) {
                    if (v.getDone() == 1 && v.getUser().getId().equals(idCliente) && v.getExercise().getId().equals(e.getId())) {
                        if (v.getStars() != null) {
                            valorado = true;
                            estrellas = v.getStars();
                            break;  // Exit loop once match is found
                        }
                    }
                }
            }
        %>
        <td align="center"><%= valorado ? estrellas : "Completar para valorar" %></td>
        <td align="center">
            <% if (isDone && !valorado) { %>
            <a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idSesion=<%=s.getId()%>&idRutina=<%=idRutina%>">Valorar</a>
            <% } else if (valorado) { %>
            <a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idSesion=<%=s.getId()%>&idRutina=<%=idRutina%>">Editar valoracion</a>
            <% } else { %>
            -
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<br>
<% } else { %>
No hay ejercicios para esta sesión.
<% } %>
<% } %>
<% } else { %>
<p>No hay sesiones disponibles.</p>
<% } %>

</body>
</html>
