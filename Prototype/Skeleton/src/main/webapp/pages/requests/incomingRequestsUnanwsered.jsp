<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 <c:set var="notVisited" value="true" />
<c:forEach items="${requests}" var="item" varStatus="activIdx">
	<c:if test="${item.isActiv &&notVisited}">    
	 <c:set var="notVisited" value="false" />
	 <c:set var="visitedHeader" value="true" />
	<h3>Unanwsered Incoming Requests</h3>
    <table style="width:60%">
				<thead align="left">
                	<tr>
                    	<th><h4>Student</h4></th>
                        <th><h4>Course</h4></th>
                        <th><h4>Subject</h4></th>
                        <th><h4>University</h4></th>
                        <th>		   </th>                      
                    </tr>
                </thead>
               
                <tbody align="left"> 
	</c:if>   
				<c:if test="${item.isActiv}">  
                		<tr>
							<td>
								${fn:substring(item.student.firstName, 0, 2)}${fn:substring(item.student.lastName, 0, 2)} 
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
						   		<form:form method="post" action="profile" modelAttribute="searchForm" id="results" cssClass="form-horizontal"  autocomplete="off">
								<button type=submit name=studentsEmail value="${item.studentsEmail}">Visit Profile</button>
						   		</form:form>
						   	</td>
						   	<td>
						   		<form:form method="post" action="requests/action" modelAttribute="searchForm" id="requests" cssClass="form-horizontal"  autocomplete="off">
						   		<button type=submit name=acceptRequest value="${item.id}">Accept Request</button>
						   		</form:form>
						   	</td>
						   	<td>
						   		<form:form method="post" action="requests/action" modelAttribute="searchForm" id="requests" cssClass="form-horizontal"  autocomplete="off">
						   		<button type=submit name=declineRequest value="${item.id}">Decline Request</button>
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