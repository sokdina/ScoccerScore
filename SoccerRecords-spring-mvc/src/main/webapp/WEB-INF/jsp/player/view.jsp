<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Player Detail">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/player/delete/${player.id}">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>


    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Date Of Birth</th>
            <th>Team</th>
            <th>Number</th>
            <th>Position</th>
            <th>Country</th>  
        </tr>
        </thead>
        <tbody>
            <tr>
                <td><big><c:out value="${player.id}"/></big></td>
                <td><big><c:out value="${player.name}"/></big></td>
                <td><fmt:formatDate value="${player.dateOfBirth}" pattern="yyyy-MM-dd"/></td>               
                <td><big><c:out value="${player.team.name}"/></big></td>
                <td><big><c:out value="${player.dressNumber}"/></big></td>
                <td><big><c:out value="${player.position}"/></big></td>
                <td><big><c:out value="${player.country}"/></big></td>
            </tr>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>