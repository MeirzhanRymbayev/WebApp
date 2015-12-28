<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<div class="my-authorization-forms">
    <h3><label for="login"><fmt:message key="registration" bundle="${msg}"/></label></h3>
    <form action="/controller" method="post">
        <input type="hidden" name="action" value="register"/>
        <div class="form-group">
            <label for="login"><fmt:message key="login" bundle="${msg}"/></label>
            <input type="text" name="login" class="form-control" id="login" />
        </div>
        <c:if test="${not empty loginError}">
            <font color="red">${loginError}</font><br>
        </c:if>
        <div class="form-group">
            <label for="password"><fmt:message key="password" bundle="${msg}"/></label>
            <input type="password" name="password" class="form-control" id="password" />
        </div>
        <c:if test="${not empty passwordError}">
            <font color="red">${passwordError}</font><br>
        </c:if>
        <div class="form-group">
            <label for="firstname"><fmt:message key="firstname" bundle="${msg}"/></label>
            <input type="text" name="firstname" class="form-control" id="firstname" />
        </div>
        <c:if test="${not empty firstnameError}">
            <font color="red">${firstnameError}</font><br>
        </c:if>
        <div class="form-group">
            <label for="lastname"><fmt:message key="lastname" bundle="${msg}"/></label>
            <input type="text" name="lastname" class="form-control" id="lastname" />
        </div>
        <c:if test="${not empty lastnameError}">
            <font color="red">${lastnameError}</font><br>
        </c:if>
        <button type="submit" class="btn btn-default"><fmt:message key="submit" bundle="${msg}"/></button>
    </form>
</div>