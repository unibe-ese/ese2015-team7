<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<c:import url="template/header.jsp" />

<h2>Edit Profile</h2>
	<form:form method="POST" modelAttribute="signupForm" action="editProfile" id="editProfileForm" cssClass="form-horizontal"  autocomplete="off">
	    <fieldset>
	       <c:if test="${not empty infoMessage}">
	                <div>${infoMessage}</div>
	            </c:if>
	
	        <c:set var="nameErrors"><form:errors path="name"/></c:set>
	        <div class="control-group<c:if test="${not empty nameErrors}"> error</c:if>">
	            <label class="control-label" for="field-name">Name</label>
	            <div class="controls">
	                <form:input path="name" id="field-name" tabindex="1" maxlength="35"/>
	                <form:errors path="name" cssClass="help-inline" element="span"/>
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
	        
	        <c:set var="passwordVerifyErrors"><form:errors path="passwordVerify"/></c:set>
	        <div class="control-group<c:if test="${not empty passwordVerifyErrors}"> error</c:if>">
	        
	            <label class="control-label" for="field-passwordVerify">confirm Password</label>
	            <div class="controls">
	                <form:password path="passwordVerify" id="field-passwordVerify" tabindex="1" maxlength="35" placeholder="Password"/>
	                <form:errors path="passwordVerify" cssClass="help-inline" element="span"/>
	            </div>
	        </div>
	        <c:set var="biographyErrors"><form:errors path="biography"/></c:set>
	        <div class="control-group<c:if test="${not empty biographyErrors}"> error</c:if>">
	        
	            <label class="control-label" for="field-biography">Biography</label>
	            <div class="controls">
	                <form:textarea path="biography" id="field-biography" tabindex="1" maxlength="250"/>
	                <form:errors path="biography" cssClass="help-inline" element="span"/>
	            </div>
	        </div>
	        
			<br>
			
			<table>
				<thead>
                	<tr>
                    	<th>University</th>
                        <th>Subject</th>
                        <th>Course</th>
                        <th>Grade</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<tr><input type="button" id="addGradeButton" value="Add" /></tr>
                	<tr>
							<c:if test="${fn:length(signupForm.grades) == 0}" >
	                            <td>
	                            	<form:select path="grades[0].university" tabindex="1">
						           		<form:option value='None' label="Select University"/>
						           		<form:options items="${universities}" itemValue="universityName"/>
				            		</form:select>
				            	</td>
				            	<td>
				            		<form:select path="grades[0].subject" tabindex="1">
						           		<form:option value='None' label="Select Subject"/>
						           		<form:options items="${subjects}" itemValue="subjectName"/>
				            		</form:select>
				            	</td>
				            	<td>
				            		<form:select path="grades[0].course" tabindex="1">
						           		<form:option value='None' label="Select Course"/>
						           		<form:options items="${courses}" itemValue="courseName"/>
				            		</form:select>
				            	</td>
				            	<td>
				            		<form:select path="grades[0].grade" tabindex="1">
						           		<form:option value='None' label="Select Grade"/>
						           		<form:option value="1"/>
						           		<form:option value="2"/>
						           		<form:option value="3"/>
						           		<form:option value="4"/>
						           		<form:option value="5"/>
						           		<form:option value="6"/>
				            		</form:select>
				            	</td>
				            		<!-- Remove button missing -->
		                	</c:if>
		                	<c:if test="${fn:length(signupForm.grades) > 0}" >
		                	<c:forEach items="${signupForm.grades}" var="element" varStatus="i" begin="0">
		                		<tr>
									<td>
										<form:select path="grades[${i.index}].university" tabindex="1">
								    		<form:option value='None' label="Select University"/>
								    		<form:options items="${universities}" itemValue="universityName"/>
								    	</form:select>
									</td>
									<td>
								   		<form:select path="grades[${i.index}].subject" tabindex="1">
								    		<form:option value='None' label="Select Subject"/>
								    		<form:options items="${subjects}" itemValue="subjectName"/>
								   		</form:select>
								   	</td>
								   	<td>
								   		<form:select path="grades[${i.index}].course" tabindex="1">
								    		<form:option value='None' label="Select Course"/>
								    		<form:options items="${courses}" itemValue="courseName"/>
								   		</form:select>
								   	</td>
								   	<td>
								   		<form:select path="grades[${i.index}].grade" tabindex="1">
								    		<form:option value='None' label="Select Grade"/>
								    		<form:option value="1"/>
								    		<form:option value="2"/>
								    		<form:option value="3"/>
								    		<form:option value="4"/>
								    		<form:option value="5"/>
								    		<form:option value="6"/>
								   		</form:select>
								   	</td>
								   		<!-- Remove button missing -->
								</tr>
							</c:forEach>
							</c:if>
					</tr>
					<tr id="submitRow" /> <!-- Hier wird neue Zeile eingefügt -->
                </tbody>
			</table>
	        
			<button type="submit" class="btn btn-primary">Update</button>
	    </fieldset>
	</form:form>
	
<button type="button" class="btn-edit" onclick="location.href='/Skeleton/profile'">Cancel</button>

<c:import url="template/footer.jsp" />


<script type="text/javascript">
$(document).ready(function() {
	
	var countList = new Array();
	<c:forEach items="${signupForm.grades}">
	    var dummy= new Object();
	    countList.push(dummy);
	</c:forEach>
	
	var gradePosition = countList.length - 1;
	
	$("#addGradeButton").click(function() {
		gradePosition++;
 
		$.get("<%=request.getContextPath()%>/editProfileAppend", { fieldId: gradePosition},
			function(data){
				$("#submitRow").before(data);
		});
	});
});
</script>