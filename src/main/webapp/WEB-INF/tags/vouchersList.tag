<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="vouchers" type="java.util.List" required="false"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<%--@elvariable id="vouchers" type="java.util.List"--%>
<%--@elvariable id="fileNames" type="java.util.List"--%>
<%--@elvariable id="voucher" type="kz.epam.mrymbayev.model.Voucher"--%>
<c:forEach items="${vouchers}" var="voucher">
    <div>
        ${v.type}
        <p>${voucher.cost}</p>
        <p>${voucher.hotel}</p>
        <p>${voucher.country}</p>
            <c:forEach items="${voucher.fileNames}" var="fileName">
                <img src="${pageContext.request.contextPath}/image${voucher.folderName}${fileName}"/>
            </c:forEach>

    </div>
</c:forEach>
