<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.SessionEntity" %>
<%@ page import="com.example.gymtaw.entity.RoutineHasSessionEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SessionEntity> lista = (List<SessionEntity>) request.getAttribute("lista");
    List<RoutineHasSessionEntity> listaSesionRutina = (List<RoutineHasSessionEntity>) request.getAttribute("listaSesionRutina");
    List<SessionEntity> listaCompleta = (List<SessionEntity>) request.getAttribute("listaCompleta");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    Integer idEntrenador = (Integer) request.getAttribute("idEntrenador");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Entrenamiento</title>
</head>
<body>
<h1>Entrenamiento de la rutina: </h1>
<a href="crearsesion?idEntrenador=<%=idEntrenador%>">Nueva sesion ... </a>
<form method="post" action="guardar_sesiones">
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
                    for (RoutineHasSessionEntity sesionRutina : listaSesionRutina) {
                        if (sesionRutina.getId().getDay() == i) {
                            found = true;
            %>
            <td>
                <select id="sesiones<%=i%>" name="idSesion<%=i%>">
                    <option value="-1">Sin sesion</option>
                    <%
                        for(SessionEntity sesion: listaCompleta){
                            String isSelected = "";
                            if (sesionRutina.getSessionEntity().getId().equals(sesion.getId())) {
                                isSelected = "selected";
                            }
                    %>
                    <option value="<%=sesion.getId()%>" <%=isSelected%>><%=sesion.getName()%></option>
                    <%
                        }
                    %>
                </select>
            </td>
            <%
                        break;
                    }
                }
                if (!found) {
            %>
            <td>
                <select id="sesiones<%=i%>" name="idSesion<%=i%>">
                    <option value="-1" selected>Sin sesion</option>
                    <%
                        for(SessionEntity sesion: listaCompleta){
                    %>
                    <option value="<%=sesion.getId()%>"><%=sesion.getName()%></option>
                    <%
                        }
                    %>
                </select>
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
                    for (RoutineHasSessionEntity sesionRutina : listaSesionRutina) {
                        if (sesionRutina.getId().getDay() == i) {
                            found = true;
            %>
            <td><a href="exercise_client?idSesion=<%=sesionRutina.getSessionEntity().getId()%>">Ver</a></td>
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
    <input type="hidden" name="idEntrenador" value="<%= idEntrenador %>">
    <input type="hidden" name="idRutina" value="<%= idRutina %>">
    <button type="submit">Guardar sesiones</button>
</form>
</body>
</html>
