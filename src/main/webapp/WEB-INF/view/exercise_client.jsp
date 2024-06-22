<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>
<%@ page import="com.example.gymtaw.entity.UserEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ExerciseEntity> listaEjercicios = (List<ExerciseEntity>) request.getAttribute("listaEjercicios");
    List<ExerciseEntity> listaEjerciciosConDatos = (List<ExerciseEntity>) request.getAttribute("listaEjerciciosConDatos");
    Integer idSesion = (Integer) request.getAttribute("idSesion");
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    String nombreRutina = (String) request.getAttribute("nombreRutina");
    String nombreSesion = (String) request.getAttribute("nombreSesion");
    UserEntity cliente = (UserEntity) session.getAttribute("cliente");
%>
<html>
<head>
    <title>Ejercicios</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1> Ejercicios de <%=cliente.getName()%> <%=cliente.getSurname()%> </h1>
<p><a href="/home/trainer">Home</a> / <a href="clients">Clientes</a> / <a href="routine_client?idCliente=<%=cliente.getId()%>">Rutinas <%=cliente.getName()%> <%=cliente.getSurname()%></a> / <a href="session_client?idRutina=<%=idRutina%>"><%=nombreRutina%></a> / <%=nombreSesion%></p><br>
<table border="1 ">

    <tr>
        <th>ORDEN</th>
        <th>NOMBRE</th>
        <th>DESCRIPCION</th>
        <th>VIDEO</th>
        <th>TIPO</th>
        <th>DATOS</th>
    </tr>
    <%
        if(listaEjercicios.isEmpty()){
    %>
    <tr>
        <td> 0 </td>
        <td> No tiene ningun ejercicio </td>
        <td> - </td>
        <td> - </td>
        <td> - </td>
        <td> - </td>
    </tr>
    <%
    }else{
        int i = 1;
        for(ExerciseEntity e : listaEjercicios){
    %>
    <tr>
        <td><%=i%></td>
        <td><%=e.getName()%></td>
        <td><%=e.getDescription()%></td>
        <td><%=e.getVideo()%></td>
        <td><%=e.getTypeIdtype().getName()%></td>
        <td><a href="exercise_client_info?idEjercicio=<%= e.getId()  %>&idSesion=<%= idSesion %>&idRutina=<%= idRutina %>"><%=listaEjerciciosConDatos.contains(e)?"Editar datos":"Añadir datos"%></a> </td>
    </tr>
    <%
        i++;
        }
    }
    %>
</table>
</body>
</html>
