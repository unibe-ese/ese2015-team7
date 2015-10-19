package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Tutor extends User 
{

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
}