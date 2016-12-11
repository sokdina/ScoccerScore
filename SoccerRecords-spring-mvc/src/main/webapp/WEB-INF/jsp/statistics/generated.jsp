<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Generated brackets">
<jsp:attribute name="body">

     <table class="table" align="center">
        <thead><tr>
            <th style="font-size: 25px; text-align: center;">Home</th>
            <th style="font-size: 25px; text-align: center;">Date</th>
            <th style="font-size: 25px; text-align: center;">Guest</th>
        </tr></thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td class="team" align="center" style="vertical-align: middle;font-size: 20px;">
                    <c:out value="${game.homeTeam.name}"/>                   
                </td>
                <td class="team" align="center">
                    <p><fmt:formatDate value="${game.dateOfGame}" pattern="dd. MM. yyyy"/></p>
                    <p><c:out value="- : -"/></p>
                </td>
                <td class="team" align="center" style="vertical-align: middle;font-size: 20px;">
                    <c:out value="${game.guestTeam.name}"/>                   
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
