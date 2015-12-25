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
                <img width="300" height="250" src="${pageContext.request.contextPath}/image${voucher.folderName}${fileName}"/>
            </c:forEach>
        </div>
        <c:if test="${user.role.name == 'user'}">

            <p>
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
            </form>
            </p>
        </c:if>
    <div class='hr-vnutr'></div>
</c:forEach>
