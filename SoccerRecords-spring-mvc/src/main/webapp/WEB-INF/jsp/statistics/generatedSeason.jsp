<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Generated season">

    <jsp:attribute name="body">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/game.css" type="text/css">

        <c:forEach items="${seasonMatches}" var="round" varStatus="loop">
            <h2>Round ${loop.index + 1}</h2>
            
            <table class="table" align="center">
                <colgroup>
                    <col span="1" style="width: 45%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 45%;">
                </colgroup>
                <thead><tr><th/></tr></thead>
                <tbody>
                    <c:forEach items="${round}" var="game">
                        <tr>
                            <td class="team" align="center">
                                <c:out value="${game.homeTeam.name}"/>
                            </td>
                            <td class="team" align="center"><c:out value="VS"/></td>
                            <td class="team" align="center">
                                <c:out value="${game.guestTeam.name}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:forEach>

    </jsp:attribute>
</my:pagetemplate>