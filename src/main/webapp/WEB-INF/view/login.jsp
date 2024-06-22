<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Gonzalo Muñoz Rubio
  Date: 02/05/2024
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
</head>
<body>
    <h1>Bienvenido a GYMTAW</h1>
    <br/>
    <form:form action="/autenticar" modelAttribute="usuario" method="post">
    ${error}
        <table>
            <tr>
                <td>Usuario:</td>
                <td><form:input path="user"/></td>
            </tr>
            <tr>
                <td>Contraseña</td>
                <td><form:password path="password"/></td>
            </tr>
            <tr>
                <td colspan="2"> <form:button> Enviar </form:button></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
