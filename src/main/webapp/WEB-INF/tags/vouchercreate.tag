<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages" var="msg"/>
<div class="my-forms">
    <form action="/controller?action=create-voucher" method="post" enctype="multipart/form-data">
        <p><input type="hidden" name="action" value="create-voucher"/></p>
        <div class="container">
            <div class="row">
                <div id="equalheight">
                    <div class="col-12 col-lg-4 col-md-4 col-sm-4 col-xs-12 demo">
                        <div class="info-block"><!-- BODY BOX-->
                            <p><t:form_kk/></p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-4 col-md-4 col-sm-4 col-xs-12 demo">
                        <div class="info-block"><!-- BODY BOX-->
                            <p><t:form_ru/></p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-4 col-md-4 col-sm-4 col-xs-12 demo">
                        <div class="info-block"><!-- BODY BOX-->
                            <p><t:form_en/></p>
                        </div>
                    </div>
                </div>
            </div><!--/row-->
        </div>
        <div class="container">
            <div id="simple-inputs">
                <div class="form-block">
                    <div class="form-group">
                        <label for="hotel"><fmt:message key="voucher.hotel" bundle="${msg}"/></label>
                        <p><input type="text" name="hotel" id="hotel" class="form-control"/></p>
                        <c:if test="${not empty hotelError}"><font color="red">${hotelError}</font></c:if>
                    </div>
                    <div class="form-group">
                        <label for="quantity"><fmt:message key="voucher.quantity" bundle="${msg}"/></label>
                        <p><input type="number" name="quantity" id="quantity" class="form-control"/></p>
                        <c:if test="${not empty quantityError}"><font color="red">${quantityError}</font></c:if>
                    </div>
                    <div class="form-group">
                        <label for="files"><fmt:message key="voucher.choose.files" bundle="${msg}"/></label>
                        <input id="files" type="file" name="image" multiple/>
                        <c:if test="${not empty uploadError}"><font color="red">${uploadError}</font></c:if>
                    </div>
                    <div class="form-group">
                        <label for="start-date">
                            <fmt:message key="manager.voucher.start.date" bundle="${msg}"/></label>
                        <input id="start-date" type="date" name="start-date"/>
                    </div>
                    <div class="form-group">
                        <label for="end-date">
                            <fmt:message key="manager.voucher.end.date" bundle="${msg}"/></label>
                        <input id="end-date" type="date" name="end-date"/>
                    </div>
                </div>
            </div>
            <p>
                <button type="submit" class="btn btn-default"><fmt:message key="submit" bundle="${msg}"/></button>
            </p>
            <label><input type="reset" class="btn btn-default" value="Тазарту (Сбросить)"></label>
        </div>
    </form>
</div>