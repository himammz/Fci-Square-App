<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/FCISquareApp/stylesheets/home.css">
<link rel="shortcut icon" type="image/x-icon"
	href="/FCISquareApp/images/mobile-icon-md.ico" />
<link rel="stylesheet" href="/FCISquareApp/stylesheets/myCheckIn.css">

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Show Check-in</title>
</head>



<body>
	<header> <span class="icon-bar"><img
		src="/FCISquareApp/images/mobile-icon-md.png" /></span> FCI<span>SQUARE</span>
	</header>
	<div id="cssmenu">
		<ul>
			<li><button type="button" name="BarControl"
					onclick="AppearBar();"></button></li>
			<li class='active'><a href='homepage'
				style="text-decoration: none">Profile</a></li>
			<li><a href='getNotification' style="text-decoration: none">
					Get Notification</a></li>
			<li><a href='ActiveLog' style="text-decoration: none">Active
					Log</a></li>
			<li style="margin-left: 50px"><a href='logout'
				style="text-decoration: none">Logout :D </a></li>

		</ul>
	</div>

	<div id="cssbar">

		<li styel="padding-left:50px"><a href='followingList'
			style="text-decoration: none">Followers</a></li>
		<li><a href='FollowPerson' style="text-decoration: none">Follow
		</a></li>
		<li><a href='UnFollowPerson' style="text-decoration: none">UnFollow
		</a></li>
		<li><a href='Checkin' style="text-decoration: none">CheckIn</a></li>
		<li><a href='/FCISquareApp/app/showLocation'
			style="text-decoration: none">Show Location</a></li>
		<li><a href='' style="text-decoration: none">Update Location</a></li>
		<li><a href='addPlace' style="text-decoration: none">Add
				Place</a></li>
		<li><a href='savePlace' style="text-decoration: none">Save
				Place</a></li>
		<li><a href='MySavedPlaces' style="text-decoration: none">Saved
				Places</a></li>
		<li><a href='showHome' style="text-decoration: none">Show
				Home</a></li>
		<li><a href='showHomeByRate' style="text-decoration: none">Show
				HomeRate</a></li>
		<li><a href='showHomeByCount' style="text-decoration: none">Show
				HomeCount</a></li>
		<li><a href='showHomeByTime' style="text-decoration: none">Show
				HomeTime</a></li>
		<li><a href='showHomeByPlace' style="text-decoration: none">Show
				HomePlace</a></li>

	</div>
	<div class="myCheckIn">


		<div id="checkin">
			<form method="" action="">
				<c:forEach var="i" items="${it}">
					<input type="hidden" value="${i.value.checkinid }" name="checkin">
				</c:forEach>

				<c:forEach var="i" items="${it}">
					<input type="hidden" value="${i.value.uid }" name="uid">
				</c:forEach>

				<c:forEach var="i" items="${it}">
					<h3>${i.value.name}</h3>
				</c:forEach>

				<c:forEach var="i" items="${it}">
					<p>${i.value.desc}</p>
				</c:forEach>
				<hr>
				<c:forEach var="i" items="${it}">
					<input type="hidden" id="it" value="${i.value.Likes}">
					<p id="a">${i.value.Likes}Likes</p>
				</c:forEach>


				<c:forEach var="i" items="${it}">

					<c:if test="${ i.value.Like==1 }">
						<input type="submit" name="like" id="like" value="Like"
							onclick="check(this.form)" class="button">
					</c:if>
					<c:if test="${ i.value.Like==0 }">
						<input type="submit" name="like" id="like" value="Unlike"
							onclick="check(this.form)" class="button">

					</c:if>

				</c:forEach>
		</form>
		</div>
	
		<hr>

		<c:forEach var="i" items="${it}">

			<c:forEach var="j" begin="0" end="${i.value.Comment.size()}" step="2">
				<c:if test="${ j < i.value.Comment.size() }">
					<p>${i.value.Comment[j+1]}comment:${i.value.Comment[j]}</p>

				</c:if>

				</br>
			</c:forEach>
		</c:forEach>


		<p>add new comment</p>
		<c:forEach var="i" items="${it}">


			<form action="newComment" method="POST" id="commentForm">

				<input type="text" name="comment" id="commentField"> <input
					type="text" name="checkinID" value="${i.value.checkinid}" hidden>
				<input type="text" name="uid" value="${i.value.uid}" hidden>
				<input type="submit" value="Comment" id="commentButton">
			</form>
		</c:forEach>

	</div>
	<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>
	<script type="text/javascript" src="/FCISquareApp/js/like.js"></script>


</body>
</html>