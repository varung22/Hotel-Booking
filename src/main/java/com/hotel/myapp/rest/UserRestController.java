package com.hotel.myapp.rest;

import com.hotel.myapp.dto.AuthResponse;
import com.hotel.myapp.dto.LoginDto;
import com.hotel.myapp.dto.RegisterDto;
import com.hotel.myapp.pojo.User;
import com.hotel.myapp.service.TokenService;
import com.hotel.myapp.service.UserService;
import com.hotel.myapp.utils.GenerateToken;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a Rest Controller part of Myapp application. It is responsible for Administration tab in the app
* 
* @author Varun
 */
@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {

	@Autowired
	UserService userService;

	@Autowired
	TokenService tokenService;

	/**
	 * @param dto
	 * @return User Registered Details
	 * -User First name and last name
	 * -User Email
	 * -User Password
	 * -User Phone number
	 */
	@PostMapping("/rest/register")
	public ResponseEntity<Boolean> registerUser(@Valid @RequestBody RegisterDto dto) {

		User user = new User();
		user.setUsername(dto.getUsername());
		user.setUserEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPassword(dto.getPassword());
		user.setPhoneNumber(dto.getPhoneNumber());
		user.setType("User");
		try {
			User u = userService.registerUser(user);
			if (u != null) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param adminDetail
	 * @return Login details of the User
	 */ 
	@PostMapping("/rest/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginDto adminDetail) {
		AuthResponse response = new AuthResponse();

		User user = userService.loginUserByUsername(adminDetail.getUsername(), adminDetail.getPassword(), "User");

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		/*
		 * If User is authenticated then Do Authorization Task.
		 */
		String tokenData[] = GenerateToken.createJWT(adminDetail.getUsername(), "JavaTpoint", "JWT Token", "User",
				43200000);

		// get Token.
		String token = tokenData[0];

		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setPhone(user.getPhoneNumber());
		response.setEmail(user.getUserEmail());
		response.setToken(token);

		// Check if token is already exist.
		long isUserEmailExists = tokenService.getTokenDetail(adminDetail.getUsername());

		/*
		 * If token exist then update Token else create and insert the token.
		 */
		if (isUserEmailExists > 0) {
			tokenService.updateToken(adminDetail.getUsername(), token, tokenData[1]);
		} else {
			tokenService.saveUserEmail(adminDetail.getUsername(), user.getId(), token, tokenData[1]);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}