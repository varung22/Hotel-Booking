package com.hotel.myapp.service;

import com.hotel.myapp.dao.UserDao;
import com.hotel.myapp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired UserDao userDao;
	public User registerUser(User u) throws Exception{
		return userDao.registerUser(u);
	}
	
	public User loginUser(String userEmail, String password, String type) {
		return userDao.loginUser(userEmail, password, type);
	}
}
