<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.UserEntity" %>
<%@ page import="com.example.gymtaw.entity.UserEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<UserEntity> lista = (List<UserEntity>) request.getAttribute("lista");
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
    <table border="1 ">

        <tr>
            <th>DNI</th>
            <th>NOMBRE</th>
            <th>APELLIDOS</th>
            <th></th>
            <th></th>
        </tr>
        <%
        if(lista.isEmpty()){
        %>
        <tr>
            <td>No tienes usuarios</td>
            <td> - </td>
            <td> - </td>
            <td> - </td>
            <td> - </td>
        </tr>
        <%
        }else{
            for (UserEntity cliente: lista) {
        %>
        <tr>
            <td><%= cliente.getDni() %></td>
            <td><%= cliente.getName() %></td>
            <td><%= cliente.getSurname() %></td>
            <td><a href="routine_client?idCliente=<%= cliente.getId() %>">Rutina</a> </td>
            <td><a href="/users/editarUsuario?id=<%= cliente.getId() %>">Valoración</a> </td>
        </tr>
        <%
            }
        }
        %>
</div>
</body>
</html>
