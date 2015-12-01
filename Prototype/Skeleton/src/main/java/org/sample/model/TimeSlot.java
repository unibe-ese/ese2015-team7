package org.sample.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.sample.controller.pojos.SignupForm.SignupValidatorGroup;

/**
 * This entity represents the time periods a user is able to teach.
 * 
 * @author Team7
 *
 */
@Entity
public class TimeSlot implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;
    
	@Pattern(groups= {SignupValidatorGroup.class}, regexp = "^(?!None$).*", message = "Please choose a period.")
	private String semesterOrSemesterBreak;
	
	@Pattern(groups= {SignupValidatorGroup.class}, regexp = "^(?!None$).*", message = "Please choose a day.")
    private String day;
    
	@Pattern(groups= {SignupValidatorGroup.class}, regexp = "^(?!None$).*", message = "Please choose a start time.")
    private String startTime;
    
	@Pattern(groups= {SignupValidatorGroup.class}, regexp = "^(?!None$).*", message = "Please choose an end time.")
    private String endTime;
    
    private boolean remove = false;

    public void setSemesterOrSemesterBreak(String semesterOrSemesterBreak){
    	this.semesterOrSemesterBreak = semesterOrSemesterBreak;
    }
    
    public String getSemesterOrSemesterBreak(){
    	return semesterOrSemesterBreak;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	@Override
	public String toString() {
		return "TimeSlot [id=" + id + ", semesterOrSemesterBreak=" + semesterOrSemesterBreak + ", day=" + day
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
