<%@ page import="com.example.gymtaw.entity.UserEntity" %>
<%@ page import="com.example.gymtaw.entity.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 05/05/2024
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserEntity userEntity = (UserEntity) request.getAttribute("usuario");
%>
<html>
<head>
    <title>Crear un usuario</title>
</head>
<body>
<h1>Datos del nuevo usuario</h1>
<form method="post" action="/users/guardarCreacion">
    <input type="hidden" name="id" value="<%=userEntity.getId()%>">
    <table border="0">
        <tr>
            <td>Gmail:</td>
            <td><input type="text" name="gmail" size="100" maxlength="100" /> </td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><input type="password" name="contrasena" size="100" maxlength="100" /> </td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" size="100" maxlength="100" /> </td>
        </tr>
        <tr>
            <td>Apellido:</td>
            <td><input type="text" name="apellido" size="4"  maxlength="100" /></td>
        </tr>
        <tr>
            <td> DNI: </td>
            <td><input type ="text" name ="dni" size="4" maxlength="9"></td>
        </tr>
        <tr>
            <td> Teléfono: </td>
            <td><input type ="text" name ="telefono" size="4" maxlength=12></td>
        </tr>
        <tr>
            <td> Edad: </td>
            <td><input type ="number" name ="edad" size="4" maxlength="3"></td>
        </tr>
        <tr>
            <td> Género: </td>
            <td><input type ="text" name ="genero" size="4" maxlength="100"></td>
        </tr>
        <tr>
            <td>Rol:</td>
            <td><label for="rol">Elige un rol:</label>
                <select name="rol" id="rol">
                    <option value="3">Admin</option>
                    <option value="4">Cliente</option>
                    <option value="1">Bodybuilding</option>
                    <option value="2">Cross-training</option>
                </select></td>
        </tr>
        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form>
</body>
</html>
