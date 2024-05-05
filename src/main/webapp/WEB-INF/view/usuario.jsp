<%@ page import="com.example.gymtaw.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 05/05/2024
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User usuario = (User) request.getAttribute("usuario");
    boolean esEditar = (usuario.getId() != -1);
    String nombre = "", apellido = "", dni = "";

    if (esEditar) {
        nombre = usuario.getName();
        apellido = usuario.getSurname();
        dni = usuario.getDni();
    }
%>
<html>
<head>
    <title>Editar un usuario</title>
</head>
<body>
<h1>Información del usuario</h1>
<form method="post" action="/users/guardar">
    <input type="hidden" name="id" value="<%= usuario.getId() %>">
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" size="100" maxlength="100" value="<%= nombre %>" /> </td>
        </tr>
        <tr>
            <td>Apellido:</td>
            <td><input type="text" name="apellido" size="4"  maxlength="100" value="<%= apellido %>" /></td>
        </tr>
        <tr>
            <td> DNI: </td>
            <td><input type ="text" name ="dni" size="4" maxlength="9" value="<%= dni%>"></td>
        </tr>
        <tr>
            <td> Teléfono: </td>
            <td><input type ="text" name ="telefono" size="4" maxlength=12 value="<%= usuario.getPhone()%>"></td>
        </tr>
        <tr>
            <td> Edad: </td>
            <td><input type ="number" name ="edad" size="4" maxlength="3" value="<%= usuario.getAge()%>"></td>
        </tr>
        <tr>
            <td> Género: </td>
            <td><input type ="text" name ="genero" size="4" maxlength="100" value="<%= usuario.getGender()%>"></td>
        </tr>
        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form>
</body>
</html>
