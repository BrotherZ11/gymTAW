<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<SessionEntity> sesiones = (List<SessionEntity>) request.getAttribute("sesiones");
List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
SessionEntity sessionActual = (SessionEntity) request.getAttribute("sessionActual");
%>
<html>
<head>
    <title>Sesiones</title>
</head>
<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">


<h1>Sesiones del Cliente</h1>

<%for(SessionEntity s : sesiones){%>
<h2><%=s.getName()%></h2>
<%
for(ExerciseEntity e : ejercicios){

%>
<table border="1">
    <tr>
        <th>Nombre ejercicio</th>
        <th>Descripcion</th>
        <th>Video</th>
        <th>Valoración</th>
    </tr>
    <tr>
        <td><a href="/home/cliente/ejercicioIndividual?idEjercicio=<%=e.getId()%>"><%=e.getName()%></a></td>
        <td><%=e.getDescription()%></td>
        <td><%=e.getVideo()%></td>
        <%
            List<ValoracionEntity> val = (List<ValoracionEntity>) e.getValoracions();
            for(ValoracionEntity v : val){
                boolean done = false;
                if(v.getDone() == 1) done = true;
                if(done){
        %>
        <td align="center"><%=v.getStars()%></td>
        <%}else{%>
        <td align="center"><a href="valorar?idSesion=<%=s.getId()%>">Valorar</a></td>
        <%
                }
            }
        %>
    </tr>

</table>
</br>
<%}%>


<%}%>
</body>
</html>
