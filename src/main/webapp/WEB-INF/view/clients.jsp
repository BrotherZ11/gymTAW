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
<table border="1 ">

    <tr>
        <th>NOMBRE</th>
        <th>APELLIDO</th>
        <th>DNI</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for(User u : lista){
    %>
    <tr>
        <td><%=u.getName()%></td>
        <td><%=u.getSurname()%></td>
        <td><%=u.getDni()%></td>
        <td><a href="/routine_client/ver?id=<%=idEntrenador%>">Rutinas</a></td>
        <td><a href="/routine_client/ver?id=<%=idEntrenador%>">Valoraciones</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
