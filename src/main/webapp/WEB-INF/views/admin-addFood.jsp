<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	$(function() {
		$("#action")
				.click(
						function() {
							var num = document.getElementById('rows').value;
							var theader = "<table border='1'>"
									+ "<tr><th width=100>Name</th>"
									+ "<th width=100>Production Date</th>"
									+ "<th width=100>Self Life</th>"
									+ "<th width=100>Picture</th>"
									+ "<th width=100>Quantity</th>"
									+ "<th width=100>Price/per</th>"
									+ "<th width=100>Sale Price</th>"
									+ "<th width=100>Description</th></tr>";
							var tbody = "";

							for (i = 0; i < num; i++) {
								tbody += "<tr><td><input name='name' id='name' size='10' required='required' /></td>"
										+ "<td><input type='text' name='productionDate' size='10' id='productionDate"+i+"' required='required' maxDate='selflifeDate"+i+"'/></td>"
										+ "<td><input type='text' name='selflifeDate' id='selflifeDate"+i+"' minDate='productionDate"+i+"' size='10' required='required'/></td>"
										+ "<td><input type='file' name='img' id='img' size='10' required='required'/></td>"
										+ "<td><input name='quantity' id='quantity' size='10' required='required' /></td>"
										+ "<td><input name='price' id='price' size='10' required='required' /></td>"
										+ "<td><input name='saleprice' id='saleprice' size='10' required='required' /></td>"
										+ "<td><input name='description' id='description' size='10' required='required' /></td></tr>";
							}
							var tfooter = "</table>";
							var sub = "<br /><input type='submit' name='btn_submit' id='btn_submit' value='Submit' />";

							var elem = document.getElementById("action");
							elem.parentNode.removeChild(elem);
							$("#txtHint1").append(
									theader + tbody + tfooter + sub);

							$('input[name$="Date"]').datepicker(
									{
										DateFormat : 'yy-MM-dd',	//it will shows like MM/dd/yyyy
										beforeShow : function() {
											if ($(this).attr('maxDate')) {
												var dateItem = $('#'
														+ $(this).attr(
																'maxDate'));
												if (dateItem.val() !== "") {
													$(this).datepicker(
															'option',
															'maxDate',
															dateItem.val());
												}
											}

											if ($(this).attr('minDate')) {
												var dateItem = $('#'
														+ $(this).attr(
																'minDate'));
												if (dateItem.val() !== "") {
													$(this).datepicker(
															'option',
															'minDate',
															dateItem.val());
												}
											}
										}
									});
						});
	});
</script>

<style>
.list-group-item {
	width: 200px;
}

li {
	list-style: none;
}
</style>
<title>Admin Management</title>

</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div class="container-fluid"
		style="background-color: #2196F3; color: #fff; height: 113px;">
		<h2>Admin Website</h2>
		<nav class="navbar">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

					<div class="collapse navbar-collapse" id="myNavbar">

						<ul class="nav navbar-nav navbar-right">
							<li><a style="font-size: 2ex; color: #fff" href="#section1">
									<i class="fa fa-users"></i>Messages
							</a></li>
							<li class="dropdown"><a style="font-size: 2ex; color: #fff"
								class="dropdown-toggle" data-toggle="dropdown"
								href="${contextPath}/admin/profile.htm">Account <span
									class="caret"></span>
							</a> <c:if test="${admin.getAdminName() != null}">
									<ul class="dropdown-menu">
										<li><a href="${contextPath}/admin/profile.htm"><c:out
													value="${admin.getAdminName()}" /></a></li>
										<li><a href="${contextPath}/admin/logout.htm">Logout</a></li>
									</ul>
								</c:if></li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</div>
	<br>
	<br>
	<div class="container-fluid">
		<!-- Right Contents Show -->

		<div class="row-fluie">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar" style="margin-top: 10ex">
					<!-- 1-level menu -->
					<li><a class="btn btn-info" href="#userMeun"
						class="nav-header menu-first" data-toggle="collapse"> <i
							class="fa fa-user"></i>&nbsp;Manage Account
					</a></li>

					<li><a class="btn btn-info" href="#productMenu"
						class="nav-header menu-first collapsed" data-toggle="collapse">
							<i class="fa fa-globe"></i>&nbsp;Manage</span>
					</a></li>
					<ul id="productMenu" class="nav nav-list collapse menu-second">
						<li><a class="btn-success" href="${contextPath}/general/addGeneral"> <i
								class="fa fa-list-alt"></i>General Items
						</a></li>

						<li><a name="bok" class="btn" href="${contextPath}/book/addBook"><i
								class="fa fa-list-alt"></i>Book</a></li>
						<li><a class="btn-success" href="${contextPath}/food/addFood"><i
								class="fa fa-list-alt"></i>Food</a></li>
						<li><a name="mam" class="btn" href="${contextPath}/admin/manage"><i
								class="fa fa-list-alt"></i>Manage All</a></li>
					</ul>


					<li><a class="btn btn-info" href="#recordMeun"
						class="nav-header menu-first collapsed" data-toggle="collapse">
							<i class="fa fa-file-text"></i>&nbsp;Reports<span class="sr-only">(current)</span>
					</a></li>
					<ul id="recordMeun" class="nav nav-list collapse menu-second">
						<li><a class="btn" href="###"
							onclick="showAtRight('${contextPath}/admin/addTable')"><i
								class="fa fa-list"></i>Best Seller</a></li>

						<li><a class="btn-success" href="###"
							onclick="showAtRight('recordList.jsp')"><i class="fa fa-list"></i>Curv</a></li>

						<li><a class="btn" href="###"
							onclick="showAtRight('recordList.jsp')"><i class="fa fa-list"></i>Food</a></li>
					</ul>

				</ul>

			</div>
		</div>
		<div class="col-sm-9 col-md-10 main">
			<h1 class="page-header">
				<i class="fa fa-cog fa-spin"></i>&nbsp;Management <small>&nbsp;&nbsp;&nbsp;Welcome</small>
			</h1>
			<h3>How many Food do you want to add?</h3>
			<form method='post' action='${contextPath}/food/addsuccess'
				enctype="multipart/form-data">
				<label>Enter: <input type="text" name="rows" id="rows" /></label><br />
				<input id="action" type="submit" value="submit" />

				<div id="txtHint1"></div>
			</form>
		</div>
	</div>
</body>
</html>