<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="kk" />
<fmt:setBundle basename="messages" var="msg"/>
<html>
<head>
    <title>Create Tour</title>
</head>
<body>
<%--@elvariable id="user" type="kz.epam.mrymbayev.model.User"--%>
<fmt:message key="welcome" bundle="${msg}">
    <fmt:param value="${user}"/>
</fmt:message>
<form action="/controller?action=create-voucher" method="post" enctype="multipart/form-data" >
    <p><input type="hidden" name="action" value="create-voucher"/></p>
    <p><input type="text" name="typeOfTour" id="typeOfTour" placeholder="Type of the voucher"/></p>
    <c:if test="${not empty typeError}">
        <font color="red">${typeError}</font>
    </c:if>
    <p><input type="text" name="cost" id="cost" placeholder="Cost of the voucher"/></p
    <c:if test="${not empty costError}">
        <font color="red">${costError}</font>
    </c:if>
    <p><input type="text" name="hotel" id="hotel" placeholder="Hotel"/></p>
    <c:if test="${not empty hotelError}">
        <font color="red">${hotelError}</font>
    </c:if>
    <p><input type="text" name="country" id="country" placeholder="Country"/></p>
    <c:if test="${not empty countryError}">
        <font color="red">${countryError}</font>
    </c:if>
    <p><input type="text" name="dayNightAmount" id="dayNightAmount" placeholder="Days and nights"/></p>
    <c:if test="${not empty dayNightAmountError}">
        <font color="red">${dayNightAmountError}</font>
    </c:if>
    <p><input type="text" name="transport" id="transport" placeholder="Transport"/></p>
    <c:if test="${not empty transportError}">
        <font color="red">${transportError}</font>
    </c:if>
    <p><input type="hidden" name="localeId" value="1" /></p>
    <p><input type="file" name="image" multiple /></p>
    <c:if test="${not empty uploadError}">
        <font color="red">${uploadError}</font>
    </c:if>
    <p><button type="submit">Add voucher to catalog</button></p>
    <p><label><input type="reset" value="Тазарту (Сбросить)"></label></p>
</form>