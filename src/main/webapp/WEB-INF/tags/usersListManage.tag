<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="users" type="java.util.List" required="false" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<%--@elvariable id="users" type="java.util.List"--%>
<%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
<c:forEach items="${users}" var="user">
    <table class='tabl1'>
        <tr>
            <td class='pr1'>
                <fmt:bundle basename="messages" prefix="manager.user.">
                    <ul class="list-group">

                        <li class="list-group-item">
                            <span class="well well-sm">ID</span>
                                ${user.id}
                        </li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="firstname"/></span>
                                ${user.firstName}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="lastname"/></span>
                                ${user.lastName}</li>
                        <li class="list-group-item">
                            <span class="well well-sm"><fmt:message key="discount"/></span>
                            <fmt:formatNumber value="${user.discount*100}"/></li>
                    </ul>
                </fmt:bundle>
            </td>
            <td>
                <p>
                <form action="/controller" method="post">
                    <input type="hidden" name="action" value="set-user-discount">
                    <input type="hidden" name="id" value="${user.id}">
                    <label for="discount"><fmt:message key="user.discount.percentage" bundle="${msg}"/>
                        <select name="discount" id="discount">
                            <option value="0.05" selected>5</option>
                            <option value="0.1">10</option>
                            <option value="0.2">20</option>
                            <option value="0.3">30</option>
                            <option value="0.4">40</option>
                            <option value="0.5">50</option>
                        </select>
                    </label>
                    <button type="submit"><fmt:message key="manager.user.submit" bundle="${msg}"/></button>
                </form>
                </p>
            </td>
        </tr>
    </table>
    <div class='hr-vnutr'></div>
</c:forEach>