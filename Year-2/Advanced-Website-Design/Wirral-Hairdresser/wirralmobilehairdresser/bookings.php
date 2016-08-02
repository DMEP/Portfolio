<?php
if (empty($_POST) === false){
	$errors = array();
	
	$name		= $_POST['name'];
	$email		= $_POST['email'];
	$message	= $_POST['message'];

	if (empty($name) === true || empty($email) === true || empty($message) === true) {
		$errors[] = 'Name, email and message are required!';
	} else {
		if (filter_var($email, FILTER_VALIDATE_EMAIL) === false) {
			$errors[] = 'That\'s not a valid email address!';
		}
		if (ctype_alpha($name) === false) {
			$errors[] = 'Name must only contain letters!';
		}
	}
	
	if (empty($errors) === true) {
		mail('danelstob@yahoo.com', 'Contact form', $message, 'From: ' . $email);
		header('Location: index.php?sent');
		exit();
	//redirect mail
	}
}
?>

<!DOCTYPE html>
<html>
	<head>
		<title>Bookings</title>
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
<h2>Booking</h1></div>
<div id="subheader" class="white">
<h3>Book an appointment</h3></div><br>
<div id="break1"></div>
<div id="text">

<form action="" method="post" align="center">
<p>
<label for="email">First Name:</label><br>
<input type="text" name="email" id="email" <?php if(isset($_POST['email']) === true) { echo 'value=', strip_tags($_POST['email']), '"';} ?> >
</p>
<p>
<label for="name">Last Name:</label><br>
<input type="text" name="name" id="name" <?php if(isset($_POST['name']) === true) { echo 'value=', strip_tags($_POST['name']), '"';} ?> >
</p>
<p>
<label for="email">Your Email:</label><br>
<input type="text" name="email" id="email" <?php if(isset($_POST['email']) === true) { echo 'value=', strip_tags($_POST['email']), '"';} ?> >
</p>
<p>
<label for="message">Mobile Number:</label><br>
<textarea name="message" id="message"> <?php if(isset($_POST['message']) === true) { echo strip_tags($_POST['message']);} ?> </textarea>
</p>
<p>
<p>
<label for="email">Day of Booking:</label><br>
<select name="email" id="email" <?php if(isset($_POST['email']) === true) { echo 'value=', strip_tags($_POST['email']), '"';} ?> >
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	<option value="13">13</option>
	<option value="14">14</option>
	<option value="15">15</option>
	<option value="16">16</option>
	<option value="17">17</option>
	<option value="18">18</option>
	<option value="19">19</option>
	<option value="20">20</option>
	<option value="21">21</option>
	<option value="22">22</option>
	<option value="23">23</option>
	<option value="24">24</option>
	<option value="25">25</option>
	<option value="26">26</option>
	<option value="27">27</option>
	<option value="28">28</option>
	<option value="29">29</option>
	<option value="30">30</option>
	<option value="31">31</option>
	</select>
	<label for="email">Month of Booking:</label><br>
<select name="email" id="email" <?php if(isset($_POST['email']) === true) { echo 'value=', strip_tags($_POST['email']), '"';} ?> >
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	</select>
	
</p>

<p>
<label for="email">Type of Service:</label><br>
<select name="email" id="email" <?php if(isset($_POST['email']) === true) { echo 'value=', strip_tags($_POST['email']), '"';} ?> >
</select>
</p>
<p>
<label for="email">Number of people:</label><br>
<input type="text" name="email" id="email" <?php if(isset($_POST['email']) === true) { echo 'value=', strip_tags($_POST['email']), '"';} ?> >
</p>
<input type="submit" value="Submit">
</p>
</form>
<?php
};
?>
</div>
<br><br>
<div id="break2"></div>
</div>
</body>
</html>

