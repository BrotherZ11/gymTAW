<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.gymtaw.dto.Exercise" %><%--
  Created by IntelliJ IDEA.
  User: Gonzalo Muñoz Rubio
  Date: 23/06/2024
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar ejercicio</title>
</head>
<body>
<h1>Editar los datos del ejercicio</h1>
<form:form method="post" action="/users/guardarEdicionEjercicio" modelAttribute="ejercicio">
    <form:input type="hidden" path="id"/>
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><form:input type="text" path="name" size="100" maxlength="100"/></td>
        </tr>
        <tr>
            <td>Descripción:</td>
            <td><form:input type="text" path="description" size="300" maxlength="300"/></td>
        </tr>
        <tr>
            <td>Video:</td>
            <td><form:input type="text" path="video" size="100" maxlength="100"/></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">Enviar</button></td>
        </tr>
</form:form>

</body>
</html>
