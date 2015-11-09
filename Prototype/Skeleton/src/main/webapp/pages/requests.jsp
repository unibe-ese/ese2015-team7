<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.ArrayList" %>
<html>
<head>
<title>Awesome Website</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="/Skeleton/css/style.css" />
</head>
<body>
<c:import url="template/header.jsp" />
<div class="outer">

<div class="inner">
<div class="request-page" >

<h1>RequestPage</h1>

<form:form method="post" action="requests/action" modelAttribute="searchForm" id="requests" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
   		 <c:forEach items="${requests}" var="item">

    		<p>
	  			<c:out value="${item.student.name}" />	<c:out value="${item.course.courseName}" />
	  		<!--  	<button type=submit name=visitProfile value="${item.student.email}">Visit Profile</button>-->
	  			<button type=submit name=acceptRequest value="${item.student.email}">Accept Request</button>
	  			<button type=submit name=declineRequest value="${item.student.email}">Decline Request</button>
   			</p>
    	</c:forEach> 
      
   	 </fieldset>
</form:form>

</body>
</html>