
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div class="form-group">
            <form:label path="gameId" cssClass="col-sm-2 control-label">Game</form:label>
            <div class="col-sm-10">
                <form:select path="gameId" cssClass="form-control">
                    <c:forEach items="${games}" var="g">
                        <form:option value="${g.id}">${g.dateOfGame} ${g.homeTeam.name} - ${g.guestTeam.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="gameId" cssClass="error"/></p>
            </div>
        </div>
        <div class="form-group">
            <form:label path="playerId" cssClass="col-sm-2 control-label">Player</form:label>
            <div class="col-sm-10">
                <form:select path="playerId" cssClass="form-control">
                    <c:forEach items="${players}" var="p">
                        <form:option value="${p.id}">${p.team.name}      ${p.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="playerId" cssClass="error"/></p>
            </div>
        </div>
        <div class="form-group">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:input path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="goalTime" cssClass="col-sm-2 control-label">Time</form:label>
            <div class="col-sm-10">
                <form:input path="goalTime" cssClass="form-control" min="0" max="110" step="1" type="number"/>
                <form:errors path="goalTime" cssClass="help-block"/>
            </div>
        </div>