<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<fieldset>Регистрация
<form action="/controller" method="post" >
    <input type="hidden" name="action" value="register"/><br>
    <input type="text" name="login" id="login" placeholder="Login"/><br>
    <c:if test="${not empty loginError}">
        <font color="red">${loginError}</font><br>
    </c:if>
    <input type="password" name="password" id="password" placeholder="Password"/><br>
    <c:if test="${not empty passwordError}">
        <font color="red">${passwordError}</font><br>
    </c:if>
    <input type="text" name="firstname" id="firstname" placeholder="Firstname"/><br>
    <c:if test="${not empty firstnameError}">
        <font color="red">${firstnameError}</font><br>
    </c:if>
    <input type="text" name="lastname" id="lastname" placeholder="Lastname"/><br>
    <c:if test="${not empty lastnameError}">
        <font color="red">${lastnameError}</font><br>
    </c:if>
    <button type="submit">Ok</button>
</form>
</fieldset>
</body>
</html>