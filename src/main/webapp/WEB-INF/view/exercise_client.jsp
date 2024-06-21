<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ExerciseEntity> listaEjercicios = (List<ExerciseEntity>) request.getAttribute("listaEjercicios");
    List<ExerciseEntity> listaEjerciciosConDatos = (List<ExerciseEntity>) request.getAttribute("listaEjerciciosConDatos");
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
        <th>ORDEN</th>
        <th>NOMBRE</th>
        <th>DESCRIPCION</th>
        <th>VIDEO</th>
        <th>TIPO</th>
        <th></th>
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
        <td><a href="exercise_client_info?idEjercicio=<%= e.getId()  %>"><%=listaEjerciciosConDatos.contains(e)?"Editar datos":"Crear datos"%></a> </td>
    </tr>
    <%
        i++;
        }
    }
    %>
</table>
</body>
</html>
