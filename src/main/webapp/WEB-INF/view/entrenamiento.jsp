<%--
David Molina Lopez 80%
David Zarzavilla Borrego 20%
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.dto.RoutineHasSession" %>
<%@ page import="com.example.gymtaw.dto.Session" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RoutineHasSession> listaSesionRutina = (List<RoutineHasSession>) request.getAttribute("listaSesionRutina");
    List<Session> listaCompleta = (List<Session>) request.getAttribute("listaCompleta");
    String nombreRutina = (String) request.getAttribute("nombreRutina");

%>
<html>
<head>
    <title>Entrenamiento</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1>Entrenamiento de la rutina: <%=nombreRutina%></h1>
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
                    for (RoutineHasSession sesionRutina : listaSesionRutina) {
                        if (sesionRutina.getId().getDay() == i) {
                            found = true;
            %>
            <td>
                <select id="sesiones<%=i%>" name="idSesion<%=i%>">
                    <option value="-1">Sin sesion</option>
                    <%
                        for(Session sesion: listaCompleta){
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
                        for(Session sesion: listaCompleta){
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
                    for (RoutineHasSession sesionRutina : listaSesionRutina) {
                        if (sesionRutina.getId().getDay() == i) {
                            found = true;
            %>
            <td>
                <a href="ver_sesion?idSesion=<%=sesionRutina.getSession().getId()%>">Ver</a>
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
