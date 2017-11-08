var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;



function validatelogin() {
	var login_email = document.getElementById("login_email").value;
	var login_password = document.getElementById("login_password").value;

	if (login_email == "") {
		document.getElementById("login_email_content").innerHTML = "Please enter user name";
		document.getElementById("login_email_content").style.color = "darkolivegreen";
		document.getElementById("login_email").focus();
		return false;
	} else if (!(login_email.match(mailformat))) {
		$('#login_email').val('');
		document.getElementById("login_email_content").innerHTML = "Please enter valid email";
		document.getElementById("login_email_content").style.color = "darkolivegreen";
		document.getElementById("login_email").focus();
		return false;
	}

	else if (login_password == "") {
		document.getElementById("login_password_content").innerHTML = "Please enter password";
		document.getElementById("login_password_content").style.color = "darkolivegreen";
		document.getElementById("login_password").focus();
		return false;
	}

}

function checkKey() {
	var key = event.keyCode;
	if (key !== 8 && key !== 32) {
		document.getElementById("login_email_content").innerHTML = "";
		document.getElementById("login_password_content").innerHTML = "";
		document.getElementById("signup_username_content").innerHTML = "";
		document.getElementById("signup_email_content").innerHTML = "";
		document.getElementById("signup_password_content").innerHTML = "";
		document.getElementById("signup_retype_password_content").innerHTML = "";

	}
}

function validatesignup() {
	var signup_username = document.getElementById("signup_username").value;
	var signup_email = document.getElementById("signup_email").value;
	var signup_password = document.getElementById("signup_password").value;
	var signup_retype_password = document
			.getElementById("signup_retype_password").value;

	if (signup_username == "") {
		document.getElementById("signup_username_content").innerHTML = "Please enter user name";
		document.getElementById("signup_username_content").style.color = "darkolivegreen";
		document.getElementById("signup_username").focus();
		return false;
	} else if (signup_email == "") {
		document.getElementById("signup_email_content").innerHTML = "Please enter email";
		document.getElementById("signup_email_content").style.color = "darkolivegreen";
		document.getElementById("signup_email").focus();
		return false;
	} else if (!(signup_email.match(mailformat))) {
		$('#signup_email').val('');
		document.getElementById("signup_email_content").innerHTML = "Please enter valid email";
		document.getElementById("signup_email_content").style.color = "darkolivegreen";
		document.getElementById("signup_email").focus();
		return false;
	} else if (signup_password == "") {
		document.getElementById("signup_password_content").innerHTML = "Please enter password";
		document.getElementById("signup_password_content").style.color = "darkolivegreen";
		document.getElementById("signup_password").focus();
		return false;
	} else if (signup_retype_password != "" && signup_password == "") {
		document.getElementById("signup_password_content").innerHTML = "";
		document.getElementById("signup_retype_password_content").innerHTML = "";
		document.getElementById("signup_retype_password_content").innerHTML = "Password doest match";
		$('#signup_password').val('');
		$('#signup_retype_password').val('');
		document.getElementById("signup_password").focus();
		return false;
	}

	else {
		if (signup_password != signup_retype_password) {

			document.getElementById("signup_password_content").innerHTML = "";
			document.getElementById("signup_retype_password_content").innerHTML = "";
			document.getElementById("signup_retype_password_content").innerHTML = "Password doest match";
			$('#signup_password').val('');
			$('#signup_retype_password').val('');
			document.getElementById("signup_password").focus();
			return false;

		} else if (signup_username == "") {

			document.getElementById("signup_password_content").innerHTML = "";
			document.getElementById("signup_retype_password_content").innerHTML = "";
			document.getElementById("signup_retype_password_content").innerHTML = "";
			$('#signup_password').val('');
			$('#signup_retype_password').val('');
			document.getElementById("signup_username_content").innerHTML = "Please enter user name";
			document.getElementById("signup_username_content").style.color = "darkolivegreen";
			document.getElementById("signup_username").focus();
			return false;

		}
	}

}
