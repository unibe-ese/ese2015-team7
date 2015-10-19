<%@page import="org.sample.controller.service.SampleServiceImpl"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.sample.model.University" %>
<%@page import="org.sample.controller.pojos.SearchForm" %>



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

<form:form method="post" modelAttribute="searchForm" action="createSearch" id="searchForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
		<jsp:scriptlet>
			SampleServiceImpl service = new SampleServiceImpl();
            ArrayList tutors = new ArrayList(); 
            tutors = service.getTutorsFromSearchForm(searchForm);
            pageContext.setAttribute("tutors", tutors);
        </jsp:scriptlet>
        <c:set var="UniversityErrors"><form:errors path="University"/></c:set>
        <div class="control-group<c:if test="${not empty UniversityErrors}"> error</c:if>">
            <label class="control-label" for="field-University">University</label>
            <div class="controls">
                <form:select path="University" id="field-University" tabindex="4">
                <form:option value='None' label="Select University"/>
                		<form:options items="${pageScope.browsers}"/>
                		<!-- 

    </fieldset>
</form:form>



	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>

