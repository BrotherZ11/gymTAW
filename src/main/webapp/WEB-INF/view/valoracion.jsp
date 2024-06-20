<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        List<RoutineEntity> rutinas = (List<RoutineEntity>) request.getAttribute("rutinas");
        List<SessionEntity> sesiones = (List<SessionEntity>) request.getAttribute("sesiones");
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
%>
<html>
<head>
    <title>Valoraciones</title>
</head>
<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">

<h1>Valoraciones del Cliente</h1>

    <% for (RoutineEntity rutina : rutinas) { %>
<h2><%= rutina.getName() %></h2>
    <% for (SessionEntity sesion : sesiones) { %>
<h3><%= sesion.getName() %></h3>
<form action="/home/cliente/guardarValoracion" method="post">
    <table border="1">
        <tr>
            <th>Nombre ejercicio</th>
            <th>Descripcion</th>
            <th>Video</th>
            <th>Completado</th>
            <th>Valoración</th>
        </tr>
<% for (ExerciseEntity ejercicio : ejercicios) { %>
        <tr>
            <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= ejercicio.getId() %>"><%= ejercicio.getName() %></a></td>
            <td><%= ejercicio.getDescription() != null ? ejercicio.getDescription() : "N/A" %></td>
            <td><%= ejercicio.getVideo() != null ? ejercicio.getVideo() : "N/A" %></td>
            <td align="center">
                <%
                    boolean isDone = false;
                    List<ValoracionEntity> val = ejercicio.getValoracions();
                    if (val != null) {
                        for (ValoracionEntity v : val) {
                            if (v.getDone() == 1) {
                                isDone = true;
                                break;
                            }
                        }
                    }
                %>
                <form action="/home/cliente/guardarCompletado" method="post">
                    <input type="hidden" name="idEjercicio" value="<%= ejercicio.getId() %>" />
                    <input type="hidden" name="idSesion" value="<%= sesion.getId() %>" />
                    <input type="hidden" name="idCliente" value="<%= sesion.getIdtrainer().getId() %>" />
                    <input type="checkbox" name="done" value="1" <% if (isDone) { %>checked disabled<% } else { %> onchange="this.form.submit()"<% } %> />
                </form>
            </td>
                <%
                boolean valorado = false;
                if (val != null) {
                    for (ValoracionEntity v : val) {
                        if (v.getDone() == 1) {
                            if (v.getStars() != null) {
                                valorado = true;
            %>
            <td align="center"><%= v.getStars() %></td>
                <%
            } else {
            %>
            <td align="center">-</td>
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
            <td align="center"><a href="/home/cliente/valorarEjercicio?idEjercicio=<%= ejercicio.getId() %>&idCliente=<%= sesion.getIdtrainer().getId() %>&idSesion=<%= sesion.getId() %>">Valorar</a></td>
            <%
                }
            %>
        </tr>
        <% } %>
    </table>
    <br>
        <% } %>
        <% } %>

</body>
</html>