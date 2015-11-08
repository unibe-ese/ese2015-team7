package org.sample.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.sample.controller.pojos.SignupForm.SignupValidatorGroup;

@Entity
public class Grade implements Serializable {

	/**
	 * Random serialID
	 */
	private static final long serialVersionUID = 5315512184484740433L;

	@Id
    @GeneratedValue
    private Long id;
    
    @Pattern(groups= {SignupValidatorGroup.class}, regexp = "^(?!None$).*", message = "Bitte wähle eine Universität.")
    private String university;
    
    @Pattern(groups= {SignupValidatorGroup.class}, regexp = "^(?!None$).*", message = "Bitte wähle ein Fach.")
    private String subject;
    
    @Pattern(groups= {SignupValidatorGroup.class}, regexp = "^(?!None$).*", message = "Bitte wähle ein Kurs.")
    private String course;
    
    @Size(groups= {SignupValidatorGroup.class}, min = 1, max = 1, message = "Bitte wähle eine Note.")
    private String grade;
    
    private boolean remove = false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public boolean isRemove() {
		return remove;
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	@Override
	public String toString() {
		return "Grade [university=" + university + ", subject=" + subject + ", course=" + course + ", grade=" + grade
				+ "]";
	}
}