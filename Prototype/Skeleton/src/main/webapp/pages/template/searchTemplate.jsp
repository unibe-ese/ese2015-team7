<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<h1>Search for a Tutor</h1>

<form:form method="post" modelAttribute="searchForm" action="results" id="searchForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
        <c:set var="UniversityErrors"><form:errors path="University"/></c:set>
        <div class="control-group<c:if test="${not empty UniversityErrors}"> error</c:if>">
            <label class="control-label" for="field-University">University</label>
            <div class="controls">
                <form:select path="University" id="field-University" tabindex="1">
                <form:option value='Select University' label="Select University"/>
                		<form:options items="${universities}"/>
                </form:select>
                <form:errors path="University" cssClass="help-inline" element="span"/>
            </div>
        </div>
         <c:set var="SubjectErrors"><form:errors path="Subject"/></c:set>
        <div class="control-group<c:if test="${not empty SubjectErrors}"> error</c:if>">
            <label class="control-label" for="field-Subject">Subject</label>
            <div class="controls">
                <form:select path="Subject" id="field-Subject" tabindex="1">
                <form:option value='Select Subject' label="Select Subject"/>
                </form:select>
                <form:errors path="Subject" cssClass="help-inline" element="span"/>
            </div>
        </div>
         <c:set var="CourseErrors"><form:errors path="Course"/></c:set>
        <div class="control-group<c:if test="${not empty CourseErrors}"> error</c:if>">
            <label class="control-label" for="field-Course">Course</label>
            <div class="controls">
                <form:select path="Course" id="field-Course" tabindex="1">
                		<form:option value='Select Course' label="Select Course"/>
                </form:select>
                <form:errors path="Course" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <button type="button" id="moreButton" class="btn btn-primary">More Criteria</button>
        <c:set var="GradeErrors"><form:errors path="Grade"/></c:set>
        <div id="grades" class="control-group<c:if test="${not empty GradeErrors}"> error</c:if>">
            <label class="control-label" for="field-Grade">Grade</label>
            <div class="controls">
                <form:select path="grade" id="field-Grade" tabindex="1">
				           		<form:option value='0' label="Select Grade"/>
				           		<form:option value="4"/>
				           		<form:option value="5"/>
				           		<form:option value="6"/>
                </form:select>
                <form:errors path="Grade" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <button type="button" id="lessButton" class="btn btn-primary">Less Criteria</button>
        
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Search</button> 
        </div>
    </fieldset>
</form:form>



<script type="text/javascript">
$(document).ready(function(){
	$("#grades").hide();	
	$("#lessButton").hide();

	$("#moreButton").click(function(){
		$("#grades").toggle();
		$("#moreButton").toggle();
		$("#lessButton").toggle();
	});
	
	$("#lessButton").click(function(){
		$("#grades").toggle();
		$("#moreButton").toggle();
		$("#lessButton").toggle();
	});
});

$("#field-University").change(function(){
	var uni = $(this).val();

	$('#field-Subject').empty();
	$('#field-Subject').append("<option value="+"\"Select Subject\""+">"+"Select Subject"+"</option>");
	$('#field-Course').empty();
	$('#field-Course').append("<option value="+"\"Select Course\""+">"+"Select Course"+"</option>");
	<c:forEach items="${subjects}" var="subject">
		if("${subject.university.universityName}"==uni){
			$('#field-Subject').append("<option value="+"\""+"${subject}"+"\""+">"+"${subject}"+"</option>");
		}
	</c:forEach>
});

$("#field-Subject").change(function(){
	var subject = $(this).val();

	$('#field-Course').empty();
	$('#field-Course').append("<option value="+"\"Select Course\""+">"+"Select Course"+"</option>");
	<c:forEach items="${courses}" var="course">
		if("${course.subject.subjectName}"==subject){
			$('#field-Course').append("<option value="+"\""+"${course}"+"\""+">"+"${course}"+"</option>");
		}
	</c:forEach>
});
</script>