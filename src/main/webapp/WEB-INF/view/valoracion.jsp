<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.ValoracionEntity" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>

<html>
<head>
    <title>Valoraciones</title>
</head>
<body>
<h1>Valoraciones del Ejercicio</h1>

<% List<ValoracionEntity> valoraciones = (List<ValoracionEntity>) request.getAttribute("valoraciones");
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios"); %>

<table border="1">
    <thead>
    <tr>
        <th>Usuario</th>
        <th>Estrellas</th>
        <th>Reseña</th>
    </tr>
    </thead>
    <tbody>
    <% for (ValoracionEntity valoracion : valoraciones) { %>
    <tr>
        <td><%= valoracion.getUserByUserId().getName() %></td>
        <td><%= valoracion.getStars() %></td>
        <td><%= valoracion.getReview() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

<h2>Añadir Valoración</h2>
<form action="/home/cliente/rateExercise" method="post">
    <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>">
    <input type="hidden" name="exerciseId" value="<%= request.getAttribute("exerciseId") %>">
    <label for="stars">Estrellas:</label>
    <select name="stars" id="stars">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
    </select>
    <br>
    <label for="review">Reseña:</label>
    <textarea name="review" id="review"></textarea>
    <br>
    <button type="submit">Enviar Valoración</button>
</form>

</body>
</html>
