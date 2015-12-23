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
<%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
<c:forEach items="${vouchers}" var="voucher">
        ${v.type}
        <p>${voucher.cost}</p>
        <p>${voucher.hotel}</p>
        <p>${voucher.country}</p>
            <c:forEach items="${voucher.fileNames}" var="fileName">
                <img src="${pageContext.request.contextPath}/image${voucher.folderName}${fileName}"/>
            </c:forEach>

            <table class='tabl1'>
                <tr>
                    <td rowspan='2'  class='lv1'><img src='%s' /></td>
                </tr>
                <tr>
                    <td class='pr1'>${v.type}<br />
                            ${voucher.cost}<br />
                            ${voucher.hotel}<br/>
                            ${voucher.country}<br />
                    </td>
                </tr>
                <c:if test="${user.role.name == 'user'}">
                <a href="/controller?action=buy&id=${voucher.id}">
                    <button><fmt:message key="voucher.buy" bundle="${msg}"/></button>
                </a>
                </c:if>
            </table><div class='hr-vnutr'></div>
</c:forEach>
