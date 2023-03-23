package com.switchfully.digibooky.dto.user;

public class UsernamePassword {
	private final String username;
	private final String password;

	public UsernamePassword(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
