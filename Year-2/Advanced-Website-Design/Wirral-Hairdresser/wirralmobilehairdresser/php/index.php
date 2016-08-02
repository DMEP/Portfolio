<!DOCTYPE html>
<html>
	<head>
		<title>Contacts</title>
		<link rel="stylesheet" href="css.css" type="text/css">
		
	</head>
	<body>
		<?php
		if (isset($_GET['sent']) === true){
			echo'<p>Thanks for contacting us!</p>';
		} else {
			if (empty($errors) === false) {
					echo '<ul>';
					foreach($errors as $error) {
						echo '<li>', $error, '<li>';
					}
					echo '</ul>';
				}
				?>
				
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
<h2>Contacts</h2></div>
<div id="subheader" class="white">
<h3>Contact for an appointment </h3></div><br>
<div id="break1"></div>
<div id="text">

<form action="#" method="POST" class="signin">
<p><input type="text" id="username" name="username" autocomplete="on" placeholder="Username"></p>
<p><input type="password" id="password" name="password" placeholder="Password"></p>
<p><input type="submit" name="login" value="Log In"></p>
</form>

<a href="register.php">Register</a>

<?php
};
?>
</div>
<div id="break2"></div>
</div>
</body>
</html>

        
                
                
                