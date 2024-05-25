<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.RoutineEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RoutineEntity> lista = (List<RoutineEntity>) request.getAttribute("lista");
    List<RoutineEntity> listaCompleta = (List<RoutineEntity>) request.getAttribute("listaCompleta");
    Integer idEntrenador = (Integer) request.getAttribute("idEntrenador");
    Integer idCliente = (Integer) request.getAttribute("idCliente");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Routinas de Cliente</title>
</head>
<body>
<div class="container">
    <div class="left-div">Izquierda</div>
    <div class="right-div">Derecha</div>
</div>
<h1> Rutinas del Cliente </h1>
<h2> TAW </h2>
<form:form method="post" action="/rutina_bodybuilding/filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="titulo" />
    <form:button>Filtrar</form:button>
</form:form>
<form method="post" action="asignar_rutina">
    <select id="rutinas">
        <%
            for(RoutineEntity rutina: listaCompleta){
                if(!lista.contains(rutina)){
        %>
        <option value=<%=rutina.getIdroutine()%>><%=rutina.getName()%></option>
        <%
                }
            }
        %>
    </select>
</form>
<table border="1 ">

    <tr>
        <th>ID</th>
        <th>DESCRIPCION</th>
        <th>FECHA</th>
    </tr>
    <%
        if(lista.isEmpty()){
    %>
    <tr>
        <td>No tiene ninguna rutina</td>
        <td> - </td>
        <td> - </td>
    </tr>
    <%
    }else{
        for(RoutineEntity r : lista){
    %>
    <tr>
        <td><%=r.getName()%></td>
        <td><%=r.getDescription()%></td>
        <td><%=r.getDate()%></td>
        <td><a href="session_client?idRutina=<%= r.getIdroutine()  %>">Ver</a> </td>
        <td><a href="quitar_rutina?idRutina=<%= r.getIdroutine()  %>&idEntrenador=<%= idEntrenador %>&idCliente=<%= idCliente %>">Quitar rutina</a> </td>
    </tr>
    <%
        }
    }
    %>
</table>
</body>
</html>
