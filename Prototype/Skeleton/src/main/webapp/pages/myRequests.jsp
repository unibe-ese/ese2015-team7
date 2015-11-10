<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


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
<div class="myRequest-page" >


<h1>myRequestPage</h1>

    
    <fieldset>
    		<table>
				<thead>
                	<tr>
                    	<th>Tutor</th>
                        <th>Course</th>
                      
                        
                    </tr>
                </thead>
                <tbody>
                	<tr> 
                			<c:if test="${fn:length(myRequests) > 0}" >
                			<c:forEach items="${myRequests}" var="item"/>
		                		<tr>
									<td>
										<c:out value="${item.tutor.name}" /> 
									</td>
									<td>
								   		<c:out value="${item.course.courseName}" />
								   	</td>
										<form:form method="post" action="myRequests/action" modelAttribute="searchForm" id="myRequests" cssClass="form-horizontal"  autocomplete="off">
								   		<button type=submit name=deleteRequest value="${item.tutor.email}">delete</button>
								   		</form:form>
								</tr>
				
						
							</c:if>
					</tr>
                </tbody>
			</table>	
   	 </fieldset>


</body>
</html>