<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.hotel.myapp.pojo.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Hotel Detail</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<style>
	.thumbnail img {
  width : 100%;
}
/* select caption-full class under thumbnail, space in btw!!! */
.thumbnail .caption-full{
  padding: 9px;
}
.thumbnail{
  padding: 0;
}
</style>
</head>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="../hotels">Hotels</a>
			<c:set var="user" scope="session" value="${sessionScope.curUser}" />
			<c:if test="${user == null}">
				<a class="navbar-brand" href="../register">Register</a>
				<a class="navbar-brand" href="../login">Login</a>
			</c:if>
			<c:if test="${user != null}">
				<c:if test="${user.getType() != 'Admin'}">
				<a class="navbar-brand" href="../viewbooking">View Booking</a>
				</c:if>
				<c:if test="${user.getType() == 'Admin'}">
					<a class="navbar-brand" href="../managebooking">Manage Booking</a>
				</c:if>
				<a class="navbar-brand"> login in as <c:out
						value="${user.getUserEmail()}" /></a>
				<a class="navbar-brand" href="../logout">Logout</a>
			</c:if>
		</div>
	</div>
</nav>
<h2>Hotel Details</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<h3 class="lead"><c:out value="${requestScope.hotel.getHotelName()}" /></h3>
				<div class="caption-full">
					<h4>
						Price :
						<c:out value="${requestScope.hotel.getPrice()}" />
						/night
					</h4>
					<h4>
						Location :
						<c:out value="${requestScope.hotel.getLocation()}" />
					</h4>
					<h4>
						Description :
						<c:out value="${requestScope.hotel.getDescription()}" />
					</h4>
					<c:set var = "user" scope="session" value="${sessionScope.curUser}"/>
					<c:if test="${user != null && user.getType() != 'Admin'}">
						<a class="btn btn-info" href="/myapp/hotelbook/?id='${requestScope.hotel.getId()}'">Book</a>
					</c:if>
					<c:if test="${user.getType() == 'Admin'}">
						<a class="btn btn-danger" href="/myapp/hoteldelete/?id='${requestScope.hotel.getId()}'">Delete</a>
						<a class="btn btn-warning" href="/myapp/hotelupdate/?id='${requestScope.hotel.getId()}'">Update</a>
					</c:if>
				</div>
			</div>
			<div class="col-md-9">
				<div class="thumbnail">
					<img class="img-responsive" src="${requestScope.hotel.getImage()}">
				</div>
			</div>
		</div>
	</div>
</body>
</html>