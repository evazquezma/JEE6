<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
<head>
	<meta charset="utf-8" />	
</head>


<body>
	<h3>CAS Login</h3>
	
	<div class="mini">
	  <form method="POST">
	    <label for="username">Username</label>
	    <input type="text" id="username" name="username" placeholder="user name">
		<br/>
		
	    <label for="password">Password</label>
	    <input type="text" id="password" name="password" placeholder="user password">
		<br/>

	    <input type="hidden" name="service" value="${service}">
	  
	    <input type="submit" value="Submit">
	  </form>
	</div>

</body>
</html>
