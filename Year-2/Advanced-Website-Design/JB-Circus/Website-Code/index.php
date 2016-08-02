<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  
  <head>
    <title>JB Circus</title>
    <meta name="keywords" content="Circus, gymnastics, acrobatics, contortion, stilts, jugglying, tumbling, trampoline, trapeze">
    <link rel="stylesheet" href="css.css" type="text/css" media="screen" />
  </head>
  
  <body>
  <?php
		if (isset($_GET['sent']) === true){
		
		} else {
			if (empty($errors) === false) {
					echo '<ul>';
					foreach($errors as $error) {
						echo '<li>', $error, '<li>';
					}
					echo '</ul>';
				}
				?>
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
      
<div id="content">
 
    

<form action="post.php" method="post" align="center">
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
		
</div>  
      	
          <p>&nbsp;</p>
   </body>
  

</html>