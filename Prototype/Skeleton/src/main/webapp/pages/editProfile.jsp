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
	                <p class="errorMessage">
	                	Please take a look at your entries.
	                </p>
	            </c:if>
	
	        <c:set var="nameErrors"><form:errors path="firstName"/></c:set>
	        <div class="control-group<c:if test="${not empty nameErrors}"> error</c:if>">
	            <label class="control-label" for="field-name">First Name</label>
	            <div class="controls errorMessage">
	                <form:input path="firstName" id="field-fistName" tabindex="1" maxlength="35"/>
	                <form:errors path="firstName"/>
	            </div>
	        </div>
	        <c:set var="nameErrors"><form:errors path="lastName"/></c:set>
	        <div class="control-group<c:if test="${not empty nameErrors}"> error</c:if>">
	            <label class="control-label" for="field-lastName">Last Name</label>
	            <div class="controls errorMessage">
	                <form:input path="lastName" id="field-lastName" tabindex="1" maxlength="35"/>
	                <form:errors path="lastName"/>
	            </div>
	        </div>
	        
	        <div  class="errorMessage">
	        <button type="button" id="moreButton" class="btn btn-primary">Change password</button>
	        <form:errors path="password"/>
	        <c:out value="${ passwordVerifyError }">
	        </c:out></div>
	        <div id="changePassword">
		        <c:set var="passwordErrors"><form:errors path="password"/></c:set>
		        <div class="control-group<c:if test="${not empty passwordErrors}"> error</c:if>">
		            <label class="control-label" for="field-password">Password</label>
		            <div class="controls errorMessage">
		                <form:password path="password" id="field-password" tabindex="1" maxlength="35" placeholder="Password"/>
		            </div>
		        </div>
		        <c:set var="passwordVerifyErrors"><form:errors path="passwordVerify"/></c:set>
		        <div class="control-group<c:if test="${not empty passwordVerifyErrors}"> error</c:if>">
		            <label class="control-label" for="field-passwordVerify">confirm Password</label>
		            <div class="controls">
		                <form:password path="passwordVerify" id="field-passwordVerify" tabindex="1" maxlength="35" placeholder="Password"/>
		            </div>
		        </div>
	        </div>
	        <button type="button" id="lessButton" class="btn btn-primary">Cancel</button>
	        
	        <c:set var="biographyErrors"><form:errors path="biography"/></c:set>
	        <div class="control-group<c:if test="${not empty biographyErrors}"> error</c:if>">
	        
	            <label class="control-label" for="field-biography">Biography</label>
	            <div class="controls errorMessage">
	                <form:textarea path="biography" id="field-biography" tabindex="1" maxlength="250"/>
	                <form:errors path="biography" cssClass="help-inline" element="span"/>
	            </div>
	        </div>
	        <p style="text-align: center">Write a short bio about you and your skills that other students choose you as a tutor.</p>
	        
			<br>
			
			<table>
				<thead align="left">
                	<tr>
                    	<th>University</th>
                        <th>Subject</th>
                        <th>Course</th>
                        <th>Grade</th>
                        <th>Teaching</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody align="left">
                	<tr><input type="button" id="addGradeButton" value="Add Course"/></tr>
                	<c:if test="${fn:length(signupForm.userCourseList) > 0}" >
                	<c:forEach items="${signupForm.userCourseList}" var="element" varStatus="i" begin="0">
                		<form:hidden path="userCourseList[${i.index}].remove" />
                		<tr id="tr${i.index}">
							<td>
								<form:form  path="userCourseList[${i.index}].university" id="field-University${i.index}" tabindex="1">
						    		<c:out value="${element.university}" />
						    	</form:form>
							</td>
							<td>
						   		<form:form path="userCourseList[${i.index}].subject" id="field-Subject${i.index}" tabindex="1">
						    		<c:out value="${element.subject}" />
						   		</form:form>
						   	</td>
						   	<td>
						   		<form:form path="userCourseList[${i.index}].course" id="field-Course${i.index}" tabindex="1">
						    		<c:out value="${element.course}" />
						   		</form:form>
						   	</td>
						   	<td>
						   		<form:select path="userCourseList[${i.index}].grade" tabindex="2">
						   		<c:if test="${userCourseList[i.index].grade>0}">
						   			<form:option value="${userCourseList[i.index].grade}"/>
						   		</c:if>
						    		<form:option value='0' label="Select Grade"/>
					           		<form:option value="4"/>
					           		<form:option value="4.5"/>
					           		<form:option value="5"/>
					           		<form:option value="5.5"/>
					           		<form:option value="6"/>
						   		</form:select>
						   	</td>
						   	<td>
						   		<form:checkbox path="userCourseList[${i.index}].teaching" tabindex="1" />
						   	</td>
						    <td>
						    	<button type="button" onclick="removeGradeRow(${i.index})">Remove</button>
						    </td>
						</tr>
					</c:forEach>
					</c:if>
					<tr id="submitRowGrades" /> <!-- Here a new row gets attached -->
                </tbody>
			</table>
			
			<p style="text-align: center">Please select the <em>Teaching</em>-box for every course you want to tutor.</p>
			
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
                	<tr><input type="button" id="addTimeSlotButton" value="Add Time Slot" /></tr>
              		<c:if test="${fn:length(signupForm.timeSlots) > 0}" >
                	<c:forEach items="${signupForm.timeSlots}" var="element" varStatus="j" begin="0">
                		<form:hidden path="timeSlots[${j.index}].remove" />
                		<tr id="trTS${j.index}">
							<td>
								<form:select path="timeSlots[${j.index}].semesterOrSemesterBreak" tabindex="1">
						    		<form:option value='None' label="Select Time Period"/>
				           			<form:option value="Autumn semester 2015"/>
				           			<form:option value="Winter vacation"/>
				           			<form:option value="Spring semester 2016"/>
				           			<form:option value="Summer vacation"/>
						    	</form:select>
						    	<form:errors path="timeSlots[${j.index}].semesterOrSemesterBreak" class="errorMessage"/>
							</td>
							<td>
						   		<form:select path="timeSlots[${j.index}].day" tabindex="1">
						    		<form:option value='None' label="Select Day"/>
				           			<form:option value="Monday"/>
				           			<form:option value="Tuesday"/>
				           			<form:option value="Wednesday"/>
				           			<form:option value="Thursday"/>
				           			<form:option value="Friday"/>
				           			<form:option value="Saturday"/>
				           			<form:option value="Sunday"/>
						   		</form:select>
						   		<form:errors path="timeSlots[${j.index}].day" class="errorMessage"/>
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
						   		<form:errors path="timeSlots[${j.index}].startTime" class="errorMessage"/>
						   	</td>
						   	<td>
						   		<form:select path="timeSlots[${j.index}].endTime" tabindex="1">
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
						   		<form:errors path="timeSlots[${j.index}].endTime" class="errorMessage"/>
						   	</td>
						   	<td>
						    	<button type="button" onclick="removeTSRow(${j.index})">Remove</button>
						    </td>
						</tr>
					</c:forEach>
					</c:if>
					<tr id="submitRowTimeSlots" /> <!-- Here a new row gets attached -->
                </tbody>
			</table>
			
			<p style="text-align: center">Add some time slots that the students know when you are available for tutoring.</p>
			
			<button type="submit" class="btn btn-primary">Update</button>
			
	    </fieldset>
	</form:form>
	
