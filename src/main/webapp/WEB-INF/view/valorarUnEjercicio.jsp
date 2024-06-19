<%@ page import="com.example.gymtaw.entity.ExerciseEntity" %><%--
  Created by IntelliJ IDEA.
  User: W10
  Date: 19/06/2024
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ExerciseEntity ejercicio = (ExerciseEntity) request.getAttribute("ejercicio");
%>
<html>
<head>
    <title><%=ejercicio.getName()%>></title>
</head>
<body>
<h1>Valorar "<%=ejercicio.getName()%>"</h1>
</body>
</html>
