<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="/FCISquareApp/stylesheets/Registeration.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/header.css">

<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/home.css">

<link rel="shortcut icon" type="image/x-icon" href="/FCISquareApp/images/mobile-icon-md.ico" />

<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/home.css">
<header>
		<span class="icon-bar"><img src = "/FCISquareApp/images/mobile-icon-md.png"/></span>
		FCI<span>SQUARE</span>
	<title>Add Place</title>
	<script src="http://maps.googleapis.com/maps/api/js">
	
 </script> 

<script>
 function getLocation() {
 	if (navigator.geolocation) 
 		navigator.geolocation.getCurrentPosition(getPosition);
 }

 function getPosition(position) {

 		document.getElementById('lat').value = position.coords.latitude;
 		document.getElementById('long').value = position.coords.longitude;
 }	
</script> 
 	</header> 

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
	
	
	
<div class="testbox">

  <h1>Add Place</h1>

<form action="newPlace" method="POST">
   
  <hr>
  <label id="icon" for="name"><i class="icon-user">   </i></label>
  <input type="text" name="Name" id="name" placeholder="Name" required/>
  
  <label id="icon" for="name"><i class="icon-user">  </i></label>
  <input type="text" name="lat" id="lat" placeholder="Latitude" required/>
   
  <label id="icon" for="name"><i class="icon-envelope ">  </i></label>
  <input type="text" name="long" id="long" placeholder="Longitude" required/>
  
  <label id="icon" for="name"><i class="icon-shield">   </i></label>
 <input type="text" name="desc" id="desc" placeholder="Description" required/>
   <a href="" class="button"><input type="submit" value = "submit" id="subbtn" /></a>
  </form>
  </br>
	<input type="checkbox" id="che"onclick="getLocation();"style="margin-bottom:20px; margin-left:50px;float:left;" /><label >get current Position</label>
 </div>
<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>
	
</body>
</html>