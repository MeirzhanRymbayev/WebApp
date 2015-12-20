<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<ul class="nav navbar-nav"><%--Навигационное меню--%>
    <li class="active"><a href="/controller?action=main-menu-page"><fmt:message key="nav.main.page" bundle="${msg}"/><span class="sr-only">(current)</span></a></li>
    <li><a href="/controller?action=view-vouchers-page"><fmt:message key="nav.view.vouchers" bundle="${msg}"/></a></li>
    <c:if test="${sessionScope.user.role.name == 'manager'}">
        <li><a href="/controller?action=create-voucher-page"><fmt:message key="voucher.create" bundle="${msg}"/></a></li>
    </c:if>
</ul>