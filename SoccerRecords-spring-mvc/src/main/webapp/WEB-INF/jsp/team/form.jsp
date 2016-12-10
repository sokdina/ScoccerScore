
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

           <div class="form-group">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="city" cssClass="col-sm-2 control-label">City</form:label>
            <div class="col-sm-10">
                <form:input path="city" cssClass="form-control"/>
                <form:errors path="city" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="country" cssClass="col-sm-2 control-label">Country</form:label>
            <div class="col-sm-10">
                 <form:input path="country" cssClass="form-control"/>
                <form:errors path="country" cssClass="help-block"/>
            </div>
        </div>
     
