<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<fieldset><c:if test="${not empty roleError}">
    <font color="#d2691e">${roleError}</font><br>
</c:if>
    <fmt:message key="sign.in" bundle="${msg}"/>
    <form action="/controller" method="post">
        <input type="hidden" name="action" value="sign-in"/>
        <input type="text" name="login" id="login" placeholder="Login"/>
        <c:if test="${not empty loginError}">
            <font color="red">${loginError}</font>
        </c:if>
        <input type="password" name="password" id="password" placeholder="password"/>
        <c:if test="${not empty passwordError}">
            <font color="red">${passwordError}</font>
        </c:if>
        <button type="submit">Ok</button>
    </form>

</fieldset>
</body>
</html>