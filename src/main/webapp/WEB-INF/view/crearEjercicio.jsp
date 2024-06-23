<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Gonzalo Muñoz Rubio
  Date: 23/06/2024
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear ejercicio</title>
</head>
<body>
<h1>Crear ejercicio</h1>
<form:form method="post" action="/users/guardarCreacionEjercicio" modelAttribute="ejercicio">
    <table>
        <tr>
            <td>Nombre:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Descripción:</td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td>Video:</td>
            <td><form:input path="video"/></td>
        </tr>
        <tr>
            <td>Tipo:</td>
            <td><form:select path="typeIdtype.id" items="${tipos}" itemValue="id" itemLabel="name"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Crear"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
