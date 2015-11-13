<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

    <fieldset>
    <table>
				<thead>
                	<tr>
                    	<th>Student</th>
                        <th>Course</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<tr>
		                	<c:forEach items="${requests}" var="item">
		                		<tr>
									<td>
										<c:out value="${item.student.name}" />
									</td>
									<td>
								   		<c:out value="${item.course.courseName}" />
								   	</td>
								   	<td>
								   		<form:form method="post" action="profile" modelAttribute="searchForm" id="results" cssClass="form-horizontal"  autocomplete="off">
										<button type=submit name=itemUser value="${item.student.email}">Visit Profile</button>
								   		</form:form>
								   	</td>
								   	<td>
								   		<form:form method="post" action="requests/action" modelAttribute="searchForm" id="requests" cssClass="form-horizontal"  autocomplete="off">
								   		<button type=submit name=acceptRequest value="${item.student.email}">Accept Request</button>
								   		<input name="courseId" type="hidden" value="${item.course.id}"/> 
								   		</form:form>
								   	</td>
								   	<td>
								   		<form:form method="post" action="requests/action" modelAttribute="searchForm" id="requests" cssClass="form-horizontal"  autocomplete="off">
								   		<button type=submit name=declineRequest value="${item.student.email}">Decline Request</button>
								   		</form:form>
								   	</td>
								   	
								</tr>
							</c:forEach>
					</tr>
                </tbody>
     </table>
  </fieldset>


</body>
</html>