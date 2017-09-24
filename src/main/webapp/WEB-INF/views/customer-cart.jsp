<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.shopping.pojo.Product"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>Cart</title>
</head>
<body>
<table class="table">
        <tr>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <c:out value="${prolist.size()}"></c:out>
        <c:forEach var="pro" items="${prolist}">
        <tr class="warning">
        	<td>${pro.name}</td>
        	<td>${pro.saleprice}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>