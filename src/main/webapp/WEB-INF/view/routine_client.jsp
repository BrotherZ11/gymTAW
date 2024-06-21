<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.RoutineEntity" %>
<%@ page import="com.example.gymtaw.entity.TypeEntity" %>
<%@ page import="com.example.gymtaw.entity.UserEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RoutineEntity> listaRutinasCliente = (List<RoutineEntity>) request.getAttribute("listaRutinasCliente");
    List<RoutineEntity> listaRutinasSinAsignar = (List<RoutineEntity>) request.getAttribute("listaRutinasSinAsignar");
    UserEntity usuario = (UserEntity) session.getAttribute("usuario");
    Integer idCliente = (Integer) request.getAttribute("idCliente");
    List<TypeEntity> tipos = (List<TypeEntity>) request.getAttribute("tipos");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Rutinas del Cliente</title>
</head>
<body>
<h1> Rutinas del Cliente </h1>
<h2> TAW </h2>
<a href="/home/trainer">Home  </a>
<a href="/home//clients">Atrás</a>
<form:form method="post" action="/home/trainer/routine_client_filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="nombre" />
    <%
        if ("crosstraining".equals(usuario.getIdRol().getType())) {
    %>
    Tipos de la rutina:
    <%
        for(TypeEntity tipo : tipos){
    %>
    <form:checkbox value="<%=tipo%>" label="<%=tipo.getName()%>" path="tipos"/>
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
            for(RoutineEntity rutina: listaRutinasSinAsignar){
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
        for(RoutineEntity r : listaRutinasCliente){
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
