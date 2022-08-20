<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Student</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>