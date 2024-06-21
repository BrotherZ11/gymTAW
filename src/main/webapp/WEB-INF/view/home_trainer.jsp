<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 08/05/2024
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Integer idEntrenador = (Integer) request.getAttribute("idEntrenador");%>
<html>
<head>
    <title>Home Trainer</title>
</head>
<body>
    <div>
        <a href="trainer/clients?idEntrenador=<%=idEntrenador%>">Clientes</a>
        <a href="trainer/rutina?idEntrenador=<%=idEntrenador%>">Rutinas</a>
        <a>Entrenamientos</a>
    </div>
</body>
</html>
