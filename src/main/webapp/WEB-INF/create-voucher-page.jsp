<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Tour</title>
</head>
<body>
<form action="/controller" method="post" >
    <input type="hidden" name="action" value="create-voucher"/>
    <input type="text" name="voucher-type" id="typeOfTour" placeholder="Type of the voucher"/>
    <input type="text" name="voucher-cost" id="costOfTour" placeholder="Cost of the voucher"/>
    <button type="submit">Add voucher to catalog</button>
</form>
</body>
</html>