<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Create a new team for La Liga Division">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/team/create"
               modelAttribute="teamCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${city_error?'has-error':''}">
            <form:label path="city" cssClass="col-sm-2 control-label">City</form:label>
            <div class="col-sm-10">
                <form:input path="city" cssClass="form-control"/>
                <form:errors path="city" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${country_error?'has-error':''}">
            <form:label path="country" cssClass="col-sm-2 control-label">Country</form:label>
            <div class="col-sm-10">
                <form:input path="country" cssClass="form-control"/>
                <form:errors path="country" cssClass="help-block"/>
            </div>
        </div>
         <center>
         <button style="float: right;" class="btn btn-success" type="submit">Create New Team</button>
        </center>
	</br>
	</br>
    </form:form>

</jsp:attribute>
</my:pagetemplate>

