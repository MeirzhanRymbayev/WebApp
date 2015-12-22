<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<div id="content-center" class="content">
    <%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
    <c:if test="${user.role.name == 'guest'}">
        <fmt:message key="welcome.guest" var="guest" bundle="${msg}"/>
        <fmt:message key="welcome.word" bundle="${msg}">
            <fmt:param value="${guest}"/>
        </fmt:message>
    </c:if>


</div>