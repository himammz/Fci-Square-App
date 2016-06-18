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
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="/FCISquareApp/stylesheets/activelog.css" rel="stylesheet">
<link href="/FCISquareApp/stylesheets/home.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/home.css">

<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/header.css">
<link rel="shortcut icon" type="image/x-icon" href="/FCISquareApp/images/mobile-icon-md.ico" />
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Active Log</title>
</head>
<body>

	<header>
		<span class="icon-bar"><img src = "/FCISquareApp/images/mobile-icon-md.png"/></span>
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
	
	
	<c:forEach var="i" begin="0" end = "${it.size()}">
			
			<c:if test="${ i < it.size() }">
		
			 <ul>
			 	<c:if test="${i%2== 0 }">
			 	 <li>
			 	 	<div class = "testbox">
						<h1></h1>
						<form action = "undoService" method = "POST">
							<c:if test= "${it[i].Type eq 'Check-in'}">
					 	 	<p> You checked-in ${it[i].placeName} </p>
					 	 </c:if>
					 	 <c:if test="${it[i].Type eq 'Comment'}">
					 	 	<p> You commented on ${it[i].UserName}'s check-in ${it[i].placeName}  </p>
					 	 </c:if>
					 	 <c:if test="${it[i].Type eq 'Like'}">
					 	 	<p> You Liked ${it[i].UserName}'s check-in ${it[i].placeName}  </p>
					 	 </c:if>
				
					 	 <form method="POST" action ="undoService">
							<input type="hidden" name="Type" value="${it[i].Type}">
							<input type="hidden" name="IDType" value="${it[i].IDType}">
		 			 	  	<input id = "submit" type="submit" value = "Undo">    
		 			 	  </form>
							
						</form>
					</div>
	 			 	  
 			 	  </li>
 			 	  </c:if>
 			 	  
<!--  			 	  //// -->
				<c:if test="${i%2 != 0 }">
			 	 <li>
			 	 	<div class = "testboxRight">
						<h1></h1>
						<form action = "undoService" method = "POST">
							<c:if test= "${it[i].Type eq 'Check-in'}">
					 	 	<p> You checked-in ${it[i].placeName} </p>
					 	 </c:if>
					 	 <c:if test="${it[i].Type eq 'Comment'}">
					 	 	<p> You commented on ${it[i].UserName}'s check-in ${it[i].placeName}  </p>
					 	 </c:if>
					 	 <c:if test="${it[i].Type eq 'Like'}">
					 	 	<p> You Liked ${it[i].UserName}'s check-in ${it[i].placeName}  </p>
					 	 </c:if>
				
					 	 <form method="POST" action ="undoService">
							<input type="hidden" name="Type" value="${it[i].Type}">
							<input type="hidden" name="IDType" value="${it[i].IDType}">
		 			 	  	<input id = "submit" type="submit" value = "Undo">    
		 			 	  </form>
							
						</form>
					</div>
	 			 	  
 			 	  </li>
 			 	  </c:if>
 			 	  
			  </ul>
			  
			  
			</c:if>
			
			</c:forEach>
	
	<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>
	
</body>
</html>