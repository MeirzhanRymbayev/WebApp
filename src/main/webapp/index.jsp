<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
Hello ${sessionScope.customer}
<ul>
    <li><a href="manage-index.jsp">Tour agent page</a></li>
    <li><a href="create-voucher.jsp">Create voucher page</a></li>
    <li><a href="registration.jsp">Registration</a></li>
    <li><a href="sign-in.jsp">Sign in</a></li>
</ul>

</body>
</html>
