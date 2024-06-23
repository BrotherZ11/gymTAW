<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.RoutineEntity" %>
<%@ page import="com.example.gymtaw.dto.Routine" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //Marta Granado Rodríguez 100%
    List<Routine> rutinas = (List<Routine>) request.getAttribute("rutinas");
%>
<html>
<head>
    <title>Rutinas</title>
</head>
<body>
<a href="/home/cliente">Volver atrás</a>
<% if (rutinas.isEmpty()) {%>
<div align="center">
    <h1>NO TIENES RUTINAS</h1>
</div>
<% } else { %>

<h2>Rutinas del Cliente</h2>
<table border="1">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Descripción</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (Routine r : rutinas) { %>
    <tr>
        <td><%=r.getName()%></td>
        <td><%=r.getDescription()%></td>
        <td><a href="/home/cliente/sesionesSemanales?idRutina=<%=r.getId()%>">Más detalles</a></td>
    </tr>
    <% } %>
    </tbody>
</table>


<% } %>
</body>
</html>
