package com.hotel.myapp.controller;

import com.hotel.myapp.pojo.User;
import com.hotel.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This is a  Controller part of Myapp application. It is responsible for Administration tab in the app
 * @author Varun
 */
@Controller
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String toRegisterView()
	{
		return "register";
	}
	/**
	 * @param request,
	 * @return User Email and Password
	 * This Method displays info about User registered details
	 * -User's Email
	 * -User's Password
	 * -If username is null then it shows get alert pop up message
	 * -After registering it redirects to login page
	 * @throws IllegalStateException
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
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
				return "redirect:/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return Login page
	 * This method is used to display Login page
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String toLoginView() {
		return "login";
	}
	
	/**
	 * @param request,
	 * @return User login validation
	 * This method is used to display Login page
	 * -User's Email
	 * -User's Password
	 * -If Login details matches it redirects to hotels
	 * @throws IllegalStateException
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginUser(HttpServletRequest request) throws IllegalStateException{
		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		try {
			User user = userService.loginUser(userEmail, password, type);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("curUser", user);
				return "redirect:/hotels";
			}else {
				request.setAttribute("getAlert", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param request,
	 * @return Logout page
	 * This method redirects to Hotel Main Page
	 * @throws IllegalStateException
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest request) throws IllegalStateException{
		HttpSession session = request.getSession(false);
		session.removeAttribute("curUser");
		return "redirect:/hotels";
		
	}
}