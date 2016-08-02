<?php
session_start();
//if(isset($_POST['login']))
//{
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	if($username && $password)
	{
		mysql_connect("127.0.0.1","admin13280","kusinagi1");
		mysql_select_db("bitzone_co_uk_circ");
		$login=mysql_query("SELECT * FROM users WHERE username = '$username' && password = '$password' LIMIT 1");
		$rowCount=mysql_num_rows($login);

// Counts the amount of results matched by $login 
			if($rowCount==1){
			
			//session_register("$username");
			$_SESSION['username'] = $username;
			
			echo 'logged in';
   			header( 'Location: index.php' ) ;
			


//		}
		}	else{ 
				session_destroy();
				echo 'something went wrong, please return to login.';	
				
			}
}

?>