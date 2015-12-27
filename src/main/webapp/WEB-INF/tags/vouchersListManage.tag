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
<c:forEach items="${vouchers}" var="voucher">
    <table class='tabl1'>
        <tr>
            <td class='pr1'>
                <fmt:bundle basename="messages" prefix="manager.voucher.">
                    <ul class="list-group">

                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="type"/></span>
                                ${voucher.type}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="cost"/></span>
                        ${voucher.cost}$</li>
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
                        ${voucher.startDate.toString()}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="end.date"/></span>
                        ${voucher.endDate}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="transport"/></span>
                        ${voucher.transport}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="residue"/></span>
                        ${voucher.quantity}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="status"/></span>
                            <c:if test="${!voucher.hot}"><fmt:message key="status.voucher.is.not.hot"/></c:if>
                            <c:if test="${voucher.hot}"><fmt:message key="status.voucher.is.hot"/></c:if></li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="discount.percentage"/></span>
                            <fmt:formatNumber value="${voucher.discount * 100}"/>%</li>
                    </ul>
                </fmt:bundle>
            </td>
            <td>
                <p>
                <form action="/controller" method="post">
                    <input type="hidden" name="action" value="make-voucher-hot">
                    <input type="hidden" name="id" value="${voucher.id}">
                    <label for="discount"><fmt:message key="voucher.discount.percentage" bundle="${msg}"/>
                        <select name="discount" id="discount">
                            <option value="0.1">10</option>
                            <option value="0.2">20</option>
                            <option value="0.3" selected>30</option>
                            <option value="0.4">40</option>
                            <option value="0.5">50</option>
                            <option value="0.6">60</option>
                        </select>
                    </label>
                    <button type="submit"><fmt:message key="nav.manager.voucher.make.hot" bundle="${msg}"/></button>
                </form>
                </p>
            </td>
        </tr>
    </table>
    <div class='hr-vnutr'></div>
</c:forEach>
