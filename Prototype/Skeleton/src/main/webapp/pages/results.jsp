<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.sample.model.University" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:import url="template/header.jsp" />

<html>
<head>
<title>Search Results</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="/Skeleton/css/style.css" />
</head>
<body>

<c:import url="template/searchTemplate.jsp" />
<br>

<div align="left" >
<h1>Search results for <br> course: ${searchedCourse}, subject: ${searchedSubject}, university: ${searchedUniversity} </h1>
<div align="left">
<form:form method="post" action="profile" modelAttribute="searchForm" id="results"  autocomplete="off">
    <c:forEach items="${userCourses}" var="item">
	    <p>${fn:substring(item.user.firstName, 0, 2)}${fn:substring(item.user.lastName, 0, 2)}, course: ${item.course}, subject: ${item.course.subject}, university: ${item.course.subject.university} 
	    <button type=submit name=itemUser value="${item.user.email}">Visit Profile</button>
    </p>
    </c:forEach>
</form:form>


	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>

</div>
</div>
<c:import url="template/footer.jsp" />
