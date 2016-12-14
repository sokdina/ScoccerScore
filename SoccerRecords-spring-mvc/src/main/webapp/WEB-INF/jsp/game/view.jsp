<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Match result">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/game/delete/${game.id}">
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>


    <table class="table">
        <tbody>
            <tr>
                <td><big><c:out value="${game.homeTeam.name}"/></big></td>
                <td><big><c:out value="${game.homeScore}:${game.guestScore}"/></big></td>
                <td><big><c:out value="${game.guestTeam.name}"/></big></td>
            </tr>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>