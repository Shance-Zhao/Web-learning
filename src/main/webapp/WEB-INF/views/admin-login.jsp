<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Login</title>

<style>
.form-signin {
	max-width: 330px;
	padding: 150px 15px 15px 15px;
	margin: 0 auto;
}
.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}
.form-signin button {
	border-radius: 0;
}
form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: 14px;
	border-radius: 0;
	padding-left: 60px;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-radius: 0;
	padding-left: 60px;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<!-- <div class="signin-head"><img src="images/test/head_120.png" alt="" class="img-circle"></div>-->
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div class="signin">
		<form:form class="form-signin" action="${contextPath}/admin/adminLogin" commandName="admin" method="post">
			<input type="text" class="form-control" name="adminName" placeholder="Name" required autofocus />
		
			<input type="password" class="form-control" name="adminPassword" placeholder="Password" required />
					
				<!-- 	<button class="btn btn-lg btn-warning btn-block" type="submit">Login</button> -->

			<button class="btn btn-lg btn-warning btn-block" type="submit" value="Login" >Login</button>
		</form:form>
	</div>

</body>
</html>
