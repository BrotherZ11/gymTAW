<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.SessionEntity" %>
<%@ page import="com.example.gymtaw.entity.RoutineHasSessionEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SessionEntity> listaSesiones = (List<SessionEntity>) request.getAttribute("listaSesiones");
    List<RoutineHasSessionEntity> listaSesionesDias = (List<RoutineHasSessionEntity>) request.getAttribute("listaSesionesDias");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Entrenamiento</title>
</head>
<body>
<h1>Entrenamiento de la rutina: </h1>
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
            int index = 0;
            for(int i = 1; i <= 7; i++){
                if(!listaSesiones.isEmpty()
                        && listaSesionesDias.size() > index
                        && listaSesionesDias.get(index).getId().getDay() == i){
            %>
            <td><%=listaSesiones.get(index).getName()%></td>
            <%
                }else{
            %>
            <td>No hay sesion</td>
            <%
                }
                index++;
            }
            %>
        </tr>

        <tr>
            <th></th>
            <%
                index = 0;
                for(int i = 1; i <= 7; i++){
                    if(!listaSesiones.isEmpty()
                            && listaSesionesDias.size() > index
                            && listaSesionesDias.get(index).getId().getDay() == i){
            %>
            <td><a href="/home/trainer/exercise_client?idSesion=<%=listaSesiones.get(index).getId()%>">Ver</a></td>
            <%
                    }else{
            %>
            <td> - </td>
            <%
                    }
                    index++;
                }
            %>
        </tr>
    </table>
</body>
</html>