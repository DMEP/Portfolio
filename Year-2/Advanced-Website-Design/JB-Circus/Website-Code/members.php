<?php

session start();
if($_SESSION['username'] || $_SESSION['password'])
{	
	$username = $_SESSION['username'];
	echo "You are logged in as " .$_SESSION['username'];
	echo "<br><br><a href='logout.php'>LogOut</a>";
	echo "<title></title>";
}
else
{
	header("header:LoginForm.php?notify=Oops! Something went wrong.");
}

?>