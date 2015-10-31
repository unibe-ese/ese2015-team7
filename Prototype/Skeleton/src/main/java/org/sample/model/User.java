package org.sample.model;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



import org.hibernate.annotations.NaturalId;


@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    private String biography;
    @ElementCollection
    private List<String> grades;
    @ElementCollection
    private List<String> timeSlots;

    @NaturalId(mutable=false)
    @Column(name="EMAIL", unique = true, nullable = false, length = 111)
    private String email;
    private String password;
    private boolean enabled;
    
    @ElementCollection
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRole;
    
    public boolean isEnabled() {
		return enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public List<String> getGrades() {
		return grades;
	}

	public void setGrades(List<String> grades) {
		this.grades = grades;
	}

	public List<String> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<String> timeSlots) {
		this.timeSlots = timeSlots;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
