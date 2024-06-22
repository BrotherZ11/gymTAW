<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.gymtaw.entity.UserEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gonla
  Date: 29/04/2024
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<UserEntity> listaUsuarios =(List<UserEntity>)request.getAttribute("usuarios");
%>
<html>
<head>
    <title>Listado de los usuarios de la Aplicación</title>
</head>
<body>
<form:form method="post" action="/users/filtrar" modelAttribute="filtro">
    <form:radiobuttons path="idRol" items="${rols}" itemLabel="type" itemValue="id"/>
    <form:button>Filtrar</form:button>
</form:form>

<form:form method="post" action="/users/filtrar" modelAttribute="filtro">
    Nombre:<form:input path="nombre" size="50"></form:input>
    Apellido:<form:input path="apellido" size="50"></form:input>
    DNI:<form:input path="dni" size="9"></form:input>
    <form:button>Filtrar</form:button>
</form:form> <br>
<form method="get" action="/users/">
    <button>Limpiar filtros</button>
</form>
<form>
    <table border="1 ">

        <tr>
            <th>DNI</th>
            <th>NOMBRE</th>
            <th>APELLIDOS</th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>

        <%
            for (UserEntity usuario: listaUsuarios) {
        %>
        <tr>
            <td><%= usuario.getDni() %></td>
            <td><%= usuario.getName() %></td>
            <td><%= usuario.getSurname() %></td>
            <td><a href="/users/borrar?id=<%= usuario.getId() %>">Borrar</a> </td>
            <td><a href="/users/editarUsuario?id=<%= usuario.getId() %>">Editar</a> </td>
            <%if(usuario.getIdRol().getId() == 3){%>
                <td>Administrador</td>
            <%}else if(usuario.getIdRol().getId() == 4){%>
                <td><a href="/users/asignar?id=<%=usuario.getId()%>">Asignar entrenadores</a></td>
            <%}else{%>
                <td><a href="/users/asignar?id=<%=usuario.getId()%>">Asignar clientes</a></td>
            <%}%>
            <%if(usuario.getIdRol().getId() == 3){%>
            <td>Administrador</td>
            <%}else if(usuario.getIdRol().getId() == 4){%>
            <td><a href="/users/desasignar?id=<%=usuario.getId()%>">Desasignar entrenadores</a></td>
            <%}else{%>
            <td><a href="/users/desasignar?id=<%=usuario.getId()%>">Desasignar clientes</a></td>
            <%}%>
        </tr>
        <%
            }
        %>
    </table>
    <a href="/users/crearUsuario">Crear nuevo usuario</a>
    <a href="/salir">Salir</a>
</form>
</body>

</html>
