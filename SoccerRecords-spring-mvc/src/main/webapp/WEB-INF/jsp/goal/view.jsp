<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Goal Detail">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Description</th>
            <th>Time</th>
            <th>Game</th> 
        </tr>
        </thead>
        <tbody>
            <tr>
                <td><big><c:out value="${goal.id}"/></big></td>
                <td><big><c:out value="${goal.description}"/></big></td>             
                <td><big><c:out value="${goal.goalTime}"/></big></td>
                <td><big><c:out value="${goal.game.homeTeam.name}"/>-<c:out value="${goal.game.guestTeam.name}"/></big></td>
                <td>                
                    <form method="post" action="${pageContext.request.contextPath}/goal/delete/${goal.id}" style="display: inline-block;">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>