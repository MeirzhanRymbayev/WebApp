<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Tour</title>
</head>
<body>
<form action="/controller" method="post" >
    <p><input type="hidden" name="action" value="create-voucher"/></p>
    <p><input type="text" name="typeOfTour" id="typeOfTour" placeholder="Type of the voucher"/></p>
    <p><input type="text" name="cost" id="cost" placeholder="Cost of the voucher"/></p>
    <p><input type="text" name="hotel" id="hotel" placeholder="Hotel"/></p>
    <p><input type="text" name="country" id="country" placeholder="Country"/></p>
    <p><input type="text" name="dayNightAmount" id="dayNightAmount" placeholder="Days and nights"/></p>
    <p><input type="text" name="transport" id="transport" placeholder="Transport"/></p>
    <p><input type="hidden" name="localeId" value="kk"/></p>
    <p><input type="file" multiple name="images"  /></p>
    <input type="text" name="" id="" placeholder=""/>
    <p><button type="submit">Add voucher to catalog</button></p>
    <p><label><input type="reset" value="Тазарту (Сбросить)"></label></p>
</form>
</body>
</html>