<?php


session_start();
if($_SESSION['username'] || $_SESSION['password'])
{
	session_destroy();
	header("location:LoginForm.php?notify=You are Logged Out")
}


?>