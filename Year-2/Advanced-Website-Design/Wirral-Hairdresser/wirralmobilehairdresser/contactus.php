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

<form action="php/post.php" method="post" align="center">
<p>
<label for="name">Name:</label><br>
<input type="text" name="name" id="name" <?php if(isset($_POST['name']) === true) { echo 'value=', strip_tags($_POST['name']), '"';} ?> >
</p>
<p>
<label for="email">Your Email:</label><br>
<input type="text" name="email" id="email" <?php if(isset($_POST['email']) === true) { echo 'value=', strip_tags($_POST['email']), '"';} ?> >
</p>
<p>
<label for="message">Your Message:</label><br>
<textarea name="message" id="message"> <?php if(isset($_POST['message']) === true) { echo strip_tags($_POST['message']);} ?> </textarea>
</p>
<p>
<input type="submit" value="submit">
</p>
</form>
<?php
};
?>
</div>
<div id="break2"></div>
</div>
</body>
</html>

