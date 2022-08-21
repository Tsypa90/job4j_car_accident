<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Text</th>
        <th scope="col">Address</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="accident" items="${accidents}">
        <tr>
            <td>${accident.id}</td>
            <td>${accident.name}</td>
            <td>${accident.text}</td>
            <td>${accident.address}</td>
            <td><a href="<c:url value='/update?id=${accident.id}'/>">Обновить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<c:url value='/create'/>">Добавить инцидент</a>
</body>
</html>