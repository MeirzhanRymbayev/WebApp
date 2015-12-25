<%@tag pageEncoding="UTF-8"%>
<%@attribute name="title" type="java.lang.String" required="false" %>
<html>
<head>
    <title>${title}</title>
    <script src="js/jquery-1.11.3.js" type="text/javascript"></script><%--jquery lib should be first in line--%>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <script src="js/bootstrap.js" type="text/javascript"></script>
    <script src="js/equalHeights.js" type="text/javascript"></script>
</head>
<body>
<jsp:doBody/>
</body>
</html>
