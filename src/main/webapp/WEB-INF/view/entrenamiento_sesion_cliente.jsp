<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.RoutineEntity" %>
<%@ page import="com.example.gymtaw.entity.SessionEntity" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<SessionEntity> sesiones = (List<SessionEntity>) request.getAttribute("sesiones"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<h2>Sesiones del Cliente</h2>
<table border="1">
    <thead>
    <tr>
        <th>Nombre</th>
        <th></th>

    </tr>
    </thead>
    <tbody>
    <% for (SessionEntity s : sesiones) { %>
    <td><%=s.getName()%></td>

    <td><a href="/home/cliente/ejercicios?id=<%=s.getId()%>">Más detalles</a></td>
    <% } %>
    <tr>

    </tr>
    </tbody>
</table>
</body>
</html>
