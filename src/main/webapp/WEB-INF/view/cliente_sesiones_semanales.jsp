<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.dto.Session" %>
<%@ page import="com.example.gymtaw.dto.RoutineHasSession" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //Marta Granado Rodríguez 100%
    List<Session> sesiones = (List<Session>) request.getAttribute("sesiones");
    List<RoutineHasSession> sesionRutina = (List<RoutineHasSession>) request.getAttribute("sesionRutina");
    Integer idCliente= (Integer) request.getAttribute("idCliente");
    Integer idRutina= (Integer) request.getAttribute("idRutina");
%>
<html>
<head>
    <title>Sesiones de Cliente</title>
</head>
<body>
<a href="http://localhost:8080/home/cliente/entrenamientos">Volver atrás</a>
<h1> Sesiones del Cliente </h1>
<h2> TAW </h2>

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
                for (RoutineHasSession sesionR : sesionRutina) {
                    if (sesionR.getId().getDay() == i) {
                        found = true;
        %>
        <td>
            <%
                for(Session sesion: sesiones){
                    if (sesionR.getSession().getId().equals(sesion.getId())) {

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
                for(Session sesion: sesiones){
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
                for (RoutineHasSession sesionR : sesionRutina) {
                    if (sesionR.getId().getDay() == i) {
                        found = true;
        %>
        <td>
            <a href="/home/cliente/ejercicio?idSesion=<%= sesionR.getSession().getId() %>&idRutina=<%=idRutina%>">Ver</a>
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
