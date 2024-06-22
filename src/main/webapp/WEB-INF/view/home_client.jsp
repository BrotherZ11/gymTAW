<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Integer idCliente = (Integer) request.getAttribute("idCliente");

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">TAW</h1>
<a href="/salir">Cerrar sesion</a> <!-- Corrected link to logout -->
<div>
    <a href="/home/cliente/entrenamientos">Entrenamientos</a>
    <a href="/home/cliente/valorar">Valoraciones</a> <!-- Corrected link to routines -->
</div>
</body>
</html>
