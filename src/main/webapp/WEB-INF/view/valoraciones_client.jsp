<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.gymtaw.entity.UserEntity" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>
<%@ page import="com.example.gymtaw.entity.ClientExerciseEntity" %>
<%@ page import="com.example.gymtaw.entity.ValoracionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserEntity cliente = (UserEntity) request.getAttribute("cliente");
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
    List<ClientExerciseEntity> ejerciciosCliente = (List<ClientExerciseEntity>) request.getAttribute("ejerciciosCliente");
    List<ValoracionEntity> valoraciones = (List<ValoracionEntity>) request.getAttribute("valoraciones");
%>
<html>
<head>
    <title>Valoraciones del cliente</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1>Valoraciones de <%=cliente.getName()%>  <%=cliente.getSurname()%></h1>
<p><a href="/home/trainer">Home</a> / <a href="clients">Clientes</a> / Valoraciones <%=cliente.getName()%> <%=cliente.getSurname()%></p><br>
<%
    int i = 0;
    for(ClientExerciseEntity ejercicioCliente : ejerciciosCliente){
%>
<table border="1">
    <tr>
        <th>NOMBRE</th>
        <th>REPS</th>
        <th>SETS</th>
        <th>PESO</th>
        <th>CALORIAS</th>
        <th>DISTANCIA</th>
    </tr>
    <tr>
        <td><%=ejercicios.get(i).getName()%></td>
        <td><%=ejercicioCliente.getReps()%></td>
        <td><%=ejercicioCliente.getSets()%></td>
        <td><%=ejercicioCliente.getWeight()%></td>
        <td><%=ejercicioCliente.getCalories()%></td>
        <td><%=ejercicioCliente.getDistance()%></td>
    </tr>
    <tr>
        <td colspan="6">
            <table border="1" width="100%">
                <tr>
                    <th>COMPLETADO</th>
                    <th>VALORACION</th>
                    <th>REVIEW</th>
                </tr>
                <tr>
                    <td><%=valoraciones.get(i).getDone()==1?"Completado":"Sin completar"%></td>
                    <td><%=valoraciones.get(i).getStars()%></td>
                    <td><%=valoraciones.get(i).getReview()%></td>
                </tr>
            </table>
        </td>
    </tr>
</table><br>
<%
        i++;
    }
%>
</body>
</html>
