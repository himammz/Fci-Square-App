
function doChange(elem,Form){
//	alert(elem);
	if(elem.value == "SAVE"){
		myFunction(elem,Form);
		elem.value = "UNSAVE";
	}else{
		//alert(elem.value);
		myFunction(elem,Form);
		elem.value = "SAVE";
	}
}

function myFunction(elem,Form){
	var pathname = window.location.pathname; // Returns path only
	var url      = window.location.href;     // Returns full URL

	if(elem.value == "SAVE"){

		Form.action="followPlace";
		console.log(1);
	}else{
		console.log(2);

		Form.action="unfollowPlace";
	}
	
}

function unsave(elem,Form){
	Form.action="unfollowPlace";
    elem.style.backgroundImage ="url('/FCISquareApp/images/mshsa7.jpg')";
	//elem.src="/FCISquareApp/images/mshsa7.jpg";
}

function save(elem,Form){
	//alert(elem);
	Form.action="followPlace";
	 elem.style.backgroundImage="url('/FCISquareApp/images/sa7.jpg')";
//    elem.style.width='50px';
//    elem.style.height='52px';
//    elem.style.body='none';
//    elem.style.color='transparent';
//    elem.style.marginLeft='10px';

	//elem.src="/FCISquareApp/images/sa7.jpg";
}

