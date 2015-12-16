<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<fmt:setLocale value="kk"/>
<fmt:setBundle basename="messages" var="msg"/>
<t:page title="INDEX">
    <jsp:body>
        <%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
        <fmt:message key="welcome" bundle="${msg}">
            <fmt:param value="${user.firstName}"/>
        </fmt:message>
        <ul>
            <li><a href="/controller?action=manage-index-page">Tour agent page</a></li>
            <li><a href="/controller?action=create-voucher-page">Create voucher page</a></li>
            <li><a href="/controller?action=registration-page">Registration</a></li>
            <li><a href="/controller?action=sign-in-page">Sign in</a></li>
            <li><a href="/controller?action=main-menu-page">Main menu</a></li>
        </ul>
        ${user.role.name}
    </jsp:body>
</t:page>
