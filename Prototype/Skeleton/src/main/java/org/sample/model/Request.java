package org.sample.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Request {
	
	
	@Id
    @GeneratedValue
    private Long id;
	
	@NotNull
	String studentEmail;
	@NotNull
	String tutorEmail;
	
	
	Date date;
	
	Boolean isActiv;
	
	Boolean isAccepted;
	
	Boolean isDeclined;
	
	Boolean isDeleted;

	Boolean newRequest;
	
	Boolean newAnwser;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public Boolean getIsActiv() {
		return isActiv;
	}

	public void setIsActiv(Boolean isActiv) {
		this.isActiv = isActiv;
	}



	public Boolean getIsDeclined() {
		return isDeclined;
	}

	public void setIsDeclined(Boolean isDeclined) {
		this.isDeclined = isDeclined;
	}



	public String getTutorEmail() {
		return tutorEmail;
	}

	public void setTutorEmail(String tutorEmail) {
		this.tutorEmail = tutorEmail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getAccepted() {
		return isAccepted;
	}

	public void setAccepted(Boolean accepted) {
		this.isAccepted = accepted;
	}



	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		this.isDeleted = deleted;
	}

	public Boolean getNewRequest() {
		return newRequest;
	}

	public void setNewRequest(Boolean newRequest) {
		this.newRequest = newRequest;
	}

	public Boolean getNewAnwser() {
		return newAnwser;
	}

	public void setNewAnwser(Boolean newAnwser) {
		this.newAnwser = newAnwser;
	}

	
	

}
