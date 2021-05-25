package com.hotel.myapp.dto;

/**
 *  * This interface models a Data Transfer Object for Hotel pojo

 * @author Priyanka
 *
 */
public class LoginDto {

	
	private String username;

	private String password;

		public LoginDto() {
		super();
	}

	/**
	 * @return User Name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}