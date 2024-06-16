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
<%ExerciseEntity ejercicio = (ExerciseEntity) request.getAttribute("ejercicio");
    List<ClientExerciseEntity> clientExercise = (List<ClientExerciseEntity>) ejercicio.getClientExercisesById();%>
<html>
<head>
    <title><%=ejercicio.getName()%></title>

</head>

<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">
<%
    for(ClientExerciseEntity ce : clientExercise){
%>
<h2><%=ejercicio.getName()%></h2>
<table border="1">
    <tr>
        <th>Repeticiones</th>
        <td><%=ce.getReps()%></td>
    </tr>

    <tr>
        <th>Sets</th>
        <td><%=ce.getSets()%></td>
    </tr>

    <tr>
        <th>Peso</th>
        <td><%=ce.getWeight()%></td>
    </tr>

    <tr>
        <th>Calorias</th>
        <td><%=ce.getCalories()%></td>
    </tr>

    <tr>
        <th>Distancia</th>
        <td><%=ce.getDistance()%></td>
    </tr>

</table>
<%}%></br>

</body>
</html>
