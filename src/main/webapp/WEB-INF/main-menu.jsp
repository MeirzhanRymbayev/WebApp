<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
Hello ${sessionScope.customer}
<a href="sign-out.jsp?action=sign-out">Sign out</a>

</body>
</html>

