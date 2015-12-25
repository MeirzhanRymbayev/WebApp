<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<t:page title="Tour agent page">
    <jsp:body>
        <div id="wrapper">
            <t:navbar/>
            <div class="content-wrapper">
                <t:leftcontent/>
                <div id="content-center" class="content">
                    //TODO
                    <a href="create-voucher-page.jsp">Create voucher</a>
                    <a href="make-voucher-hot-page.jsp">Make voucher hot</a>
                </div>
            </div>
            <t:footer/>
        </div>
    </jsp:body>
</t:page>
