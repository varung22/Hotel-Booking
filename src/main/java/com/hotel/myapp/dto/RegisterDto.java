package com.hotel.myapp.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *  * This interface models a Data Transfer Object for Register pojo

 * @author Priyanka
 *
 */
public class RegisterDto {

	
	@NotNull
	private String username;

	@NotNull
	@Email
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	@NotNull
	private String password;
	
	private String confirmPassword;
	
	@Size(min = 10, max = 10)
	@Pattern(regexp="[\\d]{6}")
	private String phoneNumber;
	
	
	public RegisterDto() {
		super();
	}
	/**
	 * @return User name
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
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Firstname
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Last Name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	/**
	 * @return Confirm password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	/**
	 * @return Phone Number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return Validation for password
	 */
	@AssertTrue()
	private boolean isValid() {
		return this.password.equals(this.confirmPassword);
	}
}