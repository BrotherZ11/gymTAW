<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>
<%@ page import="com.example.gymtaw.entity.ClientExerciseEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 16/06/2024
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ExerciseEntity ejercicio = (ExerciseEntity) request.getAttribute("ejercicio");
    List<ClientExerciseEntity> clientExercise = (List<ClientExerciseEntity>) request.getAttribute("clientExercise");
%>
<html>
<head>
    <title><%=ejercicio != null ? ejercicio.getName() : "Detalles del ejercicio"%></title>
</head>

<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">
<%
    if (ejercicio != null && clientExercise != null) {
        for (ClientExerciseEntity ce : clientExercise) {
%>
<h2><%=ejercicio.getName()%></h2>
<table border="1">
    <tr>
        <th>Repeticiones</th>
        <td><%=ce.getReps() != null ? ce.getReps() : "N/A"%></td>
    </tr>

    <tr>
        <th>Sets</th>
        <td><%=ce.getSets() != null ? ce.getSets() : "N/A"%></td>
    </tr>

    <tr>
        <th>Peso</th>
        <td><%=ce.getWeight() != null ? ce.getWeight() : "N/A"%></td>
    </tr>

    <tr>
        <th>Calorías</th>
        <td><%=ce.getCalories() != null ? ce.getCalories() : "N/A"%></td>
    </tr>

    <tr>
        <th>Distancia</th>
        <td><%=ce.getDistance() != null ? ce.getDistance() : "N/A"%></td>
    </tr>

</table>
<%
        }
    } else {
        out.println("No se han encontrado detalles del ejercicio.");
    }
%>
</body>
</html>
