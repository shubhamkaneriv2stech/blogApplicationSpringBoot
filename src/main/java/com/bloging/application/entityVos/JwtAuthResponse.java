package com.bloging.application.entityVos;

public class JwtAuthResponse {

	private String token;
	
	private UserVo user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}
	
	
}

