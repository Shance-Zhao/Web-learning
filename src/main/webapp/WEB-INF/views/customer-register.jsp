<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form action="${contextPath}/customer/register" commandName="customer" method="post">

		<table>
			<tr>
				<td>First Name:</td>
				<td>
					<form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font>
				</td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td>
					<form:input path="lastName" size="30" required="required"/>
					<font color="red"><form:errors path="lastName" /></font>
				</td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td>
					<form:input path="userName" size="30" required="required" />
					<font color="red"><form:errors path="userName" /></font>
				</td>
			</tr>

			<tr>
				<td>Password:</td>
				<td>
					<form:password path="customerPassword" size="31" required="required" /> 
					<font color="red"><form:errors path="customerPassword" /></font>
				</td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td>
					<form:input path="customerEmail" size="30" type="email" required="required" /> 
					<font color="red"><form:errors path="customerEmail" /></font></td>
			</tr>
			
			<tr>
				<td>Are you student?</td>
				<td>
		            <form:radiobutton path="isStudent" value="yes" label="Yes" /> 
		            <form:radiobutton path="isStudent" value="no" label="No" />
					<font color="red"><form:errors path="isStudent" /></font>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register User" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>