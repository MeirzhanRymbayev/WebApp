<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<html>
<head>
    <title>Vouchers</title>

</head>
<body>
<%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
<fmt:message key="welcome" bundle="${msg}">
    <fmt:param value="${user}"/>
</fmt:message>
<ul>
    <li><a href="/controller?action=manage-index-page">Tour agent page</a></li>
    <li><a href="/controller?action=create-voucher-page">Create voucher page</a></li>
    <li><a href="/controller?action=registration-page">Registration</a></li>
    <c:if test="${sessionScope.user.role.name != 'guest'}">
        <li><a href="/controller?action=sign-in-page">Sign in</a></li>
        <li><a href="/controller?action=sign-out">Sign out</a><br></li>
    </c:if>
</ul>
<a href="/controller?action=view-vouchers-page">View vouchers</a><br>
<a href="/controller?action=set-locale&locale=kk">KZ</a><br>
<a href="/controller?action=set-locale&locale=ru">RU</a><br>
<a href="/controller?action=set-locale&locale=en">EN</a><br>
<%--@elvariable id="vouchers" type="java.util.List"--%>
<%--<c:forEach items="${vouchers}" var="v">
    <div>
        ${v.type}
        <p>${v.cost}</p>
        <p>${v.hotel}</p>
        <p>${v.country}</p>
    </div>
</c:forEach>--%>

</body>
</html>
