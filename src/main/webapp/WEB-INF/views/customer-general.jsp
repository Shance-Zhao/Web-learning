<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.shopping.pojo.Product"%>
<%@ page import="com.shopping.dao.ProductDAO"%>
<%@ page language="java" import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>SZ Shopping</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <meta http-equiv="refresh" content="0; URL=./PageController" />-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/image/css/order.js"></script>
<script type="text/javascript" src="/image/css/cookie.js"></script>
<link href="/image/css/order.css" rel="stylesheet">
<style>
    .container {
        margin-top: 90px;
    }

    .navbar {
        font-family: Montserrat, sans-serif;
        margin-bottom: 0;
        border: 0;
        font-size: 11px !important;
        letter-spacing: 4px;
        opacity: 0.9;
    }

    .navbar-nav li a:hover {
        color: #fcc !important;
    }

    .navbar-nav li.active a {
        color: #fff !important;
        background-color: #29292c !important;
    }

    .navbar-default .navbar-toggle {
        border-color: transparent;
    }

    .carousel-inner img {
        width: 100%;
        /* Set width to 100% */
        margin: auto;
    }

    .carousel-caption h3 {
        color: #fff !important;
    }

    @media ( max-width: 600px) {
        .carousel-caption {
            display: none;
            /* Hide the carousel text when the screen is less than 600 pixels wide */
        }
    }

    th,
    td {
        padding-bottom: 20px;
    }
</style>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
 <script>
function showUser(str) {
	var xmlhttp;
	if(str.length==0)
	{
		document.getElementById("display").innerHTML="";
		return;
	}
	try{ 
	// Opera 8.0+, Firefox, Safari
		xmlhttp=new XMLHttpRequest();
  	}catch(e){
	// Internet Explorer Browsers
	try{
          xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
      }catch (e) {

          try{
              xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
          }catch (e){
              alert("Your browser broke!");
              return false;
          }
      }
  }  
  	var search = document.sinform.x.value;
  	
 // var url="customer/aa?val="+search;
  var url="${contextPath}/customer/aa?val="+search;
  xmlhttp.onreadystatechange=function() {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
	    	var xmlDisplay = document.getElementById("display");
	    	var result = xmlhttp.responseText;
	      	xmlDisplay.innerHTML=result;
	    }
	  }
  xmlhttp.open("GET",url,true);
  xmlhttp.send();
}
</script>


</head>

<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">
    <div class="container">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button> <a class="navbar-brand" href="/control">SZ</a>
                    <!-- Search -->
                    <div class="col-md-9" style="margin-top: 1ex; margin-left: 10ex">
                        <form name="sinform">
                            <div class="input-group">
                                <div class="input-group-btn search-panel">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> <span id="search_concept">ALL</span> <span class="caret"></span> </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="${contextPath}/customer/generalpage">General</a></li>
                                        <li><a href="#books">Book</a></li>
                                        <li><a href="#food">Food</a></li>
                                        <li><a href="${contextPath}/customer/all">All</a></li>
                                    </ul>
                                </div>
                                <!-- <input type="hidden" name="search_param" value="all" id="search_param">-->
                                <input type="text" class="form-control" name="x" id="x" onkeyup="showUser(this.value)" placeholder="Search"> <span class="input-group-btn">
            <button class="btn btn-default" id="searchx" type="button">
                <span class="glyphicon glyphicon-search"></span> </button>
                                </span>
                            </div>
                        </form>
                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-left">
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Departments <span
                class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${contextPath}/customer/generalpage">General</a></li>
                                    <li><a href="#">Book</a></li>
                                    <li><a href="#">Food</a></li>
                                    <li><a href="${contextPath}/customer/all">All</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#" href="#history">Browsing
                History <span class="caret"></span>
        </a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="${contextPath}/customer/account.htm">Account <span
                class="caret"></span>
        </a>
                                <c:if test="${customer.getUserName() == null}">
                                    <ul class="dropdown-menu">
                                        <li><a href="${contextPath}/customer/account.htm">Sign
                            in/Register</a></li>
                                    </ul>
                                </c:if>
                                <c:if test="${customer.getUserName() != null}">
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="${contextPath}/customer/profile.htm">
                                                <c:out value="${customer.getUserName()}" />
                                            </a>
                                        </li>
                                        <li><a href="${contextPath}/customer/logout.htm">Logout</a></li>
                                    </ul>
                                </c:if>
                            </li>
                            <li><a href="#orders">Orders</a></li>
                            <li><a href="showCart">Cart</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active"> <img src="/image/index1.jpg" alt="Echo Dot" width="1200" height="700">
                <div class="carousel-caption">
                    <h3>Echo Dot</h3> </div>
            </div>
            <div class="item"> <img src="/image/index2.jpg" alt="Fashion" width="1200" height="700">
                <div class="carousel-caption">
                    <h3>Fashion Save</h3> </div>
            </div>
            <div class="item"> <img src="/image/index3.jpg" alt="Off" width="1200" height="700">
                <div class="carousel-caption"></div>
            </div>
        </div>
        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span> </a>
    </div>
<div id="display">
	
	</div>
    <div class="container text-center">
        <h3>Products</h3>
        <table>
            <tr>
                <th width="600px"></th>
                <th width="600px"></th>
                <th style="text-align: center" width="500px">Name</th>
                <th style="text-align: center" width="500px">Picture</th>
                <th style="text-align: center" width="500">Price</th>
                <th style="text-align: center" width="500">Description</th>
                <th style="text-align: center" width="500">Production Date</th>
            </tr>
 			<c:forEach var="pro" items="${products}">
                <c:if test="${pro.type.getName().equals('General')}">
                    <tr>
                        <td>
                            <input id="${pro.productID}" class="btn-warning" name="buy" type="button" value="Buy" ordername="${pro.name}" price="${pro.saleprice}" /> </td>
                        <td name="proID" type="hidden" value="${pro.productID}"></td>
                        <td>${pro.name}</td>
                        <td><img src="${pro.filename}" width='150' height='170'></td>
                        <td>${pro.saleprice}</td>
                        <td>${pro.description}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
    <br />
    <br />
    <br />
    <script>
        $.fn.order.defaults = {
            staticname: 'order'
            , orderclass: 'order'
            , savecookie: true
            , cookiename: 'order'
            , numpre: 'no_'
            , orderpre: 'order_'
            , orderspanpre: 'orderspan_'
            , pricefiled: 'price'
            , namefiled: 'ordername'
            , leftdemo: 'C A R T'
            , subbuttom: ''
            , addbuttom: 'input.btn-warning'
            , nomessage: 'Nothing'
            , dosubmit: function (data) {
                $("#goodsinfo").val(JSON.stringify(data));
                $("#cartform").submit();
            }
        };
        $("body").order();
    </script>
    <hr>
    <footer>
        <p style="text-align: center">Company: SZ Company </footer>
</body>

</html>