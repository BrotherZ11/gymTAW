<%@ page import="com.example.gymtaw.entity.UserEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: gonla
  Date: 14/06/2024
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<UserEntity> usuariosAsignar = (List<UserEntity>) request.getAttribute("usuariosAsignacion");%>
<% UserEntity usuario = (UserEntity) request.getAttribute("usuario");%>
<html>
<head>
    <title>Asignación</title>
</head>
<body>
<h2><%if(usuario.getIdRol() == 4){%>
        Asignar entrenadores a <%=usuario.getName() + " " + usuario.getSurname()%>
    <%} else {%>
        Asignar clientes a <%=usuario.getName() + " " + usuario.getSurname()%>
    <%}%>
</h2>
<form method="post" action="/users/realizarAsignacion">
    <input type="hidden" name="idUsuario" value="<%=usuario.getId()%>"/>
    <table border="1">
        <tr>
            <td><%if(usuario.getIdRol() == 4){%>
                    Entrenadores:
                <%} else {%>
                    Clientes:
                <%}%>
            </td>
            <td><select name="idsUsuariosAsignar" multiple>
                <%for(UserEntity user: usuariosAsignar){%>
                <option value="<%=user.getId()%>" ><%=user.getName() + " " + user.getSurname()%></option>
                <%}%>
            </select></td>
        </tr>
    </table>
    <button>Asignar</button>
</form>
</body>
</html>
