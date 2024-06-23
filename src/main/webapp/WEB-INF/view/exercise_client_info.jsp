<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.gymtaw.dto.User" %>
<%@ page import="com.example.gymtaw.dto.Exercise" %>
<%@ page import="com.example.gymtaw.dto.ClientExercise" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Exercise ejercicio = (Exercise) request.getAttribute("ejercicio");
    Integer idSesion = (Integer) request.getAttribute("idSesion");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    ClientExercise ejercicioCliente = (ClientExercise) request.getAttribute("ejercicioCliente");
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
<a href="exercise_client?idSesion=<%=idSesion%>&idRutina=<%=idRutina%>">Atras</a><br>
<form method="post" action="/home/trainer/guardar_ejercicio">
    <input type="hidden" name="idEjercicio" value="<%= ejercicio.getId() %>">
    <input type="hidden" name="idSesion" value="<%=idSesion%>">
    <input type="hidden" name="idRutina" value="<%= idRutina %>">
    <table border="0">
        <%
            String reps = "";
            String sets = "";
            String peso = "";
            Double calorias = 0.0;
            Double distancia = 0.0;

            if(ejercicioCliente!=null) {
                reps = ejercicioCliente.getReps();
                sets = ejercicioCliente.getSets();
                peso = ejercicioCliente.getWeight();
                calorias = ejercicioCliente.getCalories();
                distancia = ejercicioCliente.getDistance();
            }
        %>
        <tr>
            <td>Repeticiones:</td>
            <td><input type="text" name="reps" size="100" maxlength="100" value="<%= reps %>" /> </td>
        </tr>
        <tr>
            <td>Sets:</td>
            <td><input type="text" name="sets" size="100"  maxlength="100" value="<%= sets %>" /></td>
        </tr>
        <tr>
            <td>Peso:</td>
            <td><input type="text" name="peso" size="100"  maxlength="100" value="<%= peso %>" /></td>
        </tr>
        <tr>
            <td>Calorias:</td>
            <td><input type="number" name="calorias" size="100"  maxlength="100" step="0.1" value="<%= calorias %>" /></td>
        </tr>
        <tr>
            <td>Distancia(Km):</td>
            <td><input type="number" name="distancia" size="100"  maxlength="100" step="0.1" value="<%= distancia %>" /></td>
        </tr>
        <tr>
            <td colspan="2"> <button>Guardar</button></td>
        </tr>
    </table>
</form>
</body>
</html>
