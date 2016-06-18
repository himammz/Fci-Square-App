

<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/home.css">
<link rel="shortcut icon" type="image/x-icon" href="/FCISquareApp/images/mobile-icon-md.ico" />
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
	<title>Get Notification</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">

</head>
<style>
a:hover {
    color: red;
}
</style>
<body>

	<header>
		<span class="icon-bar"><img src = "/FCISquareApp/images/mobile-icon-md.png"/></span>
		<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/getNotification.css">
		FCI<span>SQUARE</span>
	</header>

		<div id="cssmenu">
		<ul>
			<li><button type="button" name="BarControl" onclick="AppearBar();" ></button></li>
		   	<li class='active'><a href='homepage' style="text-decoration:none">Profile</a></li>
		   	<li><a href='getNotification' style="text-decoration:none"> Get Notification</a></li>
		  	<li><a href='ActiveLog' style="text-decoration:none">Active Log</a></li>
		   	<li  style="margin-left:50px"><a href='logout' style="text-decoration:none">Logout :D </a></li>
		   	 	
	   </ul>
	</div>
	
	<div id="cssbar">
	
		 <li styel="padding-left:50px"><a href='followingList' style="text-decoration:none">Followers</a></li>
		 <li><a href='FollowPerson' style="text-decoration:none">Follow </a></li>
		 <li><a href='UnFollowPerson' style="text-decoration:none">UnFollow </a></li>
		 <li><a href='Checkin' style="text-decoration:none">CheckIn</a></li>
		 <li><a href='/FCISquareApp/app/showLocation' style="text-decoration:none">Show Location</a></li>
		 <li><a href='' style="text-decoration:none">Update Location</a></li>
		 <li><a href='addPlace' style="text-decoration:none">Add Place</a></li>
		 <li><a href='savePlace' style="text-decoration:none">Save Place</a></li>
		 <li><a href='MySavedPlaces' style="text-decoration:none">Saved Places</a></li>
		 <li><a href='showHome' style="text-decoration:none">Show Home</a></li>
		 <li><a href='showHomeByRate' style="text-decoration:none">Show HomeRate</a></li>
		 <li><a href='showHomeByCount' style="text-decoration:none">Show HomeCount</a></li>
		 <li><a href='showHomeByTime' style="text-decoration:none">Show HomeTime</a></li>
		 <li><a href='showHomeByPlace' style="text-decoration:none">Show HomePlace</a></li>
		
	</div>

		<c:set var="j"  value="${0}"/>
		<c:forEach var="i" items="${it}">
			
			<form action="showCheckin"  method="POST">
				
				<table>
					<tr>
						<td>
						<input type="hidden" name="type" value="${i.value.type}"> 
							
						<c:if test="${i.value.type != 'follow'}">
							
							<input type="hidden" name="id" value="${i.value.checkinID}">
							
							<c:if test="${i.value.type == 'like'}">
					 		
					 		<label style="margin-left:200px;">${i.value.name} likes your checkin
					 		<input type="submit" value = "show ${i.value.type}" id = "btn" onclick="doChange(this,this.form)" sytle="margin-left:50px;"/>
					 		</label>
							
							</c:if>
							
							<c:if test="${i.value.type == 'comment'}">
					 		
					 		<label style="margin-left:200px;">${i.value.name} comments in your checkin
					 		<input type="submit" value = "show ${i.value.type}" id = "btn" onclick="doChange(this,this.form)" sytle="margin-left:50px;"/>
					 		</label>
								
							</c:if>
							
							<c:if test="${i.value.type == 'checkin'}">
							
							<label style="margin-left:200px;">${i.value.name} adds CheckIn 
							<input type="submit" value = "show ${i.value.type}" id = "btn"  sytle="margin-left:50px;"/>
							</label> 
							
							</c:if>
							
							</c:if>
							
							<c:if test="${i.value.type == 'follow'}">
							
							<label style="margin-left:200px;">${i.value.name} Follows you</label>
							
							</c:if>				
						</td>
					</tr>
				</table>
			<c:set var="j"  value="${j+1}"/>
			</form>
	
		</c:forEach> 
		<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>
	
</body>
</html>
