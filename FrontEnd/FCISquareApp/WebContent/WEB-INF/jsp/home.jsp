<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/home.css">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/FCISquareApp/images/mobile-icon-md.ico" />

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Home</title>
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
		   	<li><input type="submit" value="Follow" onclick="showFollow()"/></li>
		   	<li><input type="submit" value="Unfollow" onclick="showUnFollow()"/></li>
		   	<li  style="margin-left:50px"><a href='logout' style="text-decoration:none">Logout :D </a></li>
		   	 	
	   </ul>
	</div>
	
	<div id="cssfollow" hidden>
	<form action="submitFollow" method="POST" id="follow">
	
	<label>User Name:<input type="text" value=""name="userName"/></label>
	
	<input name="follow" type="submit" value = "Follow" id="subbtn" />
	
<!-- 	<input name="follow" type="submit" value = "UnFollow" id="subbtn" onclick="check()"/> -->
	</form>
	</div>
	
	<div id="cssunfollow" hidden>
	<form action="submitUnFollow" method="POST" id="follow">
	
	<label>User Name:<input type="text" value=""name="userName"/></label>
	
<!-- 	<input name="follow" type="submit" value = "Follow" id="subbtn" onclick="check()"/> -->
	
	<input name="follow" type="submit" value = "UnFollow" id="subbtn" />
	</form>
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
	
	<div class="testbox2">

  <h1>Profile</h1>

   
  <hr>
  <label id="icon1" for="name"><i class="icon-user">   </i>Name</label>
  <input type="text" name="Name" id="name" value="${it.name}"readonly="readonly"/>
  </br>
  <label id="icon1" for="name"><i class="icon-user">  </i>User Name</label>
  <input type="text" name="lat" id="lat"value="${it.userName}"readonly="readonly"/>
  </br> 
  <label id="icon1" for="name"><i class="icon-envelope ">  </i>Email</label>
  <input type="text" name="long" id="long" value="${it.email}"readonly="readonly"/>
  </br>
  <label id="icon1" for="name"><i class="icon-shield">   </i>Age</label>
 <input type="text" name="desc" id="desc"value="${it.age}"readonly="readonly"/>
 </div>
	
	
	<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>
	
	</body>
</html>
