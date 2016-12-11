<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Player Detail">
<jsp:attribute name="body">
            <div class="col-md-10" style="margin-right: 100px;"> 
                <div class="col-md-5" style="font-size: 35px;">Name:</div> 
                <div class="col-md-5" style="float: left;font-size: 25px;text-align: left;margin-top: 2px;color: dimgray;">
                    <big><c:out value="${player.name}"/></big>
                </div>
            </div>
            <div class="col-md-10" style="margin-right: 100px;"> 
                <div class="col-md-5" style="font-size: 35px;">Date Of Birth:</div>    
                <div class="col-md-5" style="float: left;font-size: 25px;text-align: left;margin-top: 2px;color: dimgray;">
                    <big><fmt:formatDate value="${player.dateOfBirth}" pattern="yyyy-MM-dd"/></big>
                </div> 
            </div>
            <div class="col-md-10" style="margin-right: 100px;"> 
                <div class="col-md-5" style="font-size: 35px;">Team:</div>      
                <div class="col-md-5" style="float: left;font-size: 25px;text-align: left;margin-top: 2px;color: dimgray;">
                    <big><c:out value="${player.team.name}"/></big>
                </div> 
            </div>
            <div class="col-md-10" style="margin-right: 100px;"> 
                <div class="col-md-5" style="font-size: 35px;">Dress Number:</div>  
                <div class="col-md-5" style="float: left;font-size: 25px;text-align: left;margin-top: 2px;color: dimgray;">
                    <big><c:out value="${player.dressNumber}"/></big>
                </div> 
            </div>
            <div class="col-md-10" style="margin-right: 100px;"> 
                <div class="col-md-5" style="font-size: 35px;">Position:</div>  
                <div class="col-md-5" style="float: left;font-size: 25px;text-align: left;margin-top: 2px;color: dimgray;">
                    <big><c:out value="${player.position}"/></big>
                </div> 
            </div>
            <div class="col-md-10" style="margin-right: 100px;"> 
                <div class="col-md-5" style="font-size: 35px;">Country:</div>  
                <div class="col-md-5" style="float: left;font-size: 25px;text-align: left;margin-top: 2px;color: dimgray;">
                    <big><c:out value="${player.country}"/></big>
                </div> 
            </div>
            <div class="col-md-10" style="margin-right: 100px;"> 
                <div class="col-md-5" style="font-size: 35px;">Goals:</div>  
                <div class="col-md-5" style="float: left;font-size: 25px;text-align: left;margin-top: 2px;color: dimgray;">
                    <big><c:out value="${player.goals.size()}"/></big>
                </div> 
            </div> 
            <br>
            <br>
            <br>
</jsp:attribute>
</my:pagetemplate>