<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="javascript/jquery-3.2.1.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home Page</title>
	<link rel="stylesheet" type="text/css" href="css/todo.css">
</head>
<body>
	<h3>Customer Todo List</h3>
	<%String email = (String)request.getAttribute("Email");%>
	<%String name = (String)request.getAttribute("User");%>
	<div class="dropdown">
	  	<input type="button" value="Profile" class="dropbtn">
	  	<div class="dropdown-content">
	  		<label>Name : <%= name %></label><br>
		    <label>Email: <%= email %></label><br>
		    <input type="button" id="logoutButton" value="Logout" onclick="location.href='/logout';">
	  	</div>
	</div>
	<div id="myDIV" class="form">
	
	  	<div class="blocks">
	  		
	  		<input type="button" class="details1 new-gray-btn1" value="Add Customer" onclick="displayblock();">  
	  		<div id="customerPopup" style="display: none;">
	  		<div class="arrow"> <i class="up"></i></div>
	  		<div id="addnewCustomerPopup" class="bottom open popover" style="display: block;">
										
										<h4 style="font-family: sans-serif;"class="popover-title" >Add New Customer</h4>
										<div class="popover-content">
											<input id="customerNewName" type="text" placeholder="First Name" class="newdetails" onkeydown = "if (event.keyCode == 13){addcust();}">*										<input id="customerNewLastName" type="text" placeholder="Last Name" class="newdetails" onkeydown = "if (event.keyCode == 13){addcust();}">
	   										<input id="customerNewEmail" type="text" placeholder="E-mail" class="newdetails" onkeydown = "if (event.keyCode == 13){addcust();}">
											<input id="customerNewMobile" type="text" placeholder="Mobile" class="newdetails" onkeydown = "if (event.keyCode == 13){addcust();}">
	   										<button id="addnewCustomer" class="new-gray-btn new-green-btn" onclick="addcust();">Add Customer</button>
											<button id="cancelNewCustomer" class="cancelNewStaff new-gray-btn" onclick="closepop();">Cancel</button> 
										</div>
								</div>
			
	    </div>
	    <p></p>
	    <div class="listBlocks">
	    <ul id="custList"></ul>
	    </div>
	    </div>
	    <div class=" blocks"> 
	    <div id="custDetails" class="Custdetails1"  style="display: none;">
	    	
		    	<label class="label">First Name</label>
		    	<input type="text" class="custdetails" id="custFName" placeholder="First Name" onkeydown = "if (event.keyCode == 13){updateCust();}">
		    	<br>
		    	<label class="label">Last Name</label>
		    	<input type="text" class="custdetails" id="custLName" placeholder="Last Name" onkeydown = "if (event.keyCode == 13){updateCust();}">
		    	<br>
		    	<label class="label">Email Address</label>
		    	<input type="text" class="custdetails" id="custEmail" placeholder="Email Address" onkeydown = "if (event.keyCode == 13){updateCust();}">
		    	<label class="label">Phone Number</label>
		    	<input type="text" class="custdetails" id="custPhone" placeholder="Phone Number" onkeydown = "if (event.keyCode == 13){updateCust();}">
		    	<input type="button" class="details1 new-gray-btn1" id="updateCust" value="Update" onclick="updateCust();">
	    </div>
	    </div>
	    <div class=" blocks">
	    <div id="custTodo" class="Custdetails1" style="display: none;">
	    	<div style="width: 97%;">
   		 		<input type="text" class="details"id="task" placeholder="Add Item..." name="task" onkeydown = "if (event.keyCode == 13){add();}"   >
	    		<br><br>
	    		<div class="listBlocks">
	    		<ul id="todo"></ul>
	    		</div>
	    	</div>
	    </div>
	    </div>
	    <div id="pageBlockNewCustomer" style="display: none;"></div>
	</div>
	<script src="javascript/todo.js"></script>
</body>
</html>