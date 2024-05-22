<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ExerciseEntity> lista = (List<ExerciseEntity>) request.getAttribute("lista");
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
</body>
</html>
