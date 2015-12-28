<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="kk"/>
<fmt:bundle basename="messages">
    <div class="form-block">
        <h3><label><fmt:message key="voucher.create"/></label></h3>
        <div class="form-group">
            <label for="typeOfTour"><fmt:message key="voucher.type"/></label>
            <p><input type="text" name="typeOfTour" id="typeOfTour" class="form-control"/></p>
            <c:if test="${not empty typeError}"><font color="red">${typeError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="cost"><fmt:message key="voucher.cost"/></label>
            <p><input type="text" name="cost" id="cost" class="form-control"/></p>
            <c:if test="${not empty costError}"><font color="red">${costError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="hotel"><fmt:message key="voucher.hotel"/></label>
            <p><input type="text" name="hotel" id="hotel" class="form-control"/></p>
            <c:if test="${not empty hotelError}"><font color="red">${hotelError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="country"><fmt:message key="voucher.country"/></label>
            <p><input type="text" name="country" id="country" class="form-control"/></p>
            <c:if test="${not empty countryError}"><font color="red">${countryError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="dayNightAmount"><fmt:message key="voucher.dayNightAmount"/></label>
            <p><input type="text" name="dayNightAmount" id="dayNightAmount" class="form-control"/></p>
            <c:if test="${not empty dayNightAmountError}"><font color="red">${dayNightAmountError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="transport"><fmt:message key="voucher.transport"/></label>
            <p><input type="text" name="transport" id="transport" class="form-control"/></p>
            <c:if test="${not empty transportError}"><font color="red">${transportError}</font></c:if>
        </div>
        <div class="form-group">
            <label for="quantity"><fmt:message key="voucher.quantity"/></label>
            <p><input type="number" name="quantity" id="quantity" class="form-control"/></p>
            <c:if test="${not empty quantityError}"><font color="red">${quantityError}</font></c:if>
        </div>
        <p><input type="hidden" name="localeId" value="1"/></p>
    </div>
</fmt:bundle>