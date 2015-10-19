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
<title>Search for a Tutor</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="/Skeleton/css/style.css" />
</head>
<body>

<h1>Search for a Tutor</h1>

<form:form method="post" modelAttribute="searchForm" action="createSearch" id="searchForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
		<jsp:scriptlet>
			SampleServiceImpl service = new SampleServiceImpl();
            ArrayList browsers = new ArrayList(); 
            browsers = service.getUniversities();
            pageContext.setAttribute("browsers", browsers);
        </jsp:scriptlet>
        <c:set var="UniversityErrors"><form:errors path="University"/></c:set>
        <div class="control-group<c:if test="${not empty UniversityErrors}"> error</c:if>">
            <label class="control-label" for="field-University">University</label>
            <div class="controls">
                <form:select path="University" id="field-University" tabindex="4">
                <form:option value='None' label="Select University"/>
                		<form:options items="${pageScope.browsers}"/>
                </form:select>
                <form:errors path="University" cssClass="help-inline" element="span"/>
            </div>
        </div>
         <c:set var="SubjectErrors"><form:errors path="Subject"/></c:set>
        <div class="control-group<c:if test="${not empty SubjectErrors}"> error</c:if>">
            <label class="control-label" for="field-Subject">Subject</label>
            <div class="controls">
                <form:select path="Subject" id="field-Subject" tabindex="4">
                		<form:option value='None' label="Select Subject"/>
                		<form:options items="${list}"/>
                </form:select>
                <form:errors path="Subject" cssClass="help-inline" element="span"/>
            </div>
        </div>
         <c:set var="CourseErrors"><form:errors path="Course"/></c:set>
        <div class="control-group<c:if test="${not empty CourseErrors}"> error</c:if>">
            <label class="control-label" for="field-Course">Course</label>
            <div class="controls">
                <form:select path="Course" id="field-Course" tabindex="4">
                		<form:option value='None' label="Select Course"/>
                		<form:options items="${list}"/>
                </form:select>
                <form:errors path="Course" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Login</button>
            <button type="button" class="btn" onclick="location.href='/Skeleton/results'">Search</button>
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

<c:import url="results.jsp"/>

<c:import url="template/footer.jsp" />
