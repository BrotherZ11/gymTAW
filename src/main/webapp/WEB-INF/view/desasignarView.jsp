<%@ page import="com.example.gymtaw.entity.UserEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Gonzalo Muñoz Rubio
  Date: 14/06/2024
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<UserEntity> usuariosAsignar = (List<UserEntity>) request.getAttribute("usuariosDesasignacion");%>
<% UserEntity usuario = (UserEntity) request.getAttribute("usuario");%>
<html>
<head>
    <title>Desasignación</title>
</head>
<body>
<h2><%if(usuario.getIdRol().getId() == 4){%>
    Desasignar entrenadores a <%=usuario.getName() + " " + usuario.getSurname()%>
    <%} else {%>
    Desasignar clientes a <%=usuario.getName() + " " + usuario.getSurname()%>
    <%}%>
</h2>
<form method="post" action="/users/realizarDesasignacion">
    <input type="hidden" name="idUsuario" value="<%=usuario.getId()%>"/>
    <table border="1">
        <tr>
            <td><%if(usuario.getIdRol().getId() == 4){%>
                Entrenadores asignados:
                <%} else {%>
                Clientes asignados:
                <%}%>
            </td>
            <td><select name="idsUsuariosDesasignar" multiple>
                <%for(UserEntity user: usuariosAsignar){%>
                <option value="<%=user.getId()%>" ><%=user.getName() + " " + user.getSurname()%></option>
                <%}%>
            </select></td>
        </tr>
    </table>
    <button>Desasignar</button>
</form>
</body>
</html>
