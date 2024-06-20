<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>
<%@ page import="com.example.gymtaw.entity.ValoracionEntity" %><%--
  Created by IntelliJ IDEA.
  User: W10
  Date: 19/06/2024
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ExerciseEntity ejercicio = (ExerciseEntity) request.getAttribute("ejercicio");
    ValoracionEntity nuevaValoracion = (ValoracionEntity) request.getAttribute("nuevaValoracion");
    Integer idCliente = (Integer) request.getAttribute("idCliente");
    Integer idSesion = (Integer) request.getAttribute("idSesion");
%>
<html>
<head>
    <title><%=ejercicio.getName()%></title>
</head>
<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">
<h1>Valorar "<%=ejercicio.getName()%>"</h1>
<form method="post" action="/home/cliente/guardar">
    <input type="hidden" name="exerciseId" value="<%=ejercicio.getId()%>">
    <input type="hidden" name="idCliente" value="<%=idCliente%>">
    <input type="hidden" name="idSesion" value="<%=idSesion%>">
    <p>Por favor, selecciona una calificación:</p>
    <label>
        <input type="radio" name="stars" value="1"> 1
    </label><br>
    <label>
        <input type="radio" name="stars" value="2"> 2
    </label><br>
    <label>
        <input type="radio" name="stars" value="3"> 3
    </label><br>
    <label>
        <input type="radio" name="stars" value="4"> 4
    </label><br>
    <label>
        <input type="radio" name="stars" value="5"> 5
    </label><br>
    <button type="submit">Enviar</button>
</form>
</body>
</html>
