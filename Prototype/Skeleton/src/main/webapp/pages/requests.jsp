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

<form:form method="post" action="requests" modelAttribute="searchForm" id="requests" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
   		 <c:forEach items="${requests}" var="item">

    		<p>
	  			<c:out value="${item.student.name}" />
   			</p>
    	</c:forEach> 
      
   	 </fieldset>
</form:form>

</body>
</html>