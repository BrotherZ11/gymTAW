<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.dto.User" %>
<%@ page import="com.example.gymtaw.dto.Exercise" %>
<%@ page import="com.example.gymtaw.dto.ClientExercise" %>
<%@ page import="com.example.gymtaw.dto.Valoracion" %><%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User cliente = (User) request.getAttribute("cliente");
    List<Exercise> ejercicios = (List<Exercise>) request.getAttribute("ejercicios");
    List<ClientExercise> ejerciciosCliente = (List<ClientExercise>) request.getAttribute("ejerciciosCliente");
    List<Valoracion> valoraciones = (List<Valoracion>) request.getAttribute("valoraciones");
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
    for(Valoracion valoracion : valoraciones){
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
        <%
            ClientExercise ejercicioCliente = null;
        for(ClientExercise ejercicioDelCliente : ejerciciosCliente){
            if(ejercicioDelCliente.getExercise().getId() == valoracion.getExercise().getId()){
                ejercicioCliente = ejercicioDelCliente;
            }
        }
        %>

        <td><%=ejercicioCliente.getReps()%></td>
        <td><%=ejercicioCliente.getSets()%></td>
        <td><%=ejercicioCliente.getWeight()%></td>
        <td><%=ejercicioCliente.getCalories()%></td>
        <td><%=ejercicioCliente.getDistance()%> Km</td>
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
                    <td><%=valoracion.getDone()==1?"Completado":"Sin completar"%></td>
                    <td><%=valoracion.getStars() == null ? "Sin valoración" : valoracion.getStars()%></td>
                    <td><%=((valoracion.getReview() == null) || (valoracion.getReview().equals("No tienes reviews aún."))) ? "Sin review" : valoracion.getReview()%></td>
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
