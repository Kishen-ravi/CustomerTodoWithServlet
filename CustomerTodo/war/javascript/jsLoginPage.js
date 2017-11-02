var alertpop = document.getElementById("alert");
alertpop.style.visibility = "hidden";
$('.signup_form').hide();
$('.login_form').show();

$(document)
		.ready(
				function() {
					
					// $('button').click(function(){
					// $('.box1, .box2').toggle();
					// });
					// $('.form').hide();
					
					//$('.customer_form').hide();
					
					

					$('.signup')
							.click(
									function() {

										document.getElementById("login_email").value = "";
										document
												.getElementById("login_email_content").innerHTML = "";
										document
												.getElementById("login_password_content").innerHTML = "";
										$('.login_form').hide();
										$('.signup_form').show();
									});

					$('.login')
							.click(
									function() {
										document
												.getElementById("signup_username_content").innerHTML = "";
										document
												.getElementById("signup_email_content").innerHTML = "";
										document
												.getElementById("signup_password_content").innerHTML = "";
										document
												.getElementById("signup_retype_password_content").innerHTML = "";
										$('.signup_form').hide();
										$('.login_form').show();
									});

					$('.addContact')
							.click(
									function() {
										$('.customer_form').show();
										$('#customer_username').val("");
										document
												.getElementById("delete_customer").style.visibility = 'hidden';
										document
												.getElementById("update_customer").style.visibility = 'hidden';
										document
												.getElementById("create_customer").style.visibility = 'visible';
										document
												.getElementById("customer_email").value = "";
										document
												.getElementById("customer_phno").value = "";
										document
												.getElementById("customer_address").value = "";
									});

					// $('button2').click(function(){
					// 	$('.box2').toggle();
					// });

				});


function alertPopUp(content) {
	alertpop.innerText = content;
	console.log("in alertpop");
	alertpop.style.visibility = "visible";
	setTimeout(function() {
		alertpop.style.visibility = "hidden";
	}, 2000);

}