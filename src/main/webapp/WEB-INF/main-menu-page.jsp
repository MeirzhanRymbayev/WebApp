<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<t:page title="Main menu">
    <jsp:body>
        <div id="wrapper">
            <t:navbar/>
            <div class="content-wrapper">
                <t:leftcontent/>
                <t:maincontent/>
            </div>
            <t:footer/>
        </div>
    </jsp:body>
</t:page>

