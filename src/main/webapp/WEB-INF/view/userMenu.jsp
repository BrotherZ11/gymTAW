<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .button-container {
            display: flex;
           justify-content: space-between;
            align-items: center;
            width: 80%;
            margin: 0 auto;
        }
        .button-container button {
            width: 45%;
        }
        .button-container img{
            width: 100%;
            height: auto;
        }

    </style>
</head>
<body>
<h1 align="center">TAW</h1>
<a>Cerrar sesion</a> <%--Aqui lleva al login?--%>

<div class="button-container">
    <form method="get" action="/entrenamiento" >
        <button type="submit" style="width: 250px; height: 200px">
            <img src="images/dumbell.png" style="width: 200px; height: 150px">
        </button>
        <h4>Entrenamientos</h4>
    </form>



    <form method="get" action="/desempenio">
        <button type="submit" style="width: 250px; height: 200px">
            <img src="images/star.png" style="width: 200px; height: 150px">
        </button>
        <h4>Desempeño</h4>
    </form>

</div>

</body>
</html>