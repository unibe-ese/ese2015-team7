    <%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
 <c:set var="notVisited" value="true" />
<c:forEach items="${myRequests}" var="item" varStatus="activIdx">
	<c:if test="${item.isActiv && notVisited}">    
	 <c:set var="notVisited" value="false" />
	 	 <c:set var="visitedHeader" value="true" />
    <h3>Unanwsered Outgoing Requests</h3>
    <table style="width:50%">
				<thead align="left">
                	<tr>
                    	<th><h4>Tutor</h4></th>
                        <th><h4>Course</h4></th>
                        <th><h4>Subject</h4></th>
                        <th><h4>University</h4></th>         
                    </tr>
                </thead>
                <tbody align="left"> 
	</c:if>   
				<c:if test="${item.isActiv}">       
                		<tr>
							<td>
								${fn:substring(item.tutor.firstName, 0, 1)}.${fn:substring(item.tutor.lastName, 0, 1)}. 
							</td>
							<td>
						   		<c:out value="${item.course.courseName}" />
						   	</td>
						   	<td>
						   		<c:out value="${item.subject.subjectName}" />
						   	</td>
						   	<td>
						   		<c:out value="${item.university.universityName}" />
						   	</td>
						   	<td>
						   		<form:form method="post" action="profile" modelAttribute="searchForm" id="results"  autocomplete="off">
								<button type=submit name=userCourseId value="${item.userCourseId}" class="btn btn-primary">Visit Profile</button>
						   		</form:form>
						   	</td>
						   	<td>
						   		<form:form method="post" action="myRequests/action" modelAttribute="searchForm" id="myRequests"  autocomplete="off">
						   		<button type=submit name=deleteRequest value="${item.id}" class="btn btn-primary">delete</button>
						   		</form:form>
						   	</td>
						</tr>
				</c:if>  
					
</c:forEach>  
<c:if test="${visitedHeader}"> 
 <c:set var="visitedHeader" value="false" />
     </tbody> 
</table> 
</c:if>   