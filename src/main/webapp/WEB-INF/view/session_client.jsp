<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.SessionEntity" %>
<%@ page import="com.example.gymtaw.entity.SessionRoutineEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SessionEntity> lista = (List<SessionEntity>) request.getAttribute("lista");
    List<SessionRoutineEntity> listaSesionRutina = (List<SessionRoutineEntity>) request.getAttribute("listaSesionRutina");
    List<SessionEntity> listaCompleta = (List<SessionEntity>) request.getAttribute("listaCompleta");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    Integer idEntrenador = (Integer) request.getAttribute("idEntrenador");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Sesiones de Cliente</title>
</head>
<body>
<h1> Sesiones del Cliente </h1>
<h2> TAW </h2>
<form:form method="post" action="/rutina_bodybuilding/filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="titulo" />
    <form:button>Filtrar</form:button>
</form:form>
<form method="post" action="guardar_sesiones">
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
            if(!lista.isEmpty() && index < lista.size() && listaSesionRutina.get(index).getDay() == i){
        %>
            <td>
                <select id="sesiones<%=i%>" name="idSesion<%=i%>">
                    <option value="-1">Sin sesion</option>
                    <%
                    for(SessionEntity sesion: listaCompleta){
                        String isSelected = "";
                        if(index < lista.size() && sesion.equals(lista.get(index))){
                            isSelected = "selected";
                        }
                    %>
                    <option value=<%=sesion.getId()%> <%=isSelected%>><%=sesion.getName()%></option>
                    <%
                        }
                        index++;
                    %>
                </select>
            </td>
        <%
            }else{
        %>
            <td>
                <select id="sesiones<%=i%>" name="idSesion<%=i%>">
                    <option value="-1" selected>Sin sesion</option>
                    <%
                        for(SessionEntity sesion: listaCompleta){
                    %>
                    <option value=<%=sesion.getId()%>><%=sesion.getName()%></option>
                    <%
                        }
                        index++;
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
                index = 0;
                for(int i = 1; i <= 7; i++){
                    if(!lista.isEmpty() && index < lista.size() && listaSesionRutina.get(index).getDay() == i){
            %>
            <td><a href="exercise_client?idSesion=<%=lista.get(index++).getId()%>">Ver</a></td>
            <%
                    }else{
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
