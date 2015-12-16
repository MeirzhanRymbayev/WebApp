<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<fieldset><c:if test="${not empty roleError}">
    <font color="#d2691e">${roleError}</font><br>
</c:if>
    Войти
    <form action="/controller" method="post" >
        <input type="hidden" name="action" value="sign-in"/>
        <input type="text" name="login" id="login" placeholder="Login"/>
        <input type="password" name="password" id="password" placeholder="password"/>
        <button type="submit">Ok</button>
    </form>
</fieldset>
</body>
</html>