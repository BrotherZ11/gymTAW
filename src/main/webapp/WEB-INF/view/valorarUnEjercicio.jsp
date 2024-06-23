<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>
<%@ page import="com.example.gymtaw.entity.ValoracionEntity" %>
<%@ page import="com.example.gymtaw.dto.Exercise" %><%--
  Created by IntelliJ IDEA.
  User: W10
  Date: 19/06/2024
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //Marta Granado Rodríguez
    Exercise ejercicio = (Exercise) request.getAttribute("ejercicio");
    Integer idSesion = (Integer) request.getAttribute("idSesion");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
%>
<html>
<head>
    <title><%=ejercicio.getName()%></title>
</head>
<body>
<%if(idSesion!=-1 && idRutina!=-1){%>
<a href="http://localhost:8080/home/cliente/ejercicio?idRutina=<%= idRutina%>&idSesion=<%=idSesion%>">Volver atrás</a>
<%}else{%>
<a href="http://localhost:8080/home/cliente/valorar">Volver atrás</a>
<%}%>
<h1>Valorar "<%=ejercicio.getName()%>"</h1>
<form method="post" action="/home/cliente/guardar">
    <input type="hidden" name="exerciseId" value="<%=ejercicio.getId()%>">
    <input type="hidden" name="idSesion" value="<%=idSesion%>">
    <input type="hidden" name="idRutina" value="<%=idRutina%>">
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
