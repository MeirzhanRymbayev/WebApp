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
            <td rowspan='2' class='lv1'><img src='%s'/></td>
        </tr>
        <tr>
            <td class='pr1'>${v.type}<br/>
                    ${voucher.cost}<br/>
                    ${voucher.hotel}<br/>
                    ${voucher.country}<br/>
            </td>
        </tr>
    </table>
        <div align="center">
            <c:forEach items="${voucher.fileNames}" var="fileName">
                <img src="${pageContext.request.contextPath}/image${voucher.folderName}${fileName}"/>
            </c:forEach>
        </div>
        <c:if test="${user.role.name == 'user'}">
            <p><form action="/controller" method="post">
                <input type="hidden" name="action" value="make-voucher-hot">
                <input type="hidden" name="id" value="${voucher.id}">
            <label for="discount"><fmt:message key="voucher.discount.percentage" bundle="${msg}"/>
                <select name="discount" id="discount">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30" selected >30</option>
                    <option value="40">40</option>
                    <option value="50">50</option>
                    <option value="60">60</option>
                </select>
            </label>
                <button type="submit"><fmt:message key="voucher.make.hot" bundle="${msg}"/></button>
            </form></p>
        </c:if>
    <div class='hr-vnutr'></div>
</c:forEach>