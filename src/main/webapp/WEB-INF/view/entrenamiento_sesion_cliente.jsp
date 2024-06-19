<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SessionEntity> sesiones = (List<SessionEntity>) request.getAttribute("sesiones");
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
%>
<html>
<head>
    <title>Sesiones</title>
</head>
<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">

<h1>Sesiones del Cliente</h1>

<% if (sesiones != null) { %>
<% for(SessionEntity s : sesiones) { %>
<h2><%= s.getName() %></h2>
<%
    if (ejercicios != null) {
        for(ExerciseEntity e : ejercicios) {
%>
<table border="1">
    <tr>
        <th>Nombre ejercicio</th>
        <th>Descripcion</th>
        <th>Video</th>
        <th>Valoración</th>
    </tr>
    <tr>
        <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= e.getId() %>"><%= e.getName() %></a></td>
        <td><%= e.getDescription() != null ? e.getDescription() : "N/A" %></td>
        <td><%= e.getVideo() != null ? e.getVideo() : "N/A" %></td>
        <%
            List<ValoracionEntity> val = e.getValoracions();
            boolean valorado = false;
            if (val != null) {
                for (ValoracionEntity v : val) {
                    if (v.getDone() == 1) {
                        valorado = true;
        %>
        <td align="center"><%= v.getStars() %></td>
        <%
                        break;
                    }
                }
            }
            if (!valorado) {
        %>
        <td align="center"><a href="valorar?idEjercicio=<%= e.getId() %>">Valorar</a></td>
        <%
            }
        %>
    </tr>
</table>
<br>
<%
        }
    } else {
        out.println("No hay ejercicios para esta sesión.");
    }
%>
<% } %>
<% } else { %>
<p>No hay sesiones disponibles.</p>
<% } %>
</body>
</html>
