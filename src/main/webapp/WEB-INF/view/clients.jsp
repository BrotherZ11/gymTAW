<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> lista = (List<User>) request.getAttribute("lista");
    Integer idEntrenador = (Integer) request.getAttribute("idEntrenador");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Clientes</title>
</head>
<body>
<h1> Clientes </h1>
<h2> TAW </h2>
<form:form method="post" action="/rutina_bodybuilding/filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="titulo" />
    <form:button>Filtrar</form:button>
</form:form>
<div>
    <a href="home/crosstraining/clients?idEntrenador=<%=idEntrenador%>">Clientes</a><br>
    <a href="home/crosstraining/clients">Rutinas</a><br>
    <a>Entrenamiento</a>
</div>
</body>
</html>
