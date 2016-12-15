<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Standings">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
             <th></th>
            <th>Name</th>
            <th>City</th>
            <th>Country</th>
            <th>Score</th>
            <th>Points</th>
            
       </tr>
        </thead>
        <tbody>
        <c:set var="count" value="1" scope="page" />
        <c:forEach items="${teams}" var="team">
            <tr>
                <td><c:out value="${count}"/></td>
                <td><c:out value="${team.name}"/></td>
                <td><c:out value="${team.city}"/></td>
                <td><c:out value="${team.country}"/></td>
                <td><c:out value="${score[count-1][0]} : ${score[count-1][1]}"/></td>
                <td><c:out value="${points[count-1]}"/></td>              
            </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>