package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Tutor
{
    @Id
    @GeneratedValue
    private Long id;

	@NotNull
    private String tutorsName;

    @OneToOne(targetEntity=User.class)
	private User user;
    
    @NotNull
    @ManyToOne(targetEntity=Course.class)  
    private Course course;
    
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getTutorsName() {
		return tutorsName;
	}


	public void setTutorsName(String tutorsName) {
		this.tutorsName = tutorsName;
	}


    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String toString(){
		return tutorsName;
	}
	

}