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
                	<tr><input type="button" id="addGradeButton" value="Add Grade Row" /></tr>
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
					<tr id="submitRowGrades" /> <!-- Hier wird neue Zeile eingefügt -->
                </tbody>
			</table>
	        
			<button type="submit" class="btn btn-primary">Update</button>
			
			<br>
			<br>
			
			<table>
				<thead>
                	<tr>
                    	<th>Period</th>
                        <th>Day</th>
                        <th>From</th>
                        <th>Till</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<tr><input type="button" id="addTimeSlotButton" value="Add Time Slot Row" /></tr>
                	<tr>
							<c:if test="${fn:length(signupForm.timeSlots) == 0}" >
	                            <td>
	                            	<form:select path="timeSlots[0].semesterOrSemesterBreak" tabindex="1">
						           		<form:option value='None' label="Select Time Period"/>
						           		<form:option value="Herbstsemester 2015"/>
						           		<form:option value="Semesterferien im Winter"/>
						           		<form:option value="Frühjahrssemester 2016"/>
						           		<form:option value="Semesterferien im Sommer"/>
				            		</form:select>
				            	</td>
				            	<td>
				            		<form:select path="timeSlots[0].day" tabindex="1">
						           		<form:option value='None' label="Select Day"/>
						           		<form:option value="Montag"/>
						           		<form:option value="Dienstag"/>
						           		<form:option value="Mittwoch"/>
						           		<form:option value="Donnerstag"/>
						           		<form:option value="Freitag"/>
						           		<form:option value="Samstag"/>
						           		<form:option value="Sonntag"/>
				            		</form:select>
				            	</td>
				            	<td>
				            		<form:select path="timeSlots[0].startTime" tabindex="1">
						           		<form:option value='None' label="Select Start Time"/>
						           		<form:option value="07:00"/>
						           		<form:option value="08:00"/>
						           		<form:option value="09:00"/>
						           		<form:option value="10:00"/>
						           		<form:option value="11:00"/>
						           		<form:option value="12:00"/>
						           		<form:option value="13:00"/>
						           		<form:option value="14:00"/>
						           		<form:option value="15:00"/>
						           		<form:option value="16:00"/>
						           		<form:option value="17:00"/>
						           		<form:option value="18:00"/>
						           		<form:option value="19:00"/>
						           		<form:option value="20:00"/>
						           		<form:option value="21:00"/>
						           		<form:option value="22:00"/>
				            		</form:select>
				            	</td>
				            	<td>
				            		<form:select path="timeSlots[0].endTime" tabindex="1">
						           		<form:option value='None' label="Select End Time"/>
						           		<form:option value="08:00"/>
						           		<form:option value="09:00"/>
						           		<form:option value="10:00"/>
						           		<form:option value="11:00"/>
						           		<form:option value="12:00"/>
						           		<form:option value="13:00"/>
						           		<form:option value="14:00"/>
						           		<form:option value="15:00"/>
						           		<form:option value="16:00"/>
						           		<form:option value="17:00"/>
						           		<form:option value="18:00"/>
						           		<form:option value="19:00"/>
						           		<form:option value="20:00"/>
						           		<form:option value="21:00"/>
						           		<form:option value="22:00"/>
						           		<form:option value="22:00"/>
				            		</form:select>
				            	</td>
				            		<!-- Remove button missing -->
		                	</c:if>
		                	<c:if test="${fn:length(signupForm.timeSlots) > 0}" >
		                	<c:forEach items="${signupForm.timeSlots}" var="element" varStatus="j" begin="0">
		                		<tr>
									<td>
										<form:select path="timeSlots[${j.index}].semesterOrSemesterBreak" tabindex="1">
								    		<form:option value='None' label="Select Time Period"/>
						           			<form:option value="Herbstsemester 2015"/>
						           			<form:option value="Semesterferien im Winter"/>
						           			<form:option value="Frühjahrssemester 2016"/>
						           			<form:option value="Semesterferien im Sommer"/>
								    	</form:select>
									</td>
									<td>
								   		<form:select path="timeSlots[${j.index}].day" tabindex="1">
								    		<form:option value='None' label="Select Day"/>
						           			<form:option value="Montag"/>
						           			<form:option value="Dienstag"/>
						           			<form:option value="Mittwoch"/>
						           			<form:option value="Donnerstag"/>
						           			<form:option value="Freitag"/>
						           			<form:option value="Samstag"/>
						           			<form:option value="Sonntag"/>
								   		</form:select>
								   	</td>
								   	<td>
								   		<form:select path="timeSlots[${j.index}].startTime" tabindex="1">
								    		<form:option value='None' label="Select Start Time"/>
						          	 		<form:option value="07:00"/>
						        	   		<form:option value="08:00"/>
						         	  		<form:option value="09:00"/>
							           		<form:option value="10:00"/>
							           		<form:option value="11:00"/>
							           		<form:option value="12:00"/>
							           		<form:option value="13:00"/>
							           		<form:option value="14:00"/>
							           		<form:option value="15:00"/>
							           		<form:option value="16:00"/>
							           		<form:option value="17:00"/>
							           		<form:option value="18:00"/>
							           		<form:option value="19:00"/>
							           		<form:option value="20:00"/>
							           		<form:option value="21:00"/>
							           		<form:option value="22:00"/>
								   		</form:select>
								   	</td>
								   	<td>
								   		<form:select path="timeSlots[${j.index}].endTime" tabindex="1">
								    		<form:option value="08:00"/>
							           		<form:option value="09:00"/>
							           		<form:option value="10:00"/>
							           		<form:option value="11:00"/>
							           		<form:option value="12:00"/>
							           		<form:option value="13:00"/>
							           		<form:option value="14:00"/>
							           		<form:option value="15:00"/>
							           		<form:option value="16:00"/>
							           		<form:option value="17:00"/>
							           		<form:option value="18:00"/>
							           		<form:option value="19:00"/>
							           		<form:option value="20:00"/>
							           		<form:option value="21:00"/>
							           		<form:option value="22:00"/>
							           		<form:option value="22:00"/>
								   		</form:select>
								   	</td>
								   		<!-- Remove button missing -->
								</tr>
							</c:forEach>
							</c:if>
					</tr>
					<tr id="submitRowTimeSlots" /> <!-- Hier wird neue Zeile eingefügt -->
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
				$("#submitRowGrades").before(data);
		});
	});
});
</script>
<script type="text/javascript">
$(document).ready(function addTS() {
	
	var countTimeSlotsList = new Array();
	<c:forEach items="${signupForm.timeSlots}">
	    var thickie= new Object();
	    countTimeSlotsList.push(thickie);
	</c:forEach>
	
	var timeSlotPosition = countTimeSlotsList.length - 1;
	
	$("#addTimeSlotButton").click(function tSP() {
		timeSlotPosition++;
 
		$.get("<%=request.getContextPath()%>/editProfileAttach", { fieldId: timeSlotPosition},
			function oneRowMore(data){
				$("#submitRowTimeSlots").before(data);
		});
	});
});
</script>