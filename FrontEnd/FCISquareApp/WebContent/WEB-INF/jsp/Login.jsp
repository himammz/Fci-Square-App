<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <<<<<<< HEAD -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="/FCISquareApp/stylesheets/login.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/FCISquareApp/stylesheets/header.css">
<link rel="shortcut icon" type="image/x-icon" href="/FCISquareApp/images/mobile-icon-md.ico" />
<header>
		<span class="icon-bar"><img src = "/FCISquareApp/images/mobile-icon-md.png"/></span>
		FCI<span>SQUARE</span>
	</header>
	<div id="cssmenu">
		<ul>
		   	<li ><a href='' style="text-decoration:none">Welcome</a></li>
		   	<li class='active'> <a href='#' style="text-decoration:none">Register</a></li>
	   </ul>
	   <img src="/FCISquareApp/images/login icon.png" />
	   <a href = "/FCISquareApp/app/login">Login</a> 
	</div>
<!-- 	<div id="INheader"> -->
<!-- 		<img src="/FCISquareApp/images/login icon.png" /> -->
<!-- 			<a href = "/FCISquareApp/app/login">Login</a>  -->

<!-- 	</div > -->

<div id="header">

<div class="testbox">
  <h1>Login</h1>

  <form action = "doLogin" method = "POST">
   
  <hr>
    

  <label id="icon" for="name"><i class="icon-envelope "> <img src="/FCISquareApp/images/icon-envelope.png" class ="imgs"> </i></label>
  <input type="text" name="email" id="name" placeholder="Email" required/>
  
  <br>
 
  <br>
  
  
  <label id="icon" for="name"><i class="icon-shield">  <img src="/FCISquareApp/images/icon-shield.png" class ="imgs" > </i></label>
 <input type="password" name="pass" id="name" placeholder="Password" required/>
  
  <br>
 
  <br>
   <p>By clicking Register, you agree on our <a href="#">terms and condition</a>.</p>
   <a href="doLogin" class="button"><input type="submit" value = "Login" id="subbtn" /></a>
  </form>
</div>






















