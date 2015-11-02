<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<c:import url="template/header.jsp" />

<div class="profile-page" >

	<h2>Profile</h2>
	
	<c:if test="${user.email == principalName}">
		<button type="button" class="btn-edit" onclick="location.href='/Skeleton/editProfile'">Edit</button>
	</c:if>
	
	<!-- <img src="image/1212" height="75px" width="75px" align="left" /> -->
	<h3><c:out value="${user.name}"/></h3>
	<h4><c:out value="${user.email}"/></h4>
	
	<p>
		<button type="button" class="btn" onclick="switchDisplay('profile-frontpage', 'profile-timeslots')">Front Page</button>
		<button type="button" class="btn" onclick="switchDisplay('profile-timeslots', 'profile-frontpage')">Time slots</button>
	</p>
	
	<div id="profile-frontpage">
		<h3>Grades</h3>
		<table class="default">
		<thead>
                	<tr>
                    	<th>University</th>
                        <th>Subject</th>
                        <th>Course</th>
                        <th>Grade</th>
                        <th></th>
                    </tr>
                </thead>
		<c:forEach items="${user.grades}" var="item">
		  <tr>
		    	<td><c:out value="${item.university}" /></td>
		    	<td><c:out value="${item.subject}" /></td>
		    	<td><c:out value="${item.course}" /></td>
		    	<td><c:out value="${item.grade}" /></td>
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
	<p><button type="button" class="btn" onclick="location.href='/Skeleton/profile'">Handle Requests</button></p>
    
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