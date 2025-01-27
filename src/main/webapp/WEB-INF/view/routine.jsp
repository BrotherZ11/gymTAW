<%-- David Zarzavilla Borrego--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Datos de la rutina</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1>Datos de la rutina</h1>

<a href="/home/trainer/rutina">Cancelar</a>
<form:form method="post" action="guardar" modelAttribute="rutina">
    <form:hidden path="id" />
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td>Descripcion:</td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td>Fecha de Creación:</td>
            <td><form:input path="date" type="date"/></td>
        </tr>
        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
