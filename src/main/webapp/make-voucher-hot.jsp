<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make Vouchers Hot</title>
</head>
<body>
<form action="/controller" method="post" >
    <input type="hidden" name="action" value="make-voucher-hot"/>
    <input type="text" name="voucher-type" id="typeOfTour" placeholder="Type of the voucher"/>
    <input type="text" name="voucher-cost" id="costOfTour" placeholder="Cost of the voucher"/>
    <button type="submit">Add voucher to catalog</button>
</form>
</body>
</html>