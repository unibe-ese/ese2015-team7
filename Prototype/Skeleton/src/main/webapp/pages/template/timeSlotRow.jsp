<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tr>
	<td>
		<form:select path="signupForm.timeSlots[${timeSlotNumber}].semesterOrSemesterBreak" tabindex="1">						           	<form:option value='None' label="Select Time Period"/>
				<form:option value="Herbstsemester 2015"/>
				<form:option value="Semesterferien im Winter"/>
				<form:option value="Fr�hjahrssemester 2016"/>
				<form:option value="Semesterferien im Sommer"/>
		</form:select>
	</td>
	<td>
   		<form:select path="signupForm.timeSlots[${timeSlotNumber}].day" tabindex="1">
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
   		<form:select path="signupForm.timeSlots[${timeSlotNumber}].startTime" tabindex="1">
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
   		<form:select path="signupForm.timeSlots[${timeSlotNumber}].endTime" tabindex="1">
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
</tr>
