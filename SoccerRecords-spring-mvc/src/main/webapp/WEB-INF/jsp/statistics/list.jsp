<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="TOP Scorers">
<jsp:attribute name="body">
        
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>Name</th>
            <th>Date Of Birth</th>
            <th>Team</th>
            <th>Number</th>
            <th class="col-md-3">Goals</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="count" value="1" scope="page" />

        <c:forEach items="${players}" var="player">
            <tr>
                <td><c:out value="${count}"/></td>
                <td><my:a href="/statistics/viewPlayerDetail/${player.id}"><c:out value="${player.name}"/></my:a> </td>
                <td><fmt:formatDate value="${player.dateOfBirth}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${player.team.name}"/></td>                
                <td><c:out value="${player.dressNumber}"/></td>
                <td><c:out value="${player.getGoals().size()}"/></td>
            </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
        </tbody>
    </table>
       
</jsp:attribute>
</my:pagetemplate>