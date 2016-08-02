<?php $name = $_POST['name'];
	$email = $_POST['email'];
	$message = $_POST['message'];
	$formcont="From: $name \n Message: $message";
	$recipient = "danelstob@yahoo.com";
	$subject = "Contact Form";
	$mailhead = "From: $email \r\n";
	mail($recipient, $subject, $formcont, $mailhead) or die("Error!");
echo "Thanks! We have received your message and will get back to you as soon as possible...";

 echo '<meta HTTP-EQUIV="REFRESH" content="5; url=http://www.bitzone.co.uk/dan/loginform.php">';


?>