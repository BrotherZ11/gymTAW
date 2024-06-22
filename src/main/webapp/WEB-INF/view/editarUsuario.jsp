<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.gymtaw.dto.User" %><%--
  Created by IntelliJ IDEA.
  User: Gonzalo Muñoz Rubio
  Date: 05/05/2024
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User usuario = (User) request.getAttribute("usuario");
%>
<html>
<head>
    <title>Editar un usuario</title>
</head>
<body>
<h1>Información del usuario</h1>
<form:form method="post" action="/users/guardarEdicion" modelAttribute="usuario">
    <form:input type="hidden" name="id" path="${id}"/>
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><form:input type="text" name="nombre" size="100" maxlength="100"  path="${name}"/> </td>
        </tr>
        <tr>
            <td>Apellido:</td>
            <td><form:input type="text" name="apellido" size="4"  maxlength="100" path="${surname}" /></td>
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
</form:form>
</body>
</html>
