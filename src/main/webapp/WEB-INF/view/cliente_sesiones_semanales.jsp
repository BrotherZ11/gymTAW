<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.SessionEntity" %>
<%@ page import="com.example.gymtaw.entity.RoutineHasSessionEntity" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SessionEntity> sesiones = (List<SessionEntity>) request.getAttribute("sesiones");
    List<RoutineHasSessionEntity> sesionRutina = (List<RoutineHasSessionEntity>) request.getAttribute("sesionRutina");
%>
<html>
<head>
    <title>Sesiones de Cliente</title>
</head>
<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">
<h1> Sesiones del Cliente </h1>
<h2> TAW </h2>

<table border="1 ">
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
            for(int i = 1; i <= 7; i++){
                boolean found = false;
                for(int j = 0; j < sesiones.size(); j++){
                    if(sesionRutina.get(j).getDay() == i){
                        found = true;
        %>
        <td><%= sesiones.get(j).getName() %></td>
        <%
                    //break;
                }
            }
            if (!found) {
        %>
        <td>No hay sesion</td>
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
                for(int j = 0; j < sesiones.size(); j++){
                    if(sesionRutina.get(j).getDay() == i){
                        found = true;
        %>
        <td><a href="/home/cliente/ejercicio?idSesion=<%= sesiones.get(j).getId() %>">Ver</a></td>
        <%
                    //break;
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
