<%@ page import="com.example.gymtaw.entity.SessionEntity" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SessionEntity sesion = (SessionEntity) request.getAttribute("sesion");
    boolean esEditar = (sesion.getId() != -1);
    String nombre = "";
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
    if (esEditar) {
        nombre = sesion.getName();

    }
%>
<html>
<head>
    <title>Datos de la sesion</title>
</head>
<body>
<h1>Datos de la sesion</h1>
<a href="/home/trainer/ver">Cancelar</a>
<form method="post" action="guardar_sesion">
    <input type="hidden" name="id" value="<%= sesion.getId() %>">
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" size="100" maxlength="100" value="<%= nombre %>" /> </td>
        </tr>
        <tr>
            <td>Ejercicios:</td>
            <td>
                <table border="1">
                    <%
                        for (ExerciseEntity ejercicio : ejercicios) {
                    %>
                    <tr>
                        <td><%=ejercicio.getName()%></td>
                        <td>
                            <input type="checkbox" name="ejercicioId" value="<%=ejercicio.getId()%>"> Incluir
                        </td>
                        <td>
                            Orden: <input type="number" name="orden_<%=ejercicio.getId()%>" min="1">
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="2"> <button>Guardar</button></td>
        </tr>
    </table>
</form>
</body>
</html>
