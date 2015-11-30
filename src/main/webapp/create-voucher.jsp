<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Tour</title>
</head>
<body>
<form action="/controller" method="post" >
    <input type="hidden" name="action" value="create-tour"/>
    <input type="text" name="tour-type" id="typeOfTour" placeholder="Type of the tour"/>
    <input type="text" name="tour-cost" id="costOfTour" placeholder="Cost of the tour"/>
    <button type="submit">Add voucher to catalog</button>
</form>
</body>
</html>