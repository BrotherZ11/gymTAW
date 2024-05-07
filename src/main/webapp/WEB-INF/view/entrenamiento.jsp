<%@ page import="java.util.List" %>
<%@ page import="com.example.gymtaw.entity.Routine" %><%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 06/05/2024
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Routine> rutinas = (List<Routine>) request.getAttribute("rutinas"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- si no tiene Rutinas, pone No tienes rutinas en la pagina
    si tiene rutinas, las pone en un select
    cargando automaticamente la que menor id tenga (la primera que pille)
    cuando cambias en el select la rutina, automaticamente cambia lo de debajo a la seleccionada
    salen las sesiones y debajo los ejercicios de cada una,
    con posibilidad de marcarlas como hechas y ver el video de cada ej--%>
<% if (rutinas.isEmpty()){%>
<div align="center"> <h1>NO TIENES RUTINAS</h1></div>
<%}else{%>
<select>
    <% for (Routine r : rutinas){%>
    <option><%=r.getName()%></option>
    <%}%>
</select>
<%}%>
</body>
</html>
