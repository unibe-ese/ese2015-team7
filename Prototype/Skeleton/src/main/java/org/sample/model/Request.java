package org.sample.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Request {
	
	
	public Request() {
		super();
		this.isActiv = false;
		this.isAccepted = false;
		this.isDeclined = false;
		this.isDeleted = false;
		this.newRequest = false;
		this.newAnwser = false;
	}

	@Id
    @GeneratedValue
    private Long id;
	
	@NotNull
	@ManyToOne(targetEntity=User.class)
	private User student;
	
	
	@NotNull
	@ManyToOne(targetEntity=User.class)
	private User tutor;
	
	
	//Date date;
	@NotNull
	Boolean isActiv;
	@NotNull
	Boolean isAccepted;
	@NotNull
	Boolean isDeclined;
	@NotNull
	Boolean isDeleted;
	@NotNull
	Boolean newRequest;
	@NotNull
	Boolean newAnwser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getTutor() {
		return tutor;
	}

	public void setTutor(User tutor) {
		this.tutor = tutor;
	}

	public Boolean getIsActiv() {
		return isActiv;
	}

	public void setIsActiv(Boolean isActiv) {
		this.isActiv = isActiv;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Boolean getIsDeclined() {
		return isDeclined;
	}

	public void setIsDeclined(Boolean isDeclined) {
		this.isDeclined = isDeclined;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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
