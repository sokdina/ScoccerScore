<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Goals">
<jsp:attribute name="body">

    <my:a href="/goal/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add Goal
    </my:a>
        
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Description</th>
            <th>Time</th>
            <th>Game</th>
            <th class="col-md-3">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${goals}" var="goal">
            <tr>
                <td><c:out value="${gaol.id}"/></td>
                <td><c:out value="${gaol.description}"/></td>
                <td><c:out value="${goal.goalTime}"/></td>
                
                <td><c:out value="${goal.game.homeTeam.name}"/>-<c:out value="${goal.game.guestTeam.name}"/></td>
                <td>
                    <my:a href="/goal/view/${goal.id}" class="btn btn-info">View</my:a>               
                    <my:a href="/goal/edit/${goal.id}" class="btn btn-warning">Edit</my:a>                
                    <form method="post" action="${pageContext.request.contextPath}/goal/delete/${goal.id}" style="display: inline-block;">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>