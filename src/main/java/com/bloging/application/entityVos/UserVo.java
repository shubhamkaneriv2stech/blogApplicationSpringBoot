package com.bloging.application.entityVos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserVo {

	private Integer id;

	@NotEmpty
	@Size(min = 4, message = "User Name Must be min 4 ")
	private String name;

	@NotEmpty(message = " Email must not be empty")
	@Email(message = "Not Valid email")
	private String email;

	@NotEmpty(message = "Password is required")
	@Size(min = 8, max = 16, message = " Password must be 8 to 16 character")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", message = "password must contain 1 number (0-9)\n"
			+ "password must contain 1 uppercase letters\n" + "password must contain 1 lowercase letters\n"
			+ "password must contain 1 non-alpha numeric number\n" + "password is 8-16 characters with no space")
	private String password;

	@NotEmpty(message = " about is required")
	private String about;
	
	private Set<RolesVo> roles = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	 @JsonIgnore
	public String getPassword() {
		return password;
	}

	 @JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<RolesVo> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolesVo> roles) {
		this.roles = roles;
	}


}
