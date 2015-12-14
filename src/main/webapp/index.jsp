<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
Hello ${sessionScope.user}
<ul>
    <li><a href="/controller?action=manage-index-page">Tour agent page</a></li>
    <li><a href="/controller?action=create-voucher-page">Create voucher page</a></li>
    <li><a href="/controller?action=registration-page">Registration</a></li>
    <li><a href="/controller?action=sign-in-page">Sign in</a></li>
</ul>

</body>
</html>
