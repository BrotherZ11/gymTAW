<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SessionEntity> sesiones = (List<SessionEntity>) request.getAttribute("sesiones");
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
    Integer idSesion = (Integer) request.getAttribute("idSesion");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    Integer idCliente=0;
    for (SessionEntity s : sesiones) {
        for (ExerciseHasSessionEntity es : s.getExerciseHasSessions()) {
            for (ClientExerciseEntity ec : es.getExercise().getClientExercises()) {
                idCliente = ec.getUser().getId();
            }
        }
    }
%>
<html>
<head>
    <title>Sesiones</title>
</head>
<body>
<a href="http://localhost:8080/home/cliente/sesionesSemanales?idRutina=<%= idRutina%>&idCliente=<%=idCliente%>">Volver atrás</a>
<h1>Sesiones del Cliente</h1>

<% if (sesiones != null) { %>
<% for (SessionEntity s : sesiones) { %>
<h2><%= s.getName() %></h2>
<% if (ejercicios != null) { %>
<table border="1">
    <tr>
        <th>Nombre ejercicio</th>
        <th>Descripcion</th>
        <th>Video</th>
        <th>Completado</th>
        <th>Valoración</th>
        <th></th>
    </tr>
    <% for (ExerciseEntity e : ejercicios) { %>
    <tr>

        <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= e.getId() %>&idRutina=<%=idRutina%>&idSesion=<%=idSesion%>&idCliente=<%=idCliente%>"><%= e.getName() %></a></td>

        <td><%= e.getDescription() != null ? e.getDescription() : "N/A" %></td>
        <td><%= e.getVideo() != null ? e.getVideo() : "N/A" %></td>
        <td align="center">
            <%
                boolean isDone = false;
                List<ValoracionEntity> val = e.getValoracions();

                for( ExerciseHasSessionEntity es : s.getExerciseHasSessions()){
                    for(ClientExerciseEntity ec : es.getExercise().getClientExercises()){
                        idCliente = ec.getUser().getId();
                    }}
                if (val != null) {
                    for (ValoracionEntity v : val) {
                        if (v.getDone() == 1 && v.getUser().getId().equals(idCliente)) {
                            isDone = true;
                            break;
                        }
                    }
                }
            %>
            <form action="/home/cliente/guardarCompletado" method="post">
                <input type="hidden" name="idEjercicio" value="<%= e.getId() %>" />
                <input type="hidden" name="idSesion" value="<%= idSesion %>" />
                <input type="hidden" name="idCliente" value="<%= idCliente %>" />
                <input type="hidden" name="idRutina" value="<%= idRutina %>" />
                <input type="checkbox" name="done" value="1" <% if (isDone) { %>checked disabled<% } else { %> onchange="this.form.submit()"<% } %> />
            </form>
        </td>
        <%
            boolean valorado = false;
            if (val != null) {

                for (ValoracionEntity v : val) {
                    if (v.getDone() == 1 && v.getUser().getId().equals(idCliente)) {
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

            for( ExerciseHasSessionEntity es : s.getExerciseHasSessions()){
                for(ClientExerciseEntity ec : es.getExercise().getClientExercises()){
                    idCliente = ec.getUser().getId();
                }}

        %>
        <td align="center"><a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idCliente=<%=idCliente %>&idSesion=<%=s.getId()%>&idRutina=<%=idRutina%>">Valorar</a></td>
        <%
                 }
        %>
        <td>
            <%if(valorado==true){

            for( ExerciseHasSessionEntity es : s.getExerciseHasSessions()){
                for(ClientExerciseEntity ec : es.getExercise().getClientExercises()){
                    idCliente =  ec.getUser().getId();
                }}
            %>
        <a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idCliente=<%=idCliente %>&idSesion=<%=s.getId()%>&idRutina=<%=idRutina%>">Editar valoracion</a>
            <%}else{%>
            -
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<br>
<% } else { %>
No hay ejercicios para esta sesión.
<% } %>
<% } %>
<% } else { %>
<p>No hay sesiones disponibles.</p>
<% } %>

</body>
</html>
