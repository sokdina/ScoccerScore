<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Match results">
    
<jsp:attribute name="body">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/game.css" type="text/css">
    <table class="table" align="center">
        <thead><tr><th/></tr></thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td><fmt:formatDate value="${game.dateOfGame}" pattern="yyyy-MM-dd"/></td>
                <td class="team" align="center">
                    <c:out value="${game.homeTeam.name}"/>
                    <ul class="goals">
                        <c:forEach items="${game.goals}" var="goal">
                            <c:forEach items="${players}" var="player">
                                <c:forEach items="${player.goals}" var="playerGoal">
                                    <c:if test="${goal.id == playerGoal.id and game.homeTeam.id == player.team.id}">
                                        <li>${player.name} ${goal.goalTime}'</li>
                                    </c:if>
                                </c:forEach>                                        
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td class="team" align="center"><c:out value="${game.homeScore}:${game.guestScore}"/></td>
                <td class="team" align="center">
                    <c:out value="${game.guestTeam.name}"/>
                    <ul class="goals">
                        <c:forEach items="${game.goals}" var="goal">
                            <c:forEach items="${players}" var="player">
                                <c:forEach items="${player.goals}" var="playerGoal">
                                    <c:if test="${goal.id == playerGoal.id and game.guestTeam.id == player.team.id}">
                                        <li>${player.name} ${goal.goalTime}'</li>
                                    </c:if>
                                </c:forEach>                                        
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>