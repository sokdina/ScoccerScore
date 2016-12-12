<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h2>Welcome to Soccer-Record System!</h2>
	</br>
        <p class="lead"> The Soccer Records System provides information about soccer teams, their players and a particular match between two teams. visitors are able to get more information about:</p>
	<ul style="list-style-type:circle;font-size: 18px ">
  		<li>Particular date and time of the match</li>
  		<li>The place of the match</li>
  		<li>The result of the match</li>
		<li>The report about team statistics in the specific league in particular session</li>
		<li>The report about a player in the particular team in a session</li>
                <li>To system you can assign with attributes:</li>
                <li>Email: <b>sokdina999@gmail.com</b></li>
                <li>Password: <b>admin</b></li>
                
	</ul>
                
    </div>

</jsp:attribute>
</my:pagetemplate>

