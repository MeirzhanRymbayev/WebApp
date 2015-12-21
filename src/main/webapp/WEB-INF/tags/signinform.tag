<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<div class="my-forms">
    <h3><label for="login"><fmt:message key="sign.in" bundle="${msg}"/></label></h3>
    <c:if test="${not empty roleError}">
        <font color="#d2691e">${roleError}</font><br>
    </c:if>
    <form action="/controller" method="post">
        <input type="hidden" name="action" value="sign-in"/>
        <div class="form-group">
            <label for="login"><fmt:message key="login" bundle="${msg}"/></label>
            <input type="text" name="login" id="login" class="form-control"/>
            <c:if test="${not empty loginError}">
                <font color="red">${loginError}</font>
            </c:if>
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="password" bundle="${msg}"/></label>
            <input type="password" name="password" id="password" class="form-control"/>
            <c:if test="${not empty passwordError}">
                <font color="red">${passwordError}</font>
            </c:if>
        </div>
        <button type="submit" class="btn btn-default"><fmt:message key="submit" bundle="${msg}"/></button>
    </form>
</div>