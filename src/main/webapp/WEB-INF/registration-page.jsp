<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<fieldset>Регистрация
<form action="/controller" method="post" >
    <input type="hidden" name="action" value="register"/>
    <input type="text" name="login" id="login" placeholder="Login"/>
    <input type="password" name="password" id="password" placeholder="Password"/>
    <input type="text" name="firstname" id="firstname" placeholder="Firstname"/>
    <input type="text" name="lastname" id="lastname" placeholder="Lastname"/>
    <button type="submit">Ok</button>
</form>
</fieldset>
</body>
</html>