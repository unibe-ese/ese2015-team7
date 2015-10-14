package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;


public class LoginForm {


    private Long id;
    @NotNull
    private String name;
    private String password;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}