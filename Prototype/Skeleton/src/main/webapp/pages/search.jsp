<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:import url="template/header.jsp" />



</head>
<body>

<h1>Search for a Tutor</h1>

<form:form method="post" modelAttribute="searchForm" action="results" id="searchForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
    
        <c:set var="UniversityErrors"><form:errors path="University"/></c:set>
        <div class="control-group<c:if test="${not empty UniversityErrors}"> error</c:if>">
            <label class="control-label" for="field-University">University</label>
            <div class="controls">
                <form:select path="University" id="field-University" tabindex="1">
                <form:option value='None' label="Select University"/>
                		<form:options items="${universities}"/>
                </form:select>
                <form:errors path="University" cssClass="help-inline" element="span"/>
            </div>
        </div>
       <!-- <div class="form-actions">
            <button type="button" class="btn" onclick="location.href='/Skeleton/searchSubjects'">Search University Subjects</button>
        </div>-->
         <c:set var="SubjectErrors"><form:errors path="Subject"/></c:set>
        <div class="control-group<c:if test="${not empty SubjectErrors}"> error</c:if>">
            <label class="control-label" for="field-Subject">Subject</label>
            <div class="controls">
                <form:select path="Subject" id="field-Subject" tabindex="2">
                		<form:option value='None' label="Select Subject"/>
                </form:select>
                <form:errors path="Subject" cssClass="help-inline" element="span"/>
            </div>
        </div>
         <c:set var="CourseErrors"><form:errors path="Course"/></c:set>
        <div class="control-group<c:if test="${not empty CourseErrors}"> error</c:if>">
            <label class="control-label" for="field-Course">Course</label>
            <div class="controls">
                <form:select path="Course" id="field-Course" tabindex="3">
                		<form:option value='None' label="Select Course"/>
                </form:select>
                <form:errors path="Course" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Search</button> 
        </div>
    </fieldset>
</form:form>



<script type="text/javascript">
$("#field-University").change(function(){
	var uni = $(this).val();	

	$('#field-Subject').empty();
	$('#field-Subject').append("<option value="+"Select Subject"+">"+"Select Subject"+"</option>");
	$('#field-Course').empty();
	$('#field-Course').append("<option value="+"Select Course"+">"+"Select Course"+"</option>");
	<c:forEach items="${subjects}" var="subject">
		if("${subject.university.universityName}"==uni){
			$('#field-Subject').append("<option value="+"${subject}"+">"+"${subject}"+"</option>");
		}
	</c:forEach>
});
</script>

<script type="text/javascript">
$("#field-Subject").change(function(){
	var subject = $(this).val();

	$('#field-Course').empty();
	$('#field-Course').append("<option value="+"Select Course"+">"+"Select Course"+"</option>");
	<c:forEach items="${courses}" var="course">
		if("${course.subject.subjectName}"==subject.replace(" ","")){
			$('#field-Course').append("<option value="+"${course}"+">"+"${course}"+"</option>");
		}
	</c:forEach>
});
</script>




	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
