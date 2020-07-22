package com.excilys.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class UserDB implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;
	private String password;

	@Column(name = "user_role")
	private String role;

	public UserDB() {

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

	public UserDB(Builder builder) {
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

		public UserDB build() {
			return new UserDB(this);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(role.split(",")).stream().map(a -> (GrantedAuthority) () -> a)
                .collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
