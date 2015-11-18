<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="notVisited" value="true" />
<c:forEach items="${requests}" var="item" varStatus="loopIdx">
	<c:if test="${ item.isAccepted &&notVisited}">
	<c:set var="notVisited" value="false" />
	<c:set var="visitedHeader" value="true" />
    <h3>Accepted Incoming Requests</h3>
    <table>
				<thead>
                	<tr>
                		
                    	<th><h4>First name</h4></th>
                    	<th><h4>Last name</h4></th>
                        <th><h4>E-Mail</h4></th>
                        <th><h4>Course</h4></th>
                        <th>		   </th>
                      
                    </tr>
                </thead>
               
                <tbody> 
				</c:if>
				<c:if test="${ item.isAccepted}">
                	<tr>
		                		<tr>
									<td>
										${item.student.firstName}
									</td>
									<td>
										${item.student.lastName}
									</td>
									<td>
										<c:out value="${item.student.email}" />
									</td>
									<td>
								   		<c:out value="${item.course.courseName}" />
								   	</td>
								   	<td>
								   		<form:form method="post" action="profile" modelAttribute="searchForm" id="results" cssClass="form-horizontal"  autocomplete="off">
										<button type=submit name=itemUser value="${item.tutor.email}">Visit Profile</button>
								   		</form:form>
								   	</td>
								</tr>
					</tr>
					</c:if>
</c:forEach> 
<c:if test="${visitedHeader}"> 
 <c:set var="visitedHeader" value="false" />
     </tbody> 
</table> 
</c:if>  