<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
    Integer idSesion = -1;
    Integer idRutina = -1;
    UserEntity usuario= (UserEntity) session.getAttribute("usuario");
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
    <%for(ExerciseEntity e : ejercicios){%>
    <tr>
        <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= e.getId() %>&idRutina=<%=idRutina%>&idSesion=<%=idSesion%>"><%= e.getName()%></a></td>
        <td><%=e.getDescription()%></td>
        <td><%=e.getVideo()%></td>
        <td align="center">
            <%
                boolean isDone = false;
                Set<ValoracionEntity> val = e.getValoracions();


                if (val != null) {
                    for (ValoracionEntity v : val) {
                        if (v.getDone() == 1 && v.getUser().getId().equals(usuario.getId())) {
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
            if (val != null) {

                for (ValoracionEntity v : val) {
                    if (v.getDone() == 1 && v.getUser().getId().equals(usuario.getId())) {
                        if (v.getStars() != null) {
                            valorado = true;
        %>
        <td align="center"><%= v.getStars() %></td>
        <%
        } else {
        %>

        <%
                        }
                        break;
                    }
                }
            }
            if (!isDone) {
        %>
        <td align="center">Completar para valorar</td>
        <%
        } else if (!valorado) {

        %>
        <td align="center"><a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idSesion=<%=idSesion%>&idRutina=<%=idRutina%>">Valorar</a></td>
        <%
            }
        %>
        <td>
            <% if (isDone) { %>
            <%
                if (val != null) {
                    for (ValoracionEntity v : val) {
                        if (v.getUser().getId().equals(usuario.getId())) {
                            String review = v.getReview();
                            if (review == null) {
                                review = "No tienes review aún.";
                            }
            %>
            <form method="post" action="/home/cliente/guardarReview">

                <input type="hidden" id="userId" name="userId" value="<%= v.getId().getUserId() %>">
                <input type="hidden" id="exerciseId" name="exerciseId" value="<%= v.getId().getExerciseId() %>">
                <input type="text" name="review" value="<%= review %>"/>
                <button type="submit">Guardar</button>
            </form>
            <%
                        }
                    }
                }
            %>
            <% } else { %>
            -
            <% } %>
        </td>

        <td>
            <%if(valorado==true){
            %>
            <a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idSesion=<%=idSesion%>&idRutina=<%=idRutina%>">Editar valoracion</a>
            <%}else{%>
            -
            <% } %>
        </td>

    </tr>
    <%}%>
</table>
<%}else{%>
<h3>No hay ejercicios con esa valoración.</h3>
<%}%>
</body>
</html>
