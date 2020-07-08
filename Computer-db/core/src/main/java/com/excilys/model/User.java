package com.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;
	private String password;

	@Column(name = "user_role")
	private String role;

	public User() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(Builder builder) {
		this.id = id;
		this.username = builder.username;
		this.password = builder.password;
		this.role = builder.role;
	}

	public static class Builder {
		private long id;
		private String username;
		private String password;
		private String role;

		public static Builder newInstance(long id, String username, String password, String role) {
			return new Builder(id, username, password, role);
		}

		private Builder(long id, String username, String password, String role) {
			this.id = id;
			this.username = username;
			this.role = role;
			this.password = password;
		}

		public User build() {
			return new User(this);
		}
	}

}
