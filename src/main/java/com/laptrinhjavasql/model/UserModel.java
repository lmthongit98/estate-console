package com.laptrinhjavasql.model;

public class UserModel {
	private Long id;
	private String username;
	private String fullName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"id=" + id +
				", username='" + username + '\'' +
				", fullName='" + fullName + '\'' +
				'}';
	}
}
