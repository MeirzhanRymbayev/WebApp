<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="vouchers" type="java.util.List" required="false" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<%--@elvariable id="vouchers" type="java.util.List"--%>
<%--@elvariable id="fileNames" type="java.util.List"--%>
<%--@elvariable id="voucher" type="kz.epam.mrymbayev.model.Voucher"--%>
<%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
<c:if test="${user.role.name != 'user'}">
    <label><fmt:message key="authorization.necessary" bundle="${msg}"/></label>
</c:if>
<c:forEach items="${vouchers}" var="voucher">
    <table class='tabl1'>
        <tr>
            <td class='pr1'>
                <fmt:bundle basename="messages" prefix="manager.voucher.">
                    <ul class="list-group">

                        <li class="list-group-item"><span class="well well-sm">ID</span>${voucher.id}</li>
                        <li class="list-group-item"><span class="well well-sm"><fmt:message key="type"/></span>
                                ${voucher.type}</li>
                        <li class="list-group-item"><span class="well well-sm"><fmt:message key="cost"/></span>
                                ${voucher.cost}$</li>
                        <c:if test="${user.discount > 0}"><li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="cost.with.user.discount"/></span>
                                ${voucher.cost - voucher.cost * user.discount}$</li></c:if>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="status"/></span>
                            <c:if test="${!voucher.hot}"><fmt:message key="status.voucher.is.not.hot"/></c:if>
                            <c:if test="${voucher.hot}"><fmt:message key="status.voucher.is.hot"/></c:if></li>
                        <c:if test="${voucher.hot}">
                            <li class="list-group-item">
                                <span class="well well-sm"><fmt:message key="cost.hot"/></span>
                                <fmt:formatNumber value="${voucher.cost - voucher.cost * voucher.discount}"/>$</li>
                        </c:if>
                        <c:if test="${voucher.discount > 0}"><li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="discount.percentage"/></span>
                            <fmt:formatNumber value="${voucher.discount*100}"/>%</li>
                        </c:if>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="hotel"/></span>
                                ${voucher.hotel}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="country"/></span>
                                ${voucher.country}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="days.and.nights.quantity"/></span>
                                ${voucher.dayNightAmount}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="start.date"/></span>
                                ${voucher.startDate}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="end.date"/></span>
                                ${voucher.endDate}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="transport"/></span>
                                ${voucher.transport}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="residue"/></span>
                                ${voucher.quantity}</li>

                    </ul>
                </fmt:bundle>
            </td>
            <td>
                <c:if test="${user.role.name == 'user'}">
                <form action="/controller" method="post">
                <input type="hidden" name="action" value="buy">
                <input type="hidden" name="id" value="${voucher.id}">
                <select name="amount">
                    <option value="1" selected>1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                </select>
                <button type="submit">
                    <fmt:message key="voucher.buy" bundle="${msg}"/></button>
                </form></c:if>
            </td>
        </tr>
    </table>
    <div align="left">
        <c:forEach items="${voucher.fileNames}" var="fileName">
            <img width="300" height="250" src="${pageContext.request.contextPath}/image${voucher.folderName}${fileName}"/>
        </c:forEach>
    </div>
    <div class='hr-vnutr'></div>
    <p></p>
</c:forEach>
