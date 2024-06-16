<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RoutineEntity> rutinas = (List<RoutineEntity>) request.getAttribute("rutinas");
    List<SessionEntity> sesiones = (List<SessionEntity>) request.getAttribute("sesiones");
    List<ValoracionEntity> valoraciones = (List<ValoracionEntity>) request.getAttribute("valoraciones");
    List<ExerciseEntity> ejercicios = (List<ExerciseEntity>) request.getAttribute("ejercicios");
%>
<html>
<head>
    <title>Valoraciones</title>
</head>
<body>
<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">

<h1>Valoraciones del Cliente</h1>

<%for(RoutineEntity r : rutinas){

%>
<h2><%=r.getName()%></h2>
<%
for(SessionEntity s : sesiones){

%>
<h3><%=s.getName()%></h3>
<form action="/guardarValoracion" method="post">
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
        <td><%=e.getName()%></td>
        <td><%=e.getDescription()%></td>
        <td><%=e.getVideo()%></td>
        <%
            List<ValoracionEntity> val = (List<ValoracionEntity>) e.getValoracionsById();
            for(ValoracionEntity v : val){

                boolean done = false;
                if(v.getDone() == 1) done = true;
                if(done){
        %>
        <input type="hidden" name="idCliente" value="<%=r.getIdclient()%>">
        <input type="hidden" name="idEjercicio" value="<%=e.getId()%>">
        <td align="center"><input type="text" name="estrellas" placeholder="<%=v.getStars()%>" value="<%=v.getStars()%>"></td>
        <%}else{
            String valor = "";
        if(v.getStars() == null) valor = "Por favor introduzca valor";
        %>
        <td align="center"><input type="text" name="estrellas" placeholder="<%=valor%>>" value="<%=v.getStars()%>" ></td>
        <%
                }
            }
        %>
    </tr>

</table>

</br>
<%}
%>
<button>Guardar valoraciones</button>
</form>
<%
}}%>



</body>
</html>
