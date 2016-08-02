<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  
  <head>
    <title>JB Circus</title>
    <meta name="keywords" content="Circus, gymnastics, acrobatics, contortion, stilts, jugglying, tumbling, trampoline, trapeze">
    <link rel="stylesheet" href="css.css" type="text/css" media="screen" />
	
  </head>
  
  <body>
    <div id="wrapper">
	<p>&nbsp;</p>
	<div id="header">
		<div id="banner">
			<img src="images/logo.png" width="407" height="210" alt="JBCircus Logo">
		</div>
    </div>
	
<div id="navigation">
    
 		<a href="index.html">Home</a>
		<a href="about.html">About Us</a>
		<a href="acts.html">Acts</a>
		<a href="date.html">Dates and Venues</a>
        <a href="loginform.php">Log in / Register</a>
	
</div>
		
<div id="content2">

<h1>Register</h1>

<form action="register.php" method="POST">
<p><input type="text" name="username" placeholder="Username"></p>
<p><input type="email" name="email" placeholder="Email"></p>
<p><input type="password" name="pass" placeholder="Password"></p>
<p><input type="password" name="pass1" placeholder="Password"></p>
<p><input type="submit" name="submit" value="Register"></p>
<a href="loginform.php">Back to Log in</a>
</form>

<?php

if(isset($_POST['submit']))
{
	$username = mysql_real_escape_string($_POST['username']);
	$pass = mysql_real_escape_string($_POST['pass']);
	$pass1 = mysql_real_escape_string($_POST['pass1']);
	$email = mysql_real_escape_string($_POST['email']);
	if($username && $pass && $pass1 && $email)
	{
		($pass==$pass1)
		{
			$connect = mysql_connect("127.0.0.1","admin13280","kusinagi1")or die('could not connect');
			mysql_select_db("bitzone_co_uk_circ")or die('could not select DB');
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
  
</div>
</div>
</body>
</html>