<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="messages"/>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
Hello ${sessionScope.user}
<fmt:message key="sign.out" bundle="${msg}"/>
<ul>
    <li><a href="manage-index.jsp">Tour agent page</a></li>
    <li><a href="create-voucher.jsp">Create voucher page</a></li>
    <li><a href="registration-page.jsp">Registration</a></li>
    <li><a href="sign-in-page.jsp">Sign in</a></li>
</ul>

</body>
</html>