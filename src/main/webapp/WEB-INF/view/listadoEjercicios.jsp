<%@ page import="com.example.gymtaw.dto.Exercise" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Gonzalo Muñoz Rubio
  Date: 23/06/2024
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Exercise> ejercicios = (List<Exercise>) request.getAttribute("ejercicios");%>
<html>
<head>
    <title>Listado de ejercicios</title>
</head>
<body>
<h1>Listado de ejercicios</h1>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Video</th>
        <th>Tipo</th>
    </tr>
    <%for(Exercise e: ejercicios){%>
    <tr>
        <td><%= e.getName() %></td>
        <td><%= e.getDescription() %></td>
        <td><%= e.getVideo() %></td>
        <td><%= e.getTypeIdtype().getName() %></td>
        <td><a href="/users/editarEjercicio?id=<%=e.getId()%>">Editar</a></td>
        <td><a href="/users/borrarEjercicio?id=<%=e.getId()%>">Borrar</a></td>
    </tr>
    <%}%>
</table>
<a href="/users/crearEjercicio">Crear Ejercicio</a>
<a href="/users/">Volver</a>
</body>
</html>
