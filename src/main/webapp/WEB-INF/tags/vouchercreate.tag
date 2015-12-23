<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<div class="my-forms">
    <h3><label><fmt:message key="voucher.create" bundle="${msg}"/></label></h3>
    <form action="/controller?action=create-voucher" method="post" enctype="multipart/form-data">
        <p><input type="hidden" name="action" value="create-voucher"/></p>
        <div class="form-group">
            <label for="typeOfTour"><fmt:message key="voucher.type" bundle="${msg}"/></label>
            <p><input type="text" name="typeOfTour" id="typeOfTour" class="form-control"/></p>
            <c:if test="${not empty typeError}"><font color="red">${typeError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="cost"><fmt:message key="voucher.cost" bundle="${msg}"/></label>
            <p><input type="text" name="cost" id="cost" class="form-control"/></p>
            <c:if test="${not empty costError}"><font color="red">${costError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="hotel"><fmt:message key="voucher.hotel" bundle="${msg}"/></label>
            <p><input type="text" name="hotel" id="hotel" class="form-control"/></p>
            <c:if test="${not empty hotelError}"><font color="red">${hotelError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="country"><fmt:message key="voucher.country" bundle="${msg}"/></label>
            <p><input type="text" name="country" id="country" class="form-control"/></p>
            <c:if test="${not empty countryError}"><font color="red">${countryError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="dayNightAmount"><fmt:message key="voucher.dayNightAmount" bundle="${msg}"/></label>
            <p><input type="text" name="dayNightAmount" id="dayNightAmount" class="form-control"/></p>
            <c:if test="${not empty dayNightAmountError}"><font color="red">${dayNightAmountError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="transport"><fmt:message key="voucher.transport" bundle="${msg}"/></label>
            <p><input type="text" name="transport" id="transport" class="form-control"/></p>
            <c:if test="${not empty transportError}"><font color="red">${transportError}</font></c:if>
        </div>
        <p><input type="hidden" name="localeId" value="1"/></p>
        <p><input type="file" name="image" multiple/></p>
        <c:if test="${not empty uploadError}"><font color="red">${uploadError}</font></c:if>
        <input type="date" name="start-date" />
        <input type="date" name="end-date"/>
        <button type="submit" class="btn btn-default"><fmt:message key="submit" bundle="${msg}"/></button>
        <label><input type="reset" class="btn btn-default" value="Тазарту (Сбросить)"></label>
    </form>
</div>