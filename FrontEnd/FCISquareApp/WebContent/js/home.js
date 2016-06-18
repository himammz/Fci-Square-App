var setVisForBar=0;
function AppearBar(){
	
	var bar = document.getElementById("cssbar");
    if(setVisForBar%2==0){
    	bar.style.visibility='visible';
    	//bar.style.transitionDuration="1.5s";
    }
    else{
    	bar.style.visibility='hidden';
       // bar.style.transitionDuration="1.5s";
    }
    setVisForBar++;
    console.log(setVisForBar);
}
var followvar=0;
var unfollowvar=0;
function showFollow()
{	
	
	if(followvar%2==0){
		document.getElementById("cssfollow").style.display='block';
		followvar++;
	}
	else
	{
		document.getElementById("cssfollow").style.display='none';
		followvar++;
	}
	console.log(followvar);
}
function showUnFollow()
{	
	if(unfollowvar%2==0){
		document.getElementById("cssunfollow").style.display='block';
		unfollowvar++;
	}
	else
	{
		document.getElementById("cssunfollow").style.display='none';
		unfollowvar++;
	}
}
function check()
{alert(Request.Form("follow"));
	if(Request.Form("follow")=="Follow")
		document.getElementById("follow").action="submitFollow";
	else
		document.getElementById("follow").action="submitUnFollow";
}