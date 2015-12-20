<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
       aria-haspopup="true" aria-expanded="false">
        <fmt:message key="select.language" bundle="${msg}"/>
        <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a href="/controller?action=set-locale&locale=kk">KZ</a></li>
        <li role="separator" class="divider"></li>
        <li><a href="/controller?action=set-locale&locale=ru">RU</a></li>
        <li role="separator" class="divider"></li>
        <li><a href="/controller?action=set-locale&locale=en">EN</a></li>
    </ul>
</li>