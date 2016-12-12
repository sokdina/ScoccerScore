<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Add new Goal">
<jsp:attribute name="body">
    
    <form:form method="post" action="${pageContext.request.contextPath}/goal/create"
               modelAttribute="goalCreateDTO" cssClass="form-horizontal">
            <%@include file="form.jsp"%>       
    <button class="btn btn-success" style="float: right;" type="submit">Create goal</button>
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
