package com.hotel.myapp.service;

import com.hotel.myapp.dao.UserDao;
import com.hotel.myapp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This Service part of Myapp is responsible for implementing the Business Logic
 *  @author Pradeep
 *
 */
@Service
public class UserService {
	@Autowired UserDao userDao;
	/**
	 * @param u
	 * @return Registered User
	 * @throws Exception
	 */
	public User registerUser(User u) throws Exception{
		return userDao.registerUser(u);
	}
	
	/**
	 * @param userEmail
	 * @param password
	 * @param type
	 * @return Login Details of the User from Dao
	 */
	public User loginUser(String userEmail, String password, String type) {
		return userDao.loginUser(userEmail, password, type);
	}
	
	/**
	 * @param userEmail
	 * @param password
	 * @param type
	 * @return Login Details given by User with User Email and Password
	 */
	public User loginUserByUsername(String userEmail, String password, String type) {
		return userDao.loginUserByUsername(userEmail, password, type);
	}
}