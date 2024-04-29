<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification Result</title>
</head>
<body>
<h2>Verification Result</h2>
<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>
<!-- You can add more content here if needed -->
</body>
</html>
