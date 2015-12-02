<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/Skeleton/img/favicon.ico">
<title>StudentConnector</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="/Skeleton/css/style.css" />
<link rel="stylesheet" href="/Skeleton/css/awesomeStyle.css" />
</head>
<body>

<div class="outer">
<div class="middle">
<div class="inner">
<div class="login-page" >



<h1>Login</h1>

<form:form name='loginForm' action="j_spring_security_check" method='POST'>
    <fieldset>


			<c:if test="${not empty param.err}">
                <div><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
            </c:if>
            <c:if test="${not empty param.out}">
                <div>You've logged out successfully.</div>
            </c:if>
            <c:if test="${not empty param.time}">
                <div>You've been logged out due to inactivity.</div>
            </c:if>
            
            
            
        <c:set var="nameErrors"><form:errors path="name"/></c:set>
        
        <div class="control-group<c:if test="${not empty nameErrors}"> error</c:if>">
            <label for="field-username">E-mail:</label>
            <div class="controls">
            	<input name="j_username" id="field-username" type="email"/>
                
                <form:errors path="name" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <c:set var="passwordErrors"><form:errors path="password"/></c:set>
        <div class="control-group<c:if test="${not empty passwordErrors}"> error</c:if>">
            <label for="field-password">Passwort:</label> 
            <div class="controls">
           		<input name="j_password" id="field-password" type="password"/>
             
                <form:errors path="password" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Login</button>
            <button type="button" class="btn" onclick="location.href='/Skeleton/signUp'">SignUp</button>
        </div>
    </fieldset>
</form:form>



</div>
</div>
</div>
</div>

	<c:if test="${page_error != null }">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Error!</h4>
			${page_error}
		</div>
	</c:if>
</body>
</html>