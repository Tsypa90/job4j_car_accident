<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
      integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"
        integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK"
        crossorigin="anonymous"></script>
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
        <th scope="col">Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="accident" items="${accidents}">
        <tr>
            <td>${accident.id}</td>
            <td>${accident.name}</td>
            <td>${accident.text}</td>
            <td>${accident.address}</td>
            <td>${accident.type.name}</td>
            <td><a href="<c:url value='/update?id=${accident.id}'/>">Обновить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<c:url value='/create'/>">Добавить инцидент</a>
</body>
</html>