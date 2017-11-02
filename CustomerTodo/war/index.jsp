<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Hello App Engine</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/cssLoginPage.css" />
</head>

<% if(session!=null && session.getAttribute("sessionname")!=null){
	  response.sendRedirect("todolist-home.jsp");
  }
  else{
	%>

<body>



	<div class="alert" id="alert">
	
	</div>
	

	<div class="form">

		<div class="login_form">
			<form name="login_formf" action="/login"
				onsubmit="return validatelogin()" method="post">
				<div id="account_status" class="message"></div>
				<input type="text" name="login_email" id="login_email"
					placeholder="Email" onkeypress="checkKey()" />
				<div id="login_email_content" class="message"></div>
				<input type="password" name="login_password" id="login_password"
					placeholder="password" onkeypress="checkKey()" />
				<div id="login_password_content" class="message"></div>
				<input class="button" type="submit" value="login"></input>
				<p class="message">
					Not registered? <a class="signup">Create an account</a>
				</p>

			</form>
		</div>


		<div class="signup_form">
			<form name="myForm" action="/registration"
				onsubmit="return validatesignup()" method="post">
				
				<input type="text" name="signup_username" id="signup_username"
					placeholder="username" onkeypress="checkKey()" />
				<div id="signup_username_content" class="message"></div>
				<input type="text" name="signup_email" id="signup_email"
					placeholder="email" onkeypress="checkKey()" />
				<div id="signup_email_content" class="message"></div>
				<input type="password" name="signup_password" id="signup_password"
					placeholder="password" onkeypress="checkKey()" />
				<div id="signup_password_content" class="message"></div>
				<input type="password" id="signup_retype_password"
					placeholder="retype password" onkeypress="checkKey()" />
				<div id="signup_retype_password_content" class="message"></div>
				<input class="button" type="submit" value="Sign up"></input>
				<p class="message">
					Already registered? <a class="login">Sign in</a>
				</p>
			</form>
		</div>

		<button
			onclick="location.href='https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://customer-todos.appspot.com/oauth2callback&response_type=code&client_id=776232384822-ra8n2t63ojonq5csagsl9pbfm4no556v.apps.googleusercontent.com&approval_prompt=force'"
			class="googleButton">sign in with google</button>

	</div>
<script src="javascript/jsLoginValidation.js"></script>
<script src="javascript/jsLoginPage.js"></script>
<%if(session.getAttribute("error")!=null){
if((session.getAttribute("error").toString())=="signup_duplicate_email"){%>
	<script type="text/javascript">
	$('.login_form').hide();
	$('.signup_form').show();
	alertPopUp("Email id already exist");
	</script>
	<% session.invalidate();}%>
	<%}else{} %>
</body>
</html>
<% 
}
%>