package com.sts.dto;


import lombok.AllArgsConstructor; import lombok.Data;

public class AuthResponse { 
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthResponse [token=" + token + "]";
	} 
	}
