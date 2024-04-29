<%@ page import="com.example.gymtaw.entity.Type" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: dzarz
  Date: 22/04/2024
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Type> lista = (List<Type>) request.getAttribute("lista");
%>
<html>
<head>
    <title>Listado</title>
</head>
<body>
<%

    for (Type tipo : lista){
%>
<tr>
    <td><%= tipo.getName() %></td> <br/>

</tr>
<%
    }
%>
</body>
</html>
