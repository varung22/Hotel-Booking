package com.hotel.myapp.rest;

import com.hotel.myapp.pojo.User;
import com.hotel.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {
	@Autowired
	UserService userService;

	@PostMapping("/rest/register")
	public String registerUser(HttpServletRequest request) throws IllegalStateException{
		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		User user = new User();
		user.setUserEmail(userEmail);
		user.setPassword(password);
		user.setType(type);
		try {
			User u = userService.registerUser(user);
			if(u == null) {
				request.setAttribute("getAlert", "yes");
			}
			else {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@PostMapping("/rest/login")
	public String loginUser(HttpServletRequest request) throws IllegalStateException{
		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		try {
			User user = userService.loginUser(userEmail, password, type);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("curUser", user);
				return "success";
			}else {
				request.setAttribute("getAlert", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failure";
	}
}
