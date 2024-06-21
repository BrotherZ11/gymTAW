<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.RoutineEntity" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<RoutineEntity> rutinas = (List<RoutineEntity>) request.getAttribute("rutinas");
    Integer idCliente= (Integer) request.getAttribute("idCliente");
%>
<html>
<head>
    <title>Rutinas</title>
</head>
<body>
<a href="http://localhost:8080/home/cliente?idCliente=<%= idCliente %>">Volver atrás</a>
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
    <% for (RoutineEntity r : rutinas) { %>
        <td><%=r.getName()%></td>
        <td><%=r.getDescription()%></td>
        <td><a href="/home/cliente/sesionesSemanales?idRutina=<%=r.getId()%>&idCliente=<%=idCliente%>">Más detalles</a></td>
    <% } %>
        <tr>

        </tr>
    </tbody>
</table>


<% } %>
</body>
</html>
