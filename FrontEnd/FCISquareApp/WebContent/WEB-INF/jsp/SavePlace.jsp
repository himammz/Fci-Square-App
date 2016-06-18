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
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Save Place</title>


<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/savePlace.css"> 
<!-- <link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/style.css"> -->
</head>
<body>
	

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
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td> -->
				
<!-- 				</td> -->
		<div id = "Contain">
			
		<c:forEach  var="i" items="${it}">
			
			<form id="sform" action = "followPlace" method = "POST">
<!-- 				<table class="Place"> -->
<!-- 						<tr> -->
						
<!-- 							<td class="Place"> -->
					<div class="Place">
					 			<input type="hidden" name="id" value="${i.key}" >
					 			<p >
					 			${i.value.name}
					 			</p>
					 			<c:choose>
									    <c:when test="${i.value.isSave == '1'}">
										<!-- gded -->
									   		<input type="submit" value = "" id = "checked" onclick="unsave(this,this.form);"/>
<!-- 									        <input type="submit" value = "UNSAVE" id = "btn" onclick="doChange(this,this.form);"/> -->
									        <br />
									    </c:when>    
									    <c:otherwise>
									    	<input type="submit" value = "" id = "unchecked" onclick="save(this,this.form);"/>
<!-- 									        <input type="submit" value = "SAVE" id = "btn" onclick="doChange(this,this.form);"/>  -->
									        <br />
									    </c:otherwise>
									</c:choose>
<!-- 					 		</td> -->
					</div>
			</form>
	
		</c:forEach>
		</div>
	<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>
<script type="text/javascript" src="/FCISquareApp/js/savePlace.js"></script>
	<br/>
	<br/>
</body>
</html>
