<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.Routine" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Routine> lista = (List<Routine>) request.getAttribute("lista");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Routinas de Cliente</title>
</head>
<body>
<h1> Rutinas del Cliente </h1>
<h2> TAW </h2>
<form:form method="post" action="/rutina_bodybuilding/filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="titulo" />
    <form:button>Filtrar</form:button>
</form:form>
<table border="1 ">

    <tr>
        <th>ID</th>
        <th>DESCRIPCION</th>
        <th>FECHA</th>
    </tr>
    <%
        for(Routine r : lista){
    %>
    <tr>
        <td><%=r.getName()%></td>
        <td><%=r.getDescription()%></td>
        <td><%=r.getDate()%></td>
        <td><a href="/rutina_bodybuilding/ver?id=<%= r.getId()  %>">Ver</a> </td>
        <td><a href="/editar?id=<%= r.getId()  %>">Editar</a> </td>
        <td><a href="/borrar?id=<%= r.getId()  %>">Borrar</a> </td>
        <td><a href="/rutina_bodybuilding/asignar?id=<%= r.getId()  %>">Asignar</a> </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
