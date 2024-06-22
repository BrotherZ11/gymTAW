<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.gymtaw.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserEntity cliente = (UserEntity) session.getAttribute("cliente");
    ExerciseEntity ejercicio = (ExerciseEntity) request.getAttribute("ejercicio");
    Integer idSesion = (Integer) request.getAttribute("idSesion");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    ClientExerciseEntity ejercicioCliente = (ClientExerciseEntity) request.getAttribute("ejercicioCliente");
%>
<html>
<head>
    <title>Datos del ejercicio</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1>Datos del ejercicio <%=ejercicio.getName()%></h1>
<a href="/home/trainer">Home</a><br/>
<a href="exercise_client?idSesion=<%=idSesion%>">Atras</a>
<form method="post" action="/home/trainer/guardar_ejercicio">
    <input type="hidden" name="idEjercicio" value="<%= ejercicio.getId() %>">
    <input type="hidden" name="idSesion" value="<%=idSesion%>">
    <input type="hidden" name="idRutina" value="<%= idRutina %>">
    <table border="0">
        <%
            if(ejercicioCliente!=null){
        %>
        <tr>
            <td>Repeticiones:</td>
            <td><input type="text" name="reps" size="100" maxlength="100" value="<%= ejercicioCliente.getReps() %>" /> </td>
        </tr>
        <tr>
            <td>Sets:</td>
            <td><input type="text" name="sets" size="100"  maxlength="100" value="<%= ejercicioCliente.getSets() %>" /></td>
        </tr>
        <tr>
            <td>Peso:</td>
            <td><input type="text" name="peso" size="100"  maxlength="100" value="<%= ejercicioCliente.getWeight() %>" /></td>
        </tr>
        <tr>
            <td>Calorias:</td>
            <td><input type="number" name="calorias" size="100"  maxlength="100" value="<%= ejercicioCliente.getCalories() %>" /></td>
        </tr>
        <tr>
            <td>Distancia:</td>
            <td><input type="number" name="distancia" size="100"  maxlength="100" value="<%= ejercicioCliente.getDistance() %>" /></td>
        </tr>
        <%
            }else{
        %>
        <tr>
            <td>Repeticiones:</td>
            <td><input type="text" name="reps" size="100" maxlength="100" value="" /> </td>
        </tr>
        <tr>
            <td>Sets:</td>
            <td><input type="text" name="sets" size="100"  maxlength="100" value="" /></td>
        </tr>
        <tr>
            <td>Peso:</td>
            <td><input type="text" name="peso" size="100"  maxlength="100" value="" /></td>
        </tr>
        <tr>
            <td>Calorias:</td>
            <td><input type="number" name="calorias" size="100"  maxlength="100" value="0,0" /></td>
        </tr>
        <tr>
            <td>Distancia:</td>
            <td><input type="number" name="distancia" size="100"  maxlength="100" value="0,0" /></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="2"> <button>Guardar</button></td>
        </tr>
    </table>
</form>
</body>
</html>
