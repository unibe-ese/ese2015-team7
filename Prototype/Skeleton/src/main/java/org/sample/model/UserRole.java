package org.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>This entity represents a user role.</p>
 * Example: <ul><li>ROLE_USER</li> <li>ROLE_ADMIN</li></ul>
 * 
 * @author Team7
 *
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }
}
