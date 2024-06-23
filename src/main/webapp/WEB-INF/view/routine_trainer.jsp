<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.gymtaw.entity.RoutineEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.TypeEntity" %>
<%@ page import="com.example.gymtaw.dto.Routine" %>
<%@ page import="com.example.gymtaw.dto.Type" %><%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Routine> lista = (List<Routine>) request.getAttribute("lista");
    List<Type> tipos = (List<Type>) request.getAttribute("tipos");
    String filtro = request.getParameter("filtro");
    String rol = (String) request.getAttribute("rol");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Rutinas</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1> Rutinas </h1>
<h2> TAW </h2>
<a href="/home/trainer">Volver</a>

<form:form method="post" action="/home/trainer/filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="nombre" />
    <%
        if ("crosstraining".equals(rol)) {
    %>
    Tipos de la rutina:
    <%
        for(Type tipo : tipos){
    %>
    <form:checkbox value="<%=tipo.getId()%>" label="<%=tipo.getName()%>" path="tipos"/>
    <%
            }
        }
    %>
    <form:button>Filtrar</form:button>
</form:form>
<a href="crear">Nueva rutina ... </a>
<table border="1 ">

    <tr>
        <th>NOMBRE</th>
        <th>DESCRIPCION</th>
        <th>FECHA</th>
        <th></th>
        <th></th>
        <th></th>

    </tr>
    <%
        for(Routine r : lista){
    %>
    <tr>
        <td><%=r.getName()%></td>
        <td><%=r.getDescription()%></td>
        <td><%=r.getDate()%></td>
        <td><a href="ver?idRutina=<%= r.getId()%>">Ver</a> </td>
        <td><a href="editar?idRutina=<%= r.getId()%>">Editar</a> </td>
        <td><a href="borrar?idRutina=<%= r.getId()%>">Borrar</a> </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
