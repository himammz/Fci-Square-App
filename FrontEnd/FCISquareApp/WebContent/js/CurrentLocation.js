
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
	 function initMap() {
		 alert("7amada");
	        map = new google.maps.Map(document.getElementById('map'), {
	          center: {lat: -34.397, lng: 150.644},
	          zoom: 8
	        });
	      }