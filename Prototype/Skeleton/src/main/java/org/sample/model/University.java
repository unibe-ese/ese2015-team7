package org.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class University {

    @Id
    @GeneratedValue
    private Long UId;

    @NotNull
    @Column(unique=true)
    private String universityName;
    private String city;
    private String country;
    @ManyToOne
    private Address address; 
    

    public Long getUId() {
        return UId;
    }

    public void setUId(Long UId) {
        this.UId = UId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString(){
		return universityName;
	}
	
	public boolean equals(University otherUni){
		return (this.UId==otherUni.UId);
	}
}