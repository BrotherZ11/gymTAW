<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.gymtaw.dto.User" %>
<%@ page import="com.example.gymtaw.dto.Exercise" %>
<%@ page import="com.example.gymtaw.dto.Valoracion" %>
<%@ page import="com.example.gymtaw.dto.ValoracionId" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Marta Granado Rodríguez 100%
    List<User> entrenadores = (List<User>) request.getAttribute("entrenadores");
    Integer idSesion = -1;
    Integer idRutina = -1;
    User usuario = (User) session.getAttribute("usuario");
    List<Valoracion> valoraciones = (List<Valoracion>) request.getAttribute("valoraciones");
    Integer idCliente = usuario.getId();

%>
<html>
<head>
    <title>Valorar</title>
</head>
<body>
<a href="http://localhost:8080/home/cliente">Volver atrás</a>

<h1>Filtros</h1>
<form:form method="post" action="/home/cliente/filtrarValoraciones" modelAttribute="filtro">

    <label for="stars">Valoración:</label>
    <select id="stars" name="stars">
        <option value="0">Todas</option>
        <option value="1" ${filtro.stars == 1 ? 'selected' : ''}>1 estrella</option>
        <option value="2" ${filtro.stars == 2 ? 'selected' : ''}>2 estrellas</option>
        <option value="3" ${filtro.stars == 3 ? 'selected' : ''}>3 estrellas</option>
        <option value="4" ${filtro.stars == 4 ? 'selected' : ''}>4 estrellas</option>
        <option value="5" ${filtro.stars == 5 ? 'selected' : ''}>5 estrellas</option>
    </select>
    <button type="submit">Filtrar</button>
</form:form>

<form:form method="post" action="/home/cliente/filtrarEjercicio" modelAttribute="filtroEj">

    Nombre del ejercicio: <form:input path="nombre" />
    <button type="submit">Filtrar</button>
</form:form>

<h1>Valoraciones de ejercicios</h1>
<% for (User entrenador : entrenadores) {
    Integer idEntrenador = entrenador.getId(); %>
<% if (!valoraciones.isEmpty()) { %>
<%= entrenador.getName() + " " + entrenador.getSurname() %>

<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Video</th>
        <th>Completado</th>
        <th>Valoración</th>
        <th>Review</th>
        <th></th>
    </tr>
    <%
        for (Valoracion val : valoraciones) {
            if(val.getTrainer().getId() == entrenador.getId()){
    %>
    <tr>
        <td>
            <a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= val.getExercise().getId() %>&idRutina=<%=idRutina%>&idSesion=<%=idSesion%>"><%= val.getExercise().getName()%></a>
        </td>
        <td>
            <%=val.getExercise().getDescription()%>
        </td>
        <td>
            <a href="<%=val.getExercise().getVideo()%>"><%=val.getExercise().getVideo()%></a>
        </td>
        <td>
            <form action="/home/cliente/guardarCompletado" method="post">
                <input type="hidden" name="idEjercicio" value="<%= val.getExercise().getId() %>" />
                <input type="hidden" name="idSesion" value="<%= idSesion %>" />
                <input type="hidden" name="idRutina" value="<%= idRutina %>" />
                <input type="checkbox" name="done" value="1" <% if (val.getDone()==1) { %>checked disabled<% } %> />
                <% if (val.getDone()==0) { %>
                <button type="submit">Guardar</button>
                <% } %>
            </form>
        </td>
        <td>
            <% if (val.getDone()==1 && val.getStars()==null) { %>
            <a href="valorarEjercicio?idEjercicio=<%= val.getExercise().getId() %>&idSesion=<%= idSesion %>&idRutina=<%= idRutina %>">Valorar</a>
            <% } else if (val.getStars()!=null) { %>
            <%= val.getStars() %>
            <% } else { %>
            <p>Completar para valorar</p>
            <% } %>
        </td>
        <td>
            <% if (val.getDone()==1) { %>
            <form method="post" action="/home/cliente/guardarReview">
                <input type="hidden" id="userId" name="userId" value="<%= usuario.getId() %>">
                <input type="hidden" id="entrenadorId" name="entrenadorId" value="<%= idEntrenador %>">
                <input type="hidden" id="exerciseId" name="exerciseId" value="<%= val.getExercise().getId() %>">
                <input type="text" name="review" value="<%= val.getReview()==null?"No hay review":val.getReview() %>"/>
                <button type="submit">Guardar</button>
            </form>
            <% } else { %>
            -
            <% } %>
        </td>
        <td>
            <% if (val.getStars()!=null) { %>
            <a href="valorarEjercicio?idEjercicio=<%= val.getExercise().getId() %>&idSesion=<%= idSesion %>&idRutina=<%= idRutina %>">Editar valoracion</a>
            <% } else { %>
            -
            <% } %>
        </td>
    </tr>
    <%
            }
        }
    %>
</table><br>
<% } else { %>
<h3>No hay ejercicios con esa valoración.</h3>
<% } %>
<% } %>
</body>
</html>
