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
    

	public String getTutorsName() {
		return tutorsName;
	}


	public void setTutorsName(String tutorsName) {
		this.tutorsName = tutorsName;
	}


	public String toString(){
		return tutorsName;
	}
	
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


}