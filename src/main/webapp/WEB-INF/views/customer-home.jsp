<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Account</title>
  <body>
  <br/>
  <a href="register.htm" style="text-decoration: none;">New? Register</a>
  <br/>
  <br/>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  
  <form:form action="${contextPath}/customer/login" commandName="customer" method="post">
	
		<table>
		<tr>
		    <td>User Name:</td>
		    <td><input name="userName" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input name="customerPassword" type="password" size="30" required="required"/></td>
		</tr>
		<tr>
			<td></td>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form:form>
</body>
</html>