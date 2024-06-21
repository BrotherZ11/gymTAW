<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.SessionEntity" %>
<%@ page import="com.example.gymtaw.entity.RoutineHasSessionEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RoutineHasSessionEntity> listaSesionRutina = (List<RoutineHasSessionEntity>) request.getAttribute("listaSesionRutina");
    List<SessionEntity> listaCompleta = (List<SessionEntity>) request.getAttribute("listaCompleta");

%>
<html>
<head>
    <title>Entrenamiento</title>
</head>
<body>
<h1>Entrenamiento de la rutina: </h1>
<a href="/home/trainer/rutina">Volver</a>
<a href="crear_sesion">Nueva sesion ... </a>
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
                            if (sesionRutina.getSession().getId().equals(sesion.getId())) {
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
            <td>
                <a href="exercise_client?idSesion=<%=sesionRutina.getSession().getId()%>">Ver</a>
                <a href="editar_sesion?idSesion=<%=sesionRutina.getSession().getId()%>">Editar</a>
                <a href="borrar_sesion?idSesion=<%=sesionRutina.getSession().getId()%>">Borrar</a>
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
    <button type="submit">Guardar sesiones</button>
</form>
</body>
</html>
