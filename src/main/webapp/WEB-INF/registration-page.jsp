<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<fieldset>Регистрация
<form action="/controller" method="post" >
    <input type="hidden" name="action" value="register"/><br>
    <input type="text" name="login" id="login" placeholder="Login"/><br>
    <input type="password" name="password" id="password" placeholder="Password"/><br>
    <input type="text" name="firstname" id="firstname" placeholder="Firstname"/><br>
    <input type="text" name="lastname" id="lastname" placeholder="Lastname"/><br>
    <button type="submit">Ok</button>
</form>
</fieldset>
</body>
</html>