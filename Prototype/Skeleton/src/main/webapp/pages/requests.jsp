<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="/Skeleton/css/style.css" />
</head>
<body>
<c:import url="template/header.jsp" />
<div class="request-page" >

<h2>Incoming Requests</h2>

	<c:if test="${not empty infoMessage}">
        <div>${infoMessage}</div>
    </c:if>
 	<fieldset>
		<c:import url="requests/incomingRequestsUnanwsered.jsp" />
			<c:if test="${not empty message}">
      			  <div>${message}</div>
    		</c:if>
		<c:import url="requests/incomingRequestsAccepted.jsp" />    
   	 </fieldset>

</div>
</body>
</html>