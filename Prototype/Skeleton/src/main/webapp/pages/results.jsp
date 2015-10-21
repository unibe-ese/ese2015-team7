<%@page import="org.sample.controller.service.SampleServiceImpl"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.sample.model.University" %>



<c:import url="template/header.jsp" />

<html>
<head>
<title>Search Results</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="/Skeleton/css/style.css" />
</head>
<body>

<h1>Search Results</h1>

<form:form method="post" modelAttribute="searchForm"  id="results" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
        <c:set var="tutorsErrors"><form:errors path="tutors"/></c:set>
        <div class="control-group<c:if test="${not empty TutorsErrors}"> error</c:if>">
            <label class="control-label" for="field-tutors">Tutors</label>
            <div class="controls">
                <form:select path="tutors" id="field-tutors" tabindex="4">
                <form:option value='None' label="Select Tutor"/>
                		<form:options items="${tutors}"/>
                </form:select>
                <form:errors path="tutors" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
    </fieldset>
</form:form>


	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
