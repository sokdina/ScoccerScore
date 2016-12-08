<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Match results">
<jsp:attribute name="body">

    <my:a href="/game/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New match
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>Date of match</th>
            <th>Home team</th>
            <th>Result</th>
            <th>Guest team</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td><fmt:formatDate value="${game.dateOfGame}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${game.homeTeam.name}"/></td>
                <td><c:out value="${game.homeScore}:${game.guestScore}"/></td>
                <td><c:out value="${game.guestTeam.name}"/></td>
                <td>
                    <my:a href="/game/view/${game.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/game/delete/${game.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>