package com.hotel.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;


/**
* This is a  Controller part of Myapp application. It is responsible for Administration tab in the app
* 
* @author Varun
*
*/

@Controller
public class HomeController {
	
	/**
     * Default mapping. This method checks if the logged in user is and Admin or not.
     * If Admin then you will see user list and can modify it. If not 
     * you are not allowed to enter and you will be warned 
     * @param locale, Provides a way to identify a user across 
     * @param model, 
     * @return Welcome
     */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "welcome";
	}
}