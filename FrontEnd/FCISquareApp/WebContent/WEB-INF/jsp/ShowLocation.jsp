<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/home.css">
<link rel="shortcut icon" type="image/x-icon" href="/FCISquareApp/images/mobile-icon-md.ico" />
<title>FCISquare Application</title>
<script src="http://maps.googleapis.com/maps/api/js">
	
</script>
<script>
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, showError);
		} else {

		}
	}

	function showPosition(position) {
		document.getElementById('lat').value = position.coords.latitude;
		document.getElementById('long').value = position.coords.longitude;
		var myLatLng = {
			lat : position.coords.latitude,
			lng : position.coords.longitude
		};
		var map = {
			center : myLatLng,
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("googleMap"), map);
		var marker = new google.maps.Marker({
			position : myLatLng,
			map : map,
			title : 'You are here, ta2reban ensh2allah ya3ny :D :D '
		});

	}

	function showError(error) {
		switch (error.code) {
		case error.PERMISSION_DENIED:
			x.innerHTML = "User denied the request for Geolocation."
			break;
		case error.POSITION_UNAVAILABLE:
			x.innerHTML = "Location information is unavailable."
			break;
		case error.TIMEOUT:
			x.innerHTML = "The request to get user location timed out."
			break;
		case error.UNKNOWN_ERROR:
			x.innerHTML = "An unknown error occurred."
			break;
		}
	}
	google.maps.event.addDomListener(window, 'load', getLocation);
</script>
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
	<div id="googleMap" style="width: 500px; height: 380px;"></div>
	<br>
	<form action="updateMyLocation" method="post">

		<input id="lat" type="hidden" name="lat" /> <input id="long"
			type="hidden" name="long" /> <input type="submit"
			value="update my current position" />
	</form>
	<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>
	
</body>
</html>