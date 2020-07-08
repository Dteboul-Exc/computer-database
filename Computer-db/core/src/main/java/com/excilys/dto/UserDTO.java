package com.excilys.dto;

public class UserDTO {
	private String id;

	private String username;
	private String password;
	private String role;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	public UserDTO(Builder builder)
	{
		this.id = id;
		this.username = builder.username;
		this.password = builder.password;
		this.role = builder.role;
	}
	public static class Builder
	{
		private String id;
		private String username;
		private String password;
		private String role;
        public static Builder newInstance(String id,String username,String password,String role ) 
        { 
            return new Builder(id,username,password,role); 
        } 
        private Builder(String id,String username,String password,String role) {
        	this.id = id;
        	this.username = username;
        	this.role = role;
        	this.password = password;
        }

		public UserDTO build()
		{
			return new UserDTO(this);
		}
	}
}
