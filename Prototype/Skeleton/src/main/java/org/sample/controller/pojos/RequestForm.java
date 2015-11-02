package org.sample.controller.pojos;



import org.sample.model.User;

public class RequestForm {
	
    private Long id;
	private User student;
	private User tutor;
	//Date date;
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
