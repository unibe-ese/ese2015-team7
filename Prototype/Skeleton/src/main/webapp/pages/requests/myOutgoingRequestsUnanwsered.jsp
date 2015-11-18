    <%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
 <c:set var="notVisited" value="true" />
<c:forEach items="${myRequests}" var="item" varStatus="activIdx">
	<c:if test="${item.isActiv &&notVisited}">    
	 <c:set var="notVisited" value="false" />
	 	 <c:set var="visitedHeader" value="true" />
    <h3>Unanwsered Outgoing Requests</h3>
    <table>
				<thead>
                	<tr>
                    	<th><h4>Tutor</h4></th>
                        <th><h4>Course</h4></th>
                        <th></th>
                        <th></th>
                    
                    </tr>
                </thead>
                <tbody>
				</c:if>   
				<c:if test="${item.isActiv}">              
                	<tr> 

		                		<tr>
									<td>
									${fn:substring(item.tutor.firstName, 0, 2)}${fn:substring(item.tutor.lastName, 0, 2)} 
									</td>
									<td>
								   		<c:out value="${item.course.courseName}" />
								   	</td>
								   	<td>
								   		<form:form method="post" action="profile" modelAttribute="searchForm" id="results" cssClass="form-horizontal"  autocomplete="off">
										<button type=submit name=itemUser value="${item.tutor.email}">Visit Profile</button>
								   		</form:form>
								   
								   	</td>
								   	<td>
								   		<form:form method="post" action="myRequests/action" modelAttribute="searchForm" id="myRequests" cssClass="form-horizontal"  autocomplete="off">
								   		<button type=submit name=deleteRequest value="${item.tutor.email}">delete</button>
								   		<input name="courseId" type="hidden" value="${item.course.id}"/> 
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