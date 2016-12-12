
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div class="form-group">
            <form:label path="goalId" cssClass="col-sm-2 control-label">Goal</form:label>
            <div class="col-sm-10">
                <form:select path="teamId" cssClass="form-control">
                    <c:forEach items="${teams}" var="t">
                        <form:option value="${t.id}">${t.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="teamId" cssClass="error"/></p>
            </div>
        </div>
        <div class="form-group">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="dressNumber" cssClass="col-sm-2 control-label">Number</form:label>
            <div class="col-sm-10">
                <form:input path="dressNumber" cssClass="form-control" min="1" max="99" step="1" type="number"/>
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