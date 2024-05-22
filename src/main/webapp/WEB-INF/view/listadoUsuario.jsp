<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.gymtaw.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gonla
  Date: 29/04/2024
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<User> listaUsuarios =(List<User>)request.getAttribute("usuarios");
%>
<html>
<head>
    <title>Listado de los usuarios de la Aplicación</title>
</head>
<body>
<form:form method="post" action="/users/filtrar" modelAttribute="filtro">
    <form:select path="nombreRol">
        <form:option value="admin">Admin</form:option>
        <form:option value="bodybuilding">Bodybuilding</form:option>
        <form:option value="cross-training">Cross-training</form:option>
        <form:option value="cliente">Cliente</form:option>
    </form:select>
    <form:button>Filtrar</form:button>
</form:form>
<form method="get" action="/users/">
    <button>Limpiar filtros</button>
</form>
<form>
    <table border="1 ">

        <tr>
            <th>DNI</th>
            <th>NOMBRE</th>
            <th>APELLIDOS</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>

        <%
            for (User usuario: listaUsuarios) {
        %>
        <tr>
            <td><%= usuario.getDni() %></td>
            <td><%= usuario.getName() %></td>
            <td><%= usuario.getSurname() %></td>
            <td><a href="/users/borrar?id=<%= usuario.getId() %>">Borrar</a> </td>
            <td><a href="/users/editarUsuario?id=<%= usuario.getId() %>">Editar</a> </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="/users/crearUsuario">Crear nuevo usuario</a>
    <a href="/salir">Salir</a>
</form>
</body>

</html>
