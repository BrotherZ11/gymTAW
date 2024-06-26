<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.dto.Session" %>
<%@ page import="com.example.gymtaw.dto.RoutineHasSession" %>
<%@ page import="com.example.gymtaw.dto.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //David Molina Lopez
    List<Session> listaSesiones = (List<Session>) request.getAttribute("listaSesiones");
    List<RoutineHasSession> listaSesionesDias = (List<RoutineHasSession>) request.getAttribute("listaSesionesDias");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    String nombreRutina = (String) request.getAttribute("nombreRutina");
    User cliente = (User) session.getAttribute("cliente");
%>
<html>
<head>
    <title>Sesiones</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1>Sesiones de <%=cliente.getName()%> <%=cliente.getSurname()%> </h1>
<p><a href="/home/trainer">Home</a> / <a href="clients">Clientes</a> / <a href="routine_client?idCliente=<%=cliente.getId()%>">Rutinas <%=cliente.getName()%> <%=cliente.getSurname()%></a> / <%=nombreRutina%></p><br>
<table border="1">
    <tr>
        <th>DIA</th>
        <td>Lunes</td>
        <td>Martes</td>
        <td>Miércoles</td>
        <td>Jueves</td>
        <td>Viernes</td>
        <td>Sábado</td>
        <td>Domingo</td>
    </tr>
    <tr>
        <th>NOMBRE</th>
        <%
            //int index = 0;
            for(int i = 1; i <= 7; i++){
                boolean found = false;
                for (RoutineHasSession sesionRutina : listaSesionesDias) {
                    if (sesionRutina.getId().getDay() == i) {
                        found = true;
        %>
        <td>
                <%
                    for(Session sesion: listaSesiones){
                        if (sesionRutina.getSession().getId().equals(sesion.getId())) {

                %>
                <%=sesion.getName()%>
                <%
                        }
                    }
                %>
        </td>
        <%
                    break;
                }
            }
            if (!found) {
        %>
        <td>
                <%
                    for(Session sesion: listaSesiones){
                %>
                <%=sesion.getName()%>
                <%
                    }
                %>
        </td>
        <%
                }
            }
        %>
    </tr>

    <tr>
        <th></th>
        <%
            for(int i = 1; i <= 7; i++){
                boolean found = false;
                for (RoutineHasSession sesionRutina : listaSesionesDias) {
                    if (sesionRutina.getId().getDay() == i) {
                        found = true;
        %>
        <td>
            <a href="exercise_client?idSesion=<%=sesionRutina.getSession().getId()%>&idRutina=<%=idRutina%>">Ver</a>
        </td>

        <%
                    break;
                }
            }
            if (!found) {
        %>
        <td> - </td>
        <%
                }
            }
        %>
    </tr>
</table>
</body>
</html>