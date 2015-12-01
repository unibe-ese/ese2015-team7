<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:import url="template/header.jsp" />

<head>
<title>Profile</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>

<div class="profile-page" >

	<h2>Profile</h2>
	
	<c:if test="${user.email == principalEmail}">
		<button type="button" class="btn-edit" onclick="location.href='/Skeleton/editProfile'">Edit</button>
	</c:if>
	
	<!-- <img src="image/1212" height="75px" width="75px" align="left" /> -->
	<c:choose>
		<c:when test="${user.email == principalEmail}">
			<h3><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></h3>
		</c:when>
		<c:otherwise>
			<h3><c:out value="${fn:substring(user.firstName, 0, 2)}${fn:substring(user.lastName, 0, 2)}"/></h3>
		</c:otherwise>
	</c:choose>
	<c:if test="${user.email == principalEmail}">
	<h4><c:out value="${user.email}"/></h4>
	</c:if>
	
	<p>
		<button type="button" class="btn" onclick="switchDisplay('profile-frontpage', 'profile-timeslots')">Front Page</button>
		<button type="button" class="btn" onclick="switchDisplay('profile-timeslots', 'profile-frontpage')">Time slots</button>
	</p>
	
	<div id="profile-frontpage">
		<h3>Courses</h3>
		<table class="default">
		<thead>
                	<tr>
                    	<th>University</th>
                        <th>Subject</th>
                        <th>Course</th>
                        <th>Grade</th>
                        <th>Teaching</th>
                        <th></th>
                    </tr>
                </thead>
		<c:forEach items="${userCourses}" var="item">
		  <tr>
		    	<td><c:out value="${item.course.subject.university}" /></td>
		    	<td><c:out value="${item.course.subject}" /></td>
		    	<td><c:out value="${item.course}" /></td>
		    	<td><c:out value="${item.grade}" /></td>
		    	<td><c:out value="${item.teaching}" /></td>
		  </tr>
		  </c:forEach>
		</table>
		
		<h3>Biography</h3>
		<p><c:out value="${user.biography}"/></p>
	</div>
	
	<div id="profile-timeslots" style="display: none;">
		<h3>Time slots</h3>
		<table class="default">
		<thead>
                	<tr>
                    	<th>Period</th>
                        <th>Day</th>
                        <th>From</th>
                        <th>Till</th>
                        <th></th>
                    </tr>
                </thead>
		<c:forEach items="${user.timeSlots}" var="item">
		  <tr>
		    	<td><c:out value="${item.semesterOrSemesterBreak}" /></td>
		    	<td><c:out value="${item.day}" /></td>
		    	<td><c:out value="${item.startTime}" /></td>
		    	<td><c:out value="${item.endTime}" /></td>
		  </tr>
		  </c:forEach>
		</table>
	</div>
	
	<p><button type="button" class="btn" onclick="location.href='/Skeleton/search'">Search Tutor</button></p>
	<c:if test="${user.email!=principalEmail && userCourseId!=0}">
		<form:form method="post" action="myRequests" modelAttribute="searchForm" id="profile" cssClass="form-horizontal"  autocomplete="off">
   		 	<p><button type=submit name=userCourseId value="${userCourseId}" class="btn btn-primary">Send Request for course: ${selectedCourse}, subject: ${selectedSubject}, university: ${selectedUniversity}</button></p>    
		</form:form>
	</c:if>
    
</div>

<c:import url="template/footer.jsp" />

<script type="text/javascript">
	function switchDisplay(id1,id2)
	{
		document.getElementById(id1).style.display = 'none';
		document.getElementById(id2).style.display = 'none';
		document.getElementById(id1).style.display = 'block';
	}
</script>