<button type="button" class="btn-edit" onclick="location.href='/Skeleton/profile'">Cancel</button>

<c:import url="template/footer.jsp" />


<script type="text/javascript">
$(document).ready(function addGradeRowTrigger() {
	
	var countList = new Array();
	<c:forEach items="${signupForm.userCourseList}">
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
		defineOptions(gradePosition);
	});
});
</script>
<script type="text/javascript">
$(document).ready(function addTSTrigger() {
	
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
<script type="text/javascript">
// add triggers to existing rows
<c:forEach items="${signupForm.userCourseList}" var="element" varStatus="i" begin="0">
	defineOptions("${i.index}");
</c:forEach>

function defineOptions (index)
{
	$(document).on('change', '#field-University' + index, function(){
		var uni = $(this).val();

		$('#field-Subject' + index).empty();
		$('#field-Subject' + index).append("<option value="+"Select Subject"+">"+"Select Subject"+"</option>");
		$('#field-Course' + index).empty();
		$('#field-Course' + index).append("<option value="+"Select Course"+">"+"Select Course"+"</option>");
		<c:forEach items="${subjects}" var="subject">
			if("${subject.university.universityName}"==uni){
				$('#field-Subject' + index).append("<option value="+"\""+"${subject}"+"\""+">"+"${subject}"+"</option>");
			}
		</c:forEach>
	});

	$(document).on('change', '#field-Subject' + index, function(){
		var subject = $(this).val();

		$('#field-Course'  + index).empty();
		$('#field-Course' + index).append("<option value="+"Select Course"+">"+"Select Course"+"</option>");
		<c:forEach items="${courses}" var="course">
			if("${course.subject.subjectName}"==subject){
				$('#field-Course' + index).append("<option value="+"\""+"${course}"+"\""+">"+"${course}"+"</option>");
			}
		</c:forEach>
	});
}
</script>
<script>
function removeGradeRow (id)
{
	$("tr").remove("#tr" + id);
	var elem = document.getElementById("userCourseList" + id + ".remove");
	elem.value = true;
}
function removeTSRow (id)
{
	$("tr").remove("#trTS" + id);
	var elem = document.getElementById("timeSlots" + id + ".remove");
	elem.value = true;
}
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#changePassword").hide();	
	$("#lessButton").hide();

	$("#moreButton").click(function(){
		toggleChangePasswordElements();
	});
	
	$("#lessButton").click(function(){
		toggleChangePasswordElements();
	});
	
	function toggleChangePasswordElements()
	{
		$("#changePassword").toggle();
		$("#moreButton").toggle();
		$("#lessButton").toggle();
	}
});
</script>