<%@ page import="com.example.gymtaw.entity.RoutineEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 29/04/2024
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RoutineEntity> lista = (List<RoutineEntity>) request.getAttribute("lista");
    String filtro = request.getParameter("filtro");
    Integer idEntrenador = (Integer) request.getAttribute("idEntrenador");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Rutinas</title>
</head>
<body>
<h1> Rutinas </h1>
<h2> TAW </h2>
<form:form method="post" action="/filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="nombre" />
    <form:button>Filtrar</form:button>
</form:form>
<a href="crear?idEntrenador=<%=idEntrenador%>">Nueva rutina ... </a>
<table border="1 ">

    <tr>
        <th>ID</th>
        <th>DESCRIPCION</th>
        <th>FECHA</th>
    </tr>
    <%
        for(RoutineEntity r : lista){
    %>
    <tr>
        <td><%=r.getName()%></td>
        <td><%=r.getDescription()%></td>
        <td><%=r.getDate()%></td>
        <td><a href="ver?id=<%= r.getIdroutine()  %>">Ver</a> </td>
        <td><a href="editar?id=<%= r.getIdroutine()%>&idEntrenador=<%=idEntrenador%>">Editar</a> </td>
        <td><a href="borrar?id=<%= r.getIdroutine()%>&idEntrenador=<%=idEntrenador%>">Borrar</a> </td>
        <td><a href="asignar?id=<%= r.getIdroutine()  %>">Asignar</a> </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
