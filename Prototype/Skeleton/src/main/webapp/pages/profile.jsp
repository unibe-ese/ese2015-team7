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
	
	<c:choose>
		<c:when test="${user.email == principalEmail}">
			<h3><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></h3>
		</c:when>
		<c:otherwise>
			<h3><c:out value="${fn:substring(user.firstName, 0, 1)}.${fn:substring(user.lastName, 0, 1)}."/></h3>
		</c:otherwise>
	</c:choose>
	<c:if test="${user.email == principalEmail}">
	<h4><c:out value="${user.email}"/></h4>
	</c:if>
	
	<p>
		<button type="button" id="frontButton" class="btn">Front Page</button>
		<button type="button" id="timeslotsButton" class="btn">Time slots</button>
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
		    	<td>
		    		<c:choose>
		    			<c:when test="${item.grade != 0.0}">
		    				<c:out value="${item.grade}" />
		    			</c:when> 
		    			<c:otherwise>
       						 -
   						</c:otherwise>
		    		</c:choose>
		    	</td>
		    	<td>
		    		<c:choose>
		    			<c:when test="${item.teaching == true}">
		    				yes
		    			</c:when> 
		    			<c:otherwise>
       						no
   						</c:otherwise>
		    		</c:choose>
		    	</td>
		    	
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
	
	
	<c:if test="${user.email!=principalEmail && userCourseId!=0}">
		<div id="buttonBox">
			<div id="buttonText"  >
				Selected Course is: <i>${selectedCourse}</i><br>
				subject: <i>${selectedSubject}</i><br> 
				university: <i>${selectedUniversity}</i>
			</div>
			<div id="button">
				<form:form method="post" action="myRequests" modelAttribute="searchForm" id="profile" cssClass="form-horizontal"  autocomplete="off">
   			 		<p><button type=submit name=userCourseId value="${userCourseId}" class="btn btn-primary">Send Request for selected course</button></p>    
				</form:form>
			</div>
		</div>
	
	
	<p><br>Not convinced? <br> 
	<button type="button" class="btn" onclick="location.href='/Skeleton/search'">Search Tutor</button></p>
    </c:if>
</div>

<c:import url="template/footer.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	$("#profile-timeslots").hide();	
	$("#frontButton").hide();

	$("#timeslotsButton").click(function(){
		toggleChangePasswordElements();
	});
	
	$("#frontButton").click(function(){
		toggleChangePasswordElements();
	});
	
	function toggleChangePasswordElements()
	{
		$("#profile-timeslots").toggle();
		$("#profile-frontpage").toggle();
		$("#frontButton").toggle();
		$("#timeslotsButton").toggle();
	}
});
</script>