<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.RoutineEntity" %>
<%@ page import="com.example.gymtaw.entity.SessionEntity" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Ejercicios del Cliente</h2>
<table border="1">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Video</th>
        <th>Valoración</th>

    </tr>
    </thead>
    <tbody>
    <% for ( ExerciseEntity e : ejercicios) { %>
    <td><%=e.getName()%></td>
    <td><%=e.getDescription()%></td>
    <td><%=e.getVideo().%></td>
    <td>Valoracion</td>
    <% } %>
    <tr>

    </tr>
    </tbody>
</table>
</body>
</html>
