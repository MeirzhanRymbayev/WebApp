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
        <p><input type="file" name="image" multiple/></p>
        <c:if test="${not empty uploadError}"><font color="red">${uploadError}</font></c:if>
        <p><fmt:message key="manager.voucher.start.date"/><input type="date" name="start-date" /></p>
        <p><fmt:message key="manager.voucher.end.date"/><input type="date" name="end-date"/></p>
        <p><button type="submit" class="btn btn-default"><fmt:message key="submit" bundle="${msg}"/></button></p>
        <label><input type="reset" class="btn btn-default" value="Тазарту (Сбросить)"></label>
    </form>
</div>