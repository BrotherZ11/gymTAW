<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.dto.Routine" %>
<%@ page import="com.example.gymtaw.dto.User" %>
<%@ page import="com.example.gymtaw.dto.Type" %>
<%@ page import="com.example.gymtaw.ui.FiltroRutina" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //David Molina Lopez
    List<Routine> listaRutinasCliente = (List<Routine>) request.getAttribute("listaRutinasCliente");
    List<Routine> listaRutinasSinAsignar = (List<Routine>) request.getAttribute("listaRutinasSinAsignar");
    User usuario = (User) session.getAttribute("usuario");
    User cliente = (User) session.getAttribute("cliente");
    Integer idCliente = (Integer) request.getAttribute("idCliente");
    List<Type> tipos = (List<Type>) request.getAttribute("tipos");
    FiltroRutina filtro = (FiltroRutina) request.getAttribute("filtro");
%>
<html>
<head>
    <title>Rutinas del Cliente</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1> Rutinas de <%=cliente.getName()%> <%=cliente.getSurname()%></h1>
<p><a href="/home/trainer">Home</a> / <a href="clients">Clientes</a> / Rutinas <%=cliente.getName()%> <%=cliente.getSurname()%></p><br>
<form:form method="post" action="/home/trainer/routine_client_filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="nombre" />
    <%
        if ("crosstraining".equals(usuario.getRol().getType())) {
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

<%
    if(!listaRutinasSinAsignar.isEmpty()){
%>
<form method="post" action="asignar_rutina">
    <label>Selecciona una rutina: </label>
    <select id="rutinas" name="idRutina">
        <%
            for(Routine rutina: listaRutinasSinAsignar){
        %>
        <option value=<%=rutina.getId()%>><%=rutina.getName()%></option>
        <%
            }
        %>
    </select>
    <input type="hidden" name="idCliente" value="<%= idCliente %>">
    <button type="submit">Asignar rutina al cliente</button>
</form>
<%
    }else{
%>
    <p>Todas las rutinas estan ya asignadas al cliente</p>
<%
    }
%>

<table border="1 ">

    <tr>
        <th>ID</th>
        <th>DESCRIPCION</th>
        <th>FECHA</th>
    </tr>
    <%
        if(listaRutinasCliente.isEmpty()){
    %>
    <tr>
        <td>No tiene ninguna rutina</td>
        <td> - </td>
        <td> - </td>
    </tr>
    <%
    }else{
        for(Routine r : listaRutinasCliente){
    %>
    <tr>
        <td><%=r.getName()%></td>
        <td><%=r.getDescription()%></td>
        <td><%=r.getDate()%></td>
        <td><a href="session_client?idRutina=<%= r.getId() %>">Ver</a> </td>
        <td><a href="quitar_rutina?idRutina=<%= r.getId() %>">Quitar rutina</a> </td>
    </tr>
    <%
        }
    }
    %>
</table>
</body>
</html>
