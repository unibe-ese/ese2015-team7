<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tr id="trTS${timeSlotNumber}">
	<td>
		<form:select path="signupForm.timeSlots[${timeSlotNumber}].semesterOrSemesterBreak" tabindex="1">						           	<form:option value='None' label="Select Time Period"/>
				<form:option value="Autumn semester 2015"/>
				<form:option value="Winter vacation"/>
				<form:option value="Spring semester 2016"/>
				<form:option value="Summer vacation"/>
		</form:select>
	</td>
	<td>
   		<form:select path="signupForm.timeSlots[${timeSlotNumber}].day" tabindex="1">
				<form:option value='None' label="Select Day"/>
				<form:option value="Monday"/>
				<form:option value="Tuesday"/>
				<form:option value="Wednesday"/>
	 			<form:option value="Thursday"/>
				<form:option value="Friday"/>
				<form:option value="Saturday"/>
     			<form:option value="Sunday"/>
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
	<td>
    	<button type="button" onclick="removeTSRow(${timeSlotNumber})">Remove</button>
    </td>
</tr>
<form:hidden path="signupForm.timeSlots[${timeSlotNumber}].remove" />