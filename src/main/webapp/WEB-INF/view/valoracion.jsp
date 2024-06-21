<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
    Integer idSesion = -1;
    Integer idRutina = -1;
    Integer idCliente= (Integer) request.getAttribute("idCliente");
%>
<html>
<head>
    <title>Valorar</title>
</head>
<body>
<a href="http://localhost:8080/home/cliente?idCliente=<%=idCliente%>">Volver atrás</a>



<h1>Valoraciones de ejercicios</h1>

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
        <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%= e.getId() %>&idRutina=<%=idRutina%>&idSesion=<%=idSesion%>&idCliente=<%=idCliente%>"><%= e.getName()%></a></td>
        <td><%=e.getDescription()%></td>
        <td><%=e.getVideo()%></td>
        <td align="center">
            <%
                boolean isDone = false;
                List<ValoracionEntity> val = e.getValoracions();


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

        %>
        <td align="center"><a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idCliente=<%=idCliente %>&idSesion=<%=idSesion%>&idrutina=<%=idRutina%>">Valorar</a></td>
        <%
            }
        %>
        <td>
            <%
                if (val != null) {
                    for (ValoracionEntity v : val) {
                        if (v.getUser().getId().equals(idCliente)) {
                            String review = v.getReview();
                            if (review == null) {
                                review = "No tienes review aún.";
                            }
            %>
            <form method="post" action="/home/cliente/guardarReview">
                <input type="hidden" id="idCliente" name="idCliente" value="<%= idCliente %>">
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
        </td>

        <td>
            <%if(valorado==true){
            %>
            <a href="valorarEjercicio?idEjercicio=<%= e.getId() %>&idCliente=<%=idCliente %>&idSesion=<%=idSesion%>&idRutina=<%=idRutina%>">Editar valoracion</a>
            <%}else{%>
            -
            <% } %>
        </td>

    </tr>
    <%}%>
</table>

</body>
</html>
