<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ExerciseEntity> lista = (List<ExerciseEntity>) request.getAttribute("lista");
    List<ExerciseEntity> listaCompleta = (List<ExerciseEntity>) request.getAttribute("listaCompleta");
    String filtro = request.getParameter("filtro");
    if (filtro == null) filtro = "";
%>
<html>
<head>
    <title>Ejercicios del Cliente</title>
</head>
<body>
<h1> Ejercicios del Cliente </h1>
<h2> TAW </h2>
<form:form method="post" action="/rutina_bodybuilding/filtrar" modelAttribute="filtro">
    Nombre de la rutina: <form:input path="titulo" />
    <form:button>Filtrar</form:button>
</form:form>
<table border="1 ">

    <tr>
        <th>DIA</th>
        <th>NOMBRE</th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <%
        if(lista.isEmpty()){
    %>
    <tr>
        <td>No tiene ninguna rutina</td>
        <td> - </td>
        <td> - </td>
        <td> - </td>
        <td> - </td>
        <td> - </td>
    </tr>
    <%
    }else{
        for(ExerciseEntity e : lista){
    %>
    <tr>
        <td>A</td>
        <td><%=e.getName()%></td>
        <td><a href="exercise_client?idSession=<%= e.getId()  %>">Ver</a> </td>
        <td> - </td>
        <td> - </td>
        <td> - </td>
    </tr>
    <%
        }
    }
    %>
</table>




<%
    if(listaCompleta.size() > lista.size()){
%>
<form method="post" action="asignar_rutina">
    <label>Selecciona una rutina: </label>
    <select id="rutinas" name="idRutina">
        <%
            for(ExerciseEntity ejercicio: listaCompleta){
                if(!lista.contains(ejercicio)){
        %>
        <option value=<%=ejercicio.getId()%>><%=ejercicio.getName()%></option>
        <%
                }
            }
        %>
    </select>
    <input type="hidden" name="idEntrenador" value="<%= idEntrenador %>">
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
        <td><a href="session_client?idRutina=<%= r.getIdroutine()  %>&idEntrenador=<%=idEntrenador%>">Ver</a> </td>
        <td><a href="quitar_rutina?idRutina=<%= r.getIdroutine()  %>&idEntrenador=<%= idEntrenador %>&idCliente=<%= idCliente %>">Quitar rutina</a> </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
