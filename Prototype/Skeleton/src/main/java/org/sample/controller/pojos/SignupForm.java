package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import org.sample.model.UserCourseFormAttributeFactory;
import org.sample.model.TimeSlot;
import org.sample.model.TimeSlotFactory;
import org.sample.model.UserCourseFormAttribute;
import org.springframework.util.AutoPopulatingList;


public class SignupForm {

	public interface SignupValidatorGroup {}

    private Long id;
    @NotNull
    @Pattern(regexp="\\w\\w+", message = "Your first name must be a word with more then two letters containing \"a-zA-Z0-9_\"")
    private String firstName;
    @NotNull
    @Pattern(regexp="\\w\\w+", message = "Your last name must be a Word with more then two letters containing \"a-zA-Z0-9_\"")
    private String lastName;
    private String biography;
    // @Valid
    private AutoPopulatingList<UserCourseFormAttribute> userCourseList = new AutoPopulatingList<UserCourseFormAttribute>(new UserCourseFormAttributeFactory());
    // @Valid
    private AutoPopulatingList<TimeSlot> timeSlots = new AutoPopulatingList<TimeSlot>(new TimeSlotFactory());
    
    @Size(groups= {SignupValidatorGroup.class, Default.class}, min = 6, max = 20, message = "Your Password did not match, please try again!")
    private String password;
    
    private String passwordVerify;


    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 
    message = "Please enter a valid E-Mail address.")
    private String email;

 

	public AutoPopulatingList<UserCourseFormAttribute> getUserCourseList() {
		return userCourseList;
	}

	public void setUserCourseList(AutoPopulatingList<UserCourseFormAttribute> userCourseList) {
		this.userCourseList = userCourseList;
	}

	public AutoPopulatingList<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(AutoPopulatingList<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getPasswordVerify() {
		return passwordVerify;
	}

	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
