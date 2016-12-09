<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="La Liga Division">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/team/update/${team.id}">
        <button type="submit" class="btn btn-primary">Update ${team.name}</button>
    </form>
    </br>

    <table class="table">
        <tbody>
            <tr>
                <td><big><c:out value="${team.id}"/></big></td>
                <td><big><c:out value="${team.name}"/></big></td>
                <td><big><c:out value="${team.city}"/></big></td>
		<td><big><c:out value="${team.country}"/></big></td>
            </tr>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>

