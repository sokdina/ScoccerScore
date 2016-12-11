<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Select team to generate">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/statistics/generated">
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>City</th>
            <th>Country</th>
            <th>Select</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teams}" var="team">
            <tr>
                <td><c:out value="${team.name}"/></td>
                <td><c:out value="${team.city}"/></td>
                <td><c:out value="${team.country}"/></td>
                <td><input type="checkbox" name="teamIds" value="${team.id}"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-success" style="float: right;" type="submit">Generate</button>
    </form>

</jsp:attribute>
</my:pagetemplate>
