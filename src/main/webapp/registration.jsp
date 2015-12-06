<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/controller" method="post" >
    <input type="hidden" name="action" value="register"/>
    <input type="text" name="login" id="login" placeholder="Login"/>
    <input type="password" name="password" id="password" placeholder="password"/>
    <button type="submit">Ok</button>
</form>
</body>
</html>