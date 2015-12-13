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
    <li><a href="/controller?action=registration-page">Registration</a></li>
    <li><a href="/controller?action=sign-in-page">Sign in</a></li>
</ul>

</body>
</html>
