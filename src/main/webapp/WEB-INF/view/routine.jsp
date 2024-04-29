<%@ page import="com.example.gymtaw.entity.Routine" %><%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Routine rutina = (Routine) request.getAttribute("rutina");
    boolean esEditar = (rutina.getId() != -1);
    String nombre = "", descripcion = "";

    if (esEditar) {
        nombre = rutina.getName();
        descripcion = rutina.getDescription() + "";
    }
%>
<html>
<head>
    <title>Datos de la rutina</title>
</head>
<body>
<h1>Datos de la rutina</h1>
<form method="post" action="/guardar">
    <input type="hidden" name="id" value="<%= rutina.getId() %>">
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" size="100" maxlength="100" value="<%= nombre %>" /> </td>
        </tr>
        <tr>
            <td>Descripcion:</td>
            <td><input type="text" name="descripcion" size="100"  maxlength="100" value="<%= descripcion %>" /></td>
        </tr>
        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form>
</body>
</html>
