<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tr>
	<td>
		<form:select path="signupForm.grades[${gradeNumber}].university" tabindex="1">
    		<form:option value='None' label="Select University"/>
    		<form:options items="${universities}" itemValue="universityName"/>
    	</form:select>
	</td>
	<td>
   		<form:select path="signupForm.grades[${gradeNumber}].subject" tabindex="1">
    		<form:option value='None' label="Select Subject"/>
    		<form:options items="${subjects}" itemValue="subjectName"/>
   		</form:select>
   	</td>
   	<td>
   		<form:select path="signupForm.grades[${gradeNumber}].course" tabindex="1">
    		<form:option value='None' label="Select Course"/>
    		<form:options items="${courses}" itemValue="courseName"/>
   		</form:select>
   	</td>
   	<td>
   		<form:select path="signupForm.grades[${gradeNumber}].grade" tabindex="1">
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