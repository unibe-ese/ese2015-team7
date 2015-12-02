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

<form:form method="post" modelAttribute="signupForm" action="create" id="signupForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
       <c:if test="${not empty infoMessage}">
                <div>${infoMessage}</div>
            </c:if>


        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
        <div class="control-group<c:if test="${not empty firstNameErrors}"> error</c:if>">
            <label class="control-label" for="field-firstName">First Name</label>
            <div class="controls">
                <form:input path="firstName" id="field-firstName" tabindex="1" maxlength="35" placeholder="firstName"/>
                <form:errors path="firstName" cssClass="help-inline" element="span"/>
            </div>
        </div><c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
        <div class="control-group<c:if test="${not empty lastNameErrors}"> error</c:if>">
            <label class="control-label" for="field-lastName">Last Name</label>
            <div class="controls">
                <form:input path="lastName" id="field-lastName" tabindex="1" maxlength="35" placeholder="lastName"/>
                <form:errors path="lastName" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="emailErrors"><form:errors path="email"/></c:set>
        <div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
            <label class="control-label" for="field-email">Email</label>

            <div class="controls">
                <form:input path="email" id="field-email" tabindex="1" maxlength="45" placeholder="Email"/>
                <form:errors path="email" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="passwordErrors"><form:errors path="password"/></c:set>
        <div class="control-group<c:if test="${not empty passwordErrors}"> error</c:if>">
            <label class="control-label" for="field-password">Password</label>
            <div class="controls">
                <form:password path="password" id="field-password" tabindex="1" maxlength="35" placeholder="Password"/>
                <form:errors path="password" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <c:set var="passwordErrors"><form:errors path="password"/></c:set>
        <div class="control-group<c:if test="${not empty passwordErrors}"> error</c:if>">
        
            <label class="control-label" for="field-password">confirm Password</label>
            <div class="controls">
                <form:password path="passwordVerify" id="field-passwordVerify" tabindex="1" maxlength="35" placeholder="Password"/>
                <form:errors path="password" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">SignUp</button>
            <button type="button" class="btn" onclick="location.href='/Skeleton/'">Cancel</button>
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