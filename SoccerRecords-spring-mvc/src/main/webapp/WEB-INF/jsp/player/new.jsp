<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Add new Player">
<jsp:attribute name="body">
    
    <form:form method="post" action="${pageContext.request.contextPath}/player/create"
               modelAttribute="playerCreate" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="teamId" cssClass="col-sm-2 control-label">Team</form:label>
            <div class="col-sm-10">
                <form:select path="teamId" cssClass="form-control">
                    <c:forEach items="${teams}" var="t">
                        <form:option value="${t.id}">${t.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="teamId" cssClass="error"/></p>
            </div>
        </div>
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="dressNumber" cssClass="col-sm-2 control-label">Number</form:label>
            <div class="col-sm-10">
                 <form:input path="dressNumber" cssClass="form-control"/>
                <form:errors path="dressNumber" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="country" cssClass="col-sm-2 control-label">Country</form:label>
            <div class="col-sm-10">
                 <form:input path="country" cssClass="form-control"/>
                <form:errors path="country" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="dateOfBirth" cssClass="col-sm-2 control-label">Date Of Birth</form:label>
            <div  class="col-sm-10">
                <form:input path="dateOfBirth" cssClass="form-control"/>
                <form:errors path="dateOfBirth" cssClass="help-block"/>
            </div>
        </div>            
        <div class="form-group">
            <form:label path="position" cssClass="col-sm-2 control-label">Position</form:label>
            <div class="col-sm-10">
                <form:select path="position" cssClass="form-control">
                    <c:forEach items="${positions}" var="p">
                        <form:option value="${p}">${p}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="position" cssClass="error"/></p>
            </div>
        </div>
       

    <button class="btn btn-primary" type="submit">Create player</button>
    </form:form>



</jsp:attribute>
</my:pagetemplate>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>    
<script>
  $( function() {
    $( "#dateOfBirth" ).datepicker();
  } );
</script>
