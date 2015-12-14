<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
Hello ${sessionScope.user}<br>
<a href="/controller?action=sign-out">Sign out</a><br>
<a href="/controller?action=view-vouchers">View vouchers</a><br>
</body>
</html>

