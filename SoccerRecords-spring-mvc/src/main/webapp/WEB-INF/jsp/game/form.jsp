
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <div class="form-group">
            <form:label path="dateOfGame" cssClass="col-sm-2 control-label">Date Of Match</form:label>
            <div  class="col-sm-10">
                <form:input path="dateOfGame" cssClass="form-control"/>
                <form:errors path="dateOfGame" cssClass="help-block"/>
            </div>
        </div>  

        <div class="form-group">
            <form:label path="homeTeam" cssClass="col-sm-2 control-label">Home team</form:label>
            <div class="col-sm-10">
                <form:select path="homeTeam" cssClass="form-control">
                    <c:forEach items="${teams}" var="t">
                        <form:option value="${t.id}">${t.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="homeTeam" cssClass="error"/></p>
            </div>
        </div>
            
            
        <div class="form-group">
            <form:label path="guestTeam" cssClass="col-sm-2 control-label">Guest team</form:label>
            <div class="col-sm-10">
                <form:select path="guestTeam" cssClass="form-control">
                    <c:forEach items="${teams}" var="t">
                        <form:option value="${t.id}">${t.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="guestTeam" cssClass="error"/></p>
            </div>
        </div>
            
        