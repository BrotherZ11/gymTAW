<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.dto.Exercise" %>
<%@ page import="com.example.gymtaw.dto.User" %>
<%@ page import="com.example.gymtaw.dto.ExerciseHasSession" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ExerciseHasSession> listaEjercicios = (List<ExerciseHasSession>) request.getAttribute("listaEjercicios");
    Integer id = (Integer) request.getAttribute("idRutina");
    String nombreSesion = (String) request.getAttribute("nombreSesion");
%>
<html>
<head>
    <title>Ejercicios</title>
</head>
<body>
<div align="right">
    <a href="/salir">Log out</a>
</div>
<h1> Ejercicios <%=nombreSesion%></h1>
<a href="/home/trainer/ver?idRutina=<%=id%>">Cancelar</a>
<table border="1 ">

    <tr>
        <th>ORDEN</th>
        <th>NOMBRE</th>
        <th>DESCRIPCION</th>
        <th>VIDEO</th>
        <th>TIPO</th>
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
        for(ExerciseHasSession e : listaEjercicios){
    %>
    <tr>
        <td><%=e.getId().getOrder()%></td>
        <td><%=e.getExercise().getName()%></td>
        <td><%=e.getExercise().getDescription()%></td>
        <td><%=e.getExercise().getVideo()%></td>
        <td><%=e.getExercise().getTypeIdtype().getName()%></td>
    </tr>
    <%
                i++;
            }
        }
    %>
</table>
</body>
</html>
