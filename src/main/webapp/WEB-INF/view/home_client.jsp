<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Integer idCliente = (Integer) request.getAttribute("idCliente");

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">TAW</h1>
<a href="/logout">Cerrar sesion</a> <!-- Corrected link to logout -->
<div>
    <a href="/home/client/entrenamientos?idCliente=<%=idCliente%>">Entrenamientos</a>
    <a href="/home/client/valorar?idCliente=<%=idCliente%>">Valoraciones</a> <!-- Corrected link to routines -->
</div>
</body>
</html>
