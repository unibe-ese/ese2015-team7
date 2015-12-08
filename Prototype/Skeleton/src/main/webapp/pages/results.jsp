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
</head>
<body>

<c:import url="template/searchTemplate.jsp" />
<br>

<div align="left" >
<h1>Search results for <br> course: ${searchedCourse}, subject: ${searchedSubject}, university: ${searchedUniversity} </h1>
<div align="left">
	<form:form method="post" action="profile" modelAttribute="searchForm" id="results"  autocomplete="off">
    	<table  style="width:70%;" class="withBox">
				<thead align="left" class="tableHeader">
                	<tr >
                    	<th>Tutor</th>
                        <th>Course</th>
                        <th>Subject</th>
                        <th>University</th>
                        <th>Grade</th>
                        <th id="noBox"></th>
                    </tr>
                </thead>
                <tbody align="left">
                	<c:forEach items="${userCourses}" var="item">
                		<tr id="tr${i.index}">
							<td><c:out value="${fn:substring(item.user.firstName, 0, 1)}.${fn:substring(item.user.lastName, 0, 1)}." /></td>
							<td><c:out value="${item.course}" /></td>
							<td><c:out value="${item.subject}" /></td>
							<td><c:out value="${item.university}" /></td>
							<td><c:out value="${item.grade}" /></td>
						    <td id="noBox">
						    	<button type=submit name=userCourseId value="${item.userCourseId}">Visit Profile</button>
						    </td>
						</tr>
    				</c:forEach>
    	</table>
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
