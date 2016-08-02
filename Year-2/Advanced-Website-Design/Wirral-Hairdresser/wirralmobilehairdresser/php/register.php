<!DOCTYPE html>
<html>
	<head>
		<title>Contacts</title>
		<link rel="stylesheet" href="css.css" type="text/css">
		
	</head>
	<body>
						
<!-- Left side of website-->
<div id="menu">

<img src="images/Logo.jpg" style="width:500px; height:100px" >

<div id="navigation">

<ul>
<li class="tabs"><a class="a" href="index.html">Home</a></li>
<li class="tabs"><a class="a" href="about.html">About</a></li>
<li class="tabs"><a class="a" href="weddings.html">Weddings</a></li>
<li class="tabs"><a class="a" href="proms.html">Proms</a></li>
<li class="tabs"><a class="a" href="children.html">Children</a></li>
<li class="tabs"><a class="a" href="pricelist.html">Price List</a></li>
<li class="tabs"><a class="a" href="contactus.php">Contacts</a></li>
<li class="tabs"><a class="a" href="php/index.php">Log in</a></li>
</ul>



</div>

</div>

<!-- Right side of website-->

<div id="content" align="right">
<div id="header2" class="white">
<h2>Register</h2></div>
<div id="subheader" class="white">
<h3>Register an account </h3></div><br>
<div id="break1"></div>
<div id="text">

<form action="register.php" method="POST">
<p><input type="text" name="username" placeholder="Username"></p>
<p><input type="email" name="email" placeholder="Email"></p>
<p><input type="password" name="pass" placeholder="Password"></p>
<p><input type="password" name="pass1" placeholder="Password"></p>
<p><input type="submit" name="submit" value="Register"></p>
</form>

</html>
<?php

if(isset($_POST['submit']))
{
	$username = mysql_real_escape_string($_POST['username']);
	$pass = mysql_real_escape_string($_POST['pass']);
	$pass1 = mysql_real_escape_string($_POST['pass1']);
	$email = mysql_real_escape_string($_POST['email']);
	if($username && $pass && $pass1 && $email)
	{
		if($pass==$pass1)
		{
			$connect = mysql_connect("127.0.0.1","root","");
			mysql_select_db("bitzone_co_uk_usersd");
			$query = mysql_query("INSERT INTO users VALUES('$username','$pass','$email');");
			echo "You have been registered.";
		}
	}
	else
	{
		echo "All fields are required.";
	}
}
?>