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
    List<Exercise> ejercicios = (List<Exercise>) request.getAttribute("ejercicios");
    Integer idSesion = -1;
    Integer idRutina = -1;
    User usuario= (User) session.getAttribute("usuario");
    List<Valoracion> valoraciones = (List<Valoracion>) request.getAttribute("valoraciones");
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
        <option value="">Todas</option>
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
<% if (!ejercicios.isEmpty()) { %>
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
    <% for (Exercise e : ejercicios) { %>
    <tr>
        <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= e.getId() %>&idRutina=<%=idRutina%>&idSesion=<%=idSesion%>"><%= e.getName()%></a></td>
        <td><%=e.getDescription()%></td>
        <td><%=e.getVideo()%></td>
        <td align="center">
            <%
                boolean isDone = false;

                if (valoraciones != null) {
                    for (Valoracion v : valoraciones) {
                        if (v.getDone() == 1 && v.getUser().getId().equals(usuario.getId()) && v.getExercise().getId().equals(e.getId())) {
                            isDone = true;
                            break;
                        }
                    }
                }
            %>
            <form action="/home/cliente/guardarCompletado" method="post">
                <input type="hidden" name="idEjercicio" value="<%= e.getId() %>" />
                <input type="hidden" name="idSesion" value="<%= idSesion %>" />
                <input type="hidden" name="idRutina" value="<%= idRutina %>" />
                <input type="checkbox" name="done" value="1" <% if (isDone) { %>checked disabled<% } else { %> onchange="this.form.submit()"<% } %> />
            </form>
        </td>
        <%
            boolean valorado = false;
            Integer estrellas = null;
            String review = "No tienes review aún.";

            if (valoraciones != null) {
                for (Valoracion v : valoraciones) {
                    if (v.getDone() == 1 && v.getUser().getId().equals(usuario.getId()) && v.getExercise().getId().equals(e.getId())) {
                        if (v.getStars() != null) {
                            valorado = true;
                            estrellas = v.getStars();
                        }
                        if (v.getReview() != null) {
                            review = v.getReview();
                        }
                        break;
                    }
                }
            }
        %>
        <td align="center"><%= valorado ? estrellas : "Completar para valorar" %></td>
        <td>
            <% if (isDone) { %>
            <form method="post" action="/home/cliente/guardarReview">
                <input type="hidden" id="userId" name="userId" value="<%= usuario.getId() %>">
                <input type="hidden" id="exerciseId" name="exerciseId" value="<%= e.getId() %>">
                <input type="text" name="review" value="<%= review %>"/>
                <button type="submit">Guardar</button>
            </form>
            <% } else { %>
            -
            <% } %>
        </td>
        <td>
            <% if (valorado) { %>
            <a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idSesion=<%=idSesion%>&idRutina=<%=idRutina%>">Editar valoracion</a>
            <% } else { %>
            -
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<% } else { %>
<h3>No hay ejercicios con esa valoración.</h3>
<% } %>
</body>
</html>
