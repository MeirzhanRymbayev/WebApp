<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
       aria-haspopup="true" aria-expanded="false">
        <fmt:message key="authorization" bundle="${msg}"/>
        <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <c:if test="${sessionScope.user.role.name != 'user' && sessionScope.user.role.name != 'manager'}">
            <li><a href="/controller?action=sign-in-page"><fmt:message key="sign.in" bundle="${msg}"/></a></li>
            <li><a href="/controller?action=registration-page"><fmt:message key="registration" bundle="${msg}"/></a></li>
        </c:if>
        <c:if test="${sessionScope.user.role.name == 'user' || sessionScope.user.role.name == 'manager'}">
            <li><a href="/controller?action=sign-out"><fmt:message key="sign.out" bundle="${msg}"/></a></li>
        </c:if>
        <c:if test="${sessionScope.user.role.name != 'manager'}">
            <li role="separator" class="divider"></li>
            <li><a href="/controller?action=manage-index-page"><fmt:message key="tour.agent.page" bundle="${msg}"/></a></li>
        </c:if>
    </ul>
</li>