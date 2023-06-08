<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>

<h1> Hola!!! <c:out value="${usuario.userName}"></c:out></h1> 
	
	<a href="/logout"> Log out</a>
	
	<table class="table">


    <tr>
      <th scope="row">First Name :</th>
      <td>${usuario.userName}</td>
    </tr>
    <tr>
      <th scope="row">Last Name :</th>
      <td>${usuario.userLastName}</td>
    </tr>
    <tr>
      <th scope="row">Email :</th>
      <td>${usuario.email}</td>
    </tr>
        <tr>
      <th scope="row">Sign up date :</th>
      <td>${usuario.createdAt}</td>
    </tr>
        <tr>
      <th scope="row">Last Sign in :</th>
      <td>${usuario.updatedAt}</td>
    </tr>

</table>
</body>
</html>