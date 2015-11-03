package org.sample.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TimeSlot implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;
    
	@NotNull
	@Size(min = 1, max = 30, message = "Bitte wähle einen Zeitperiode.")
	private String semesterOrSemesterBreak;
	
    @NotNull
    @Size(min = 1, max = 20, message = "Bitte wähle einen Wochentag.")
    private String day;
    
    @NotNull
    @Size(min = 1, max = 8, message = "Bitte wähle eine Startzeit.")
    private String startTime;
    
    @NotNull
    @Size(min = 1, max = 8, message = "Bitte wähle eine Endzeit.")
    private String endTime;

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

	@Override
	public String toString() {
		return "TimeSlot [id=" + id + ", semesterOrSemesterBreak=" + semesterOrSemesterBreak + ", day=" + day
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
