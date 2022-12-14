<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
      integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"
        integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK"
        crossorigin="anonymous"></script>
<html>
<body>
<form  action="<c:url value='/edit?id=${accident.id}'/>" method='POST'>
  <table>
    <tr>
      <td>Название:</td>
      <td><input type='text' name='name'></td>
      <td>Описание:</td>
      <td><input type='text' name='text'></td>
      <td>Адрес:</td>
      <td><input type='text' name='address'></td>
      <td>
        <select name="type.id" required>
          <c:forEach var="type" items="${types}" >
            <option value="${type.id}">${type.name}</option>
          </c:forEach>
        </select>
      </td>
      <td>Статьи:</td>
      <td>
        <select name="rule.id" multiple required>
          <c:forEach var="rule" items="${rules}" >
            <option value="${rule.id}">${rule.name}</option>
          </c:forEach>
        </select>
    </tr>
    <tr>
      <td colspan='2'><input name="submit" type="submit" value="Обновить" /></td>
    </tr>
  </table>
</form>
</body>
</html>