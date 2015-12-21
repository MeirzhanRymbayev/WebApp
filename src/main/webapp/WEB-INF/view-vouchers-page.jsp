<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<t:page title="View vouchers">
    <jsp:body>
        <div id="wrapper">
            <t:navbar/>
            <div id="content-wrapper">
                <t:leftcontent/>
                <div id="content-center">
                    <t:vouchersList vouchers="${vouchers}"/>
                </div>
            </div>
        </div>
        <t:footer/>
        </div>
    </jsp:body>
</t:page>
