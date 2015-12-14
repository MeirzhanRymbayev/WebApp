<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
Hello ${sessionScope.user}
<ul>
    <li><a href="manage-index.jsp">Tour agent page</a></li>
    <li><a href="create-voucher.jsp">Create voucher page</a></li>
    <li><a href="registration-page.jsp">Registration</a></li>
    <li><a href="sign-in-page.jsp">Sign in</a></li>
</ul>

</body>
</html>