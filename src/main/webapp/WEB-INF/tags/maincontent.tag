<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<div id="content-center">
    <%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
    <c:if test="${user.role.name == 'guest'}">
        <fmt:message key="welcome.guest" var="guest" bundle="${msg}"/>
        <fmt:message key="welcome.word" bundle="${msg}">
            <fmt:param value="${guest}"/>
        </fmt:message>
    </c:if>
        <div class="container">
            <div class="block-grid-lg-4 block-grid-md-3 block-grid-sm-2">
                <div class="block-grid-item">
                    <h1>User 1</h1>
                </div>
                <div class="block-grid-item">
                    <h1>User 2</h1>
                </div>
                <div class="block-grid-item">
                    <h1>User 3</h1>
                </div>
                <div class="block-grid-item">
                    <h1>User 4</h1>
                </div>
            </div>
        </div>

</div>