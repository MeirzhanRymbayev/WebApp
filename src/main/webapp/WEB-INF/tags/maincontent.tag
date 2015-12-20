<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<div id="content-center">
    Контент
    <%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
    <fmt:message key="welcome" bundle="${msg}">
        <fmt:param value="${user.firstName}"/>
    </fmt:message>

</div>