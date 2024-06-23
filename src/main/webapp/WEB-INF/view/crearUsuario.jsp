<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Gonzalo Muñoz Rubio
  Date: 05/05/2024
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Crear un usuario</title>
</head>
<body>
<h1>Datos del nuevo usuario</h1>
<form:form method="post" action="/users/guardarCreacion" modelAttribute="usuario">
    <form:hidden path="id"/>
    <table border="0">
        <tr>
            <td>Email:</td>
            <td><form:input type="text" path="email" size="100" maxlength="100"/></td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><form:input type="text" path="password" size="100" maxlength="100"/></td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td><form:input type="text" path="name" size="100" maxlength="100"/></td>
        </tr>
        <tr>
            <td>Apellido:</td>
            <td><form:input type="text" path="surname" size="100" maxlength="100"/></td>
        </tr>
        <tr>
            <td>DNI:</td>
            <td><form:input type="text" path="dni" size="9" maxlength="9"/></td>
        </tr>
        <tr>
            <td>Teléfono:</td>
            <td><form:input type="text" path="phone" size="12" maxlength="12"/></td>
        </tr>
        <tr>
            <td>Edad:</td>
            <td><form:input type="number" path="age" size="3" maxlength="3"/></td>
        </tr>
        <tr>
            <td>Género:</td>
            <td><form:input type="text" path="gender" size="100" maxlength="100"/></td>
        </tr>
        <tr>
            <td>Rol:</td>
            <td><form:radiobuttons path="idRol" items="${rols}" itemLabel="type" itemValue="id"></form:radiobuttons></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">Enviar</button></td>
        </tr>

    </table>
</form:form>
</body>
</html>
