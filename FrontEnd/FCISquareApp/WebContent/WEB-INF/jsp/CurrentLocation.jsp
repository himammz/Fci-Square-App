<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/home.css">
<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/header.css">
<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/currentLocation.css">
<link rel="shortcut icon" type="image/x-icon" href="/FCISquareApp/images/mobile-icon-md.ico" />
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>CheckIn</title>

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
		var LatLng = {	lat : position.coords.latitude, lng : position.coords.longitude };
		
		 var geocoder = new google.maps.Geocoder();

	        var latlng = new google.maps.LatLng(LatLng.lat,LatLng.lng);
	        geocoder.geocode({'latLng': latlng}, function(results, status) {
	            if(status == google.maps.GeocoderStatus.OK) {
	                document.getElementById('Name').value=results[1].formatted_address;
	                document.getElementById('current').value=document.getElementById('Name').value;
	            };
	        });
		var map = {
			center : LatLng,
			zoom : 18,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("map"), map);
		
		 new google.maps.Marker({
			position : LatLng,
			map : map,
		});
	}
	google.maps.event.addDomListener(window, 'load', getLocation);
	
	function updatePosition() {
	   var address = document.getElementById('current').value;
	    geocoder = new google.maps.Geocoder();
	    if (geocoder) {
	        geocoder.geocode({
	            'address': address
	        }, function (results, status) {
	            if (status == google.maps.GeocoderStatus.OK) {
	            	var LatLng = {lat : results[0].geometry.location.lat(),lng : results[0].geometry.location.lng()};
	            	 var map = {
	     	    			center : LatLng,
	     	    			zoom : 18,
	     	    			mapTypeId : google.maps.MapTypeId.ROADMAP
	     	    		};
	     	    		var map = new google.maps.Map(document.getElementById("map"), map);
	     	    		
	     	    		 new google.maps.Marker({
	     	    			position : LatLng,
	     	    			map : map,
	     	    		});
	            }
	            else
	            	alert("Wrong Address");
	        });
	    }
	}
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

	<div id="map" style="margin-left:200px;width:915px; height: 420px; border: 4px solid #fd8a17; margin-top:20px;"></div>
	
	<div class="testbox">

  <h1>CheckIn</h1>

<form action="addCheck" method="POST">
   <input id="lat" type="hidden" name="lat" value="0"/>
   <input id="long" type="hidden" name="long" value="0" />
   <input id="Name" type="hidden" name="Name" />
  <hr>
  <label id="icon" for="name"><i class="icon-user">   </i></label>
  <input id="current" type="text" name="Current" size=20 id="name" placeholder="Current Place" required>
  </br>
  </br>
  </br>
  <label id="icon" for="name"><i class="icon-user">  </i></label>
  <input type="text" name="rate" id="long" placeholder="Rate for place" required/>
  </br>
  </br>
  </br>
    <a href="" class="button"><input type="submit" value = "CheckIn" id="subbtn" /></a>
  </form>
  <input type="submit" value ="Update" onclick="updatePosition()" id="subbtnUpdate"/>
<!--   <button onclick="updatePosition()"  class="button">updatePosition</button> -->
	
 </div>
<script type="text/javascript" src="/FCISquareApp/js/home.js"></script>

 
</body>
</html>
	
