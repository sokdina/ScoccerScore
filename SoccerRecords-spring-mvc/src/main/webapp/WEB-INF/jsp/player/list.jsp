<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Players">
<jsp:attribute name="body">

    <my:a href="/player/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add Player
    </my:a>
        
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Date Of Birth</th>
            <th>Team</th>
            <th>Number</th>
            <th class="col-md-3">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${players}" var="player">
            <tr>
                <td><c:out value="${player.name}"/></td>
                <td><fmt:formatDate value="${player.dateOfBirth}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${player.team.name}"/></td>
                
                <td><c:out value="${player.dressNumber}"/></td>
                <td>
                    <my:a href="/player/view/${player.id}" class="btn btn-info">View</my:a>               
                    <my:a href="/player/edit/${player.id}" class="btn btn-warning">Edit</my:a>                
                    <form method="post" action="${pageContext.request.contextPath}/player/delete/${player.id}" style="display: inline-block;">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>