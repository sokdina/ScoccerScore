<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="La Liga Division">
<jsp:attribute name="body">

    <my:a href="/team/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add Team
    </my:a>
    </br>
    </br>

    <table class="table">
        <thead>
        <tr>
	    <th>Id</th>
            <th>Name</th>
            <th>City</th>
            <th>Country</th>
       </tr>
        </thead>
        <tbody>
        <c:forEach items="${teams}" var="team">
            <tr>
		<td><c:out value="${team.id}"/></td>
                <td><c:out value="${team.name}"/></td>
                <td><c:out value="${team.city}"/></td>
                <td><c:out value="${team.country}"/></td>
                <td>
                    <my:a href="/team/view/${team.id}" class="btn btn-primary">View</my:a>               
                    <my:a href="/team/edit/${team.id}" class="btn btn-primary">Edit</my:a>                
      		    <form method="post" action="${pageContext.request.contextPath}/team/delete/${team.id}" style="display: inline-block;">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>


