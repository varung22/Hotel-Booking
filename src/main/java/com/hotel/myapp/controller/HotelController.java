package com.hotel.myapp.controller;

import com.hotel.myapp.pojo.Booking;
import com.hotel.myapp.pojo.Hotel;
import com.hotel.myapp.pojo.User;
import com.hotel.myapp.service.BookingService;
import com.hotel.myapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This is a  Controller part of Myapp application. It is responsible for Administration tab in the app
 * @author Varun
 *	
 */
@Controller
public class HotelController {

	@Autowired HotelService hotelService;

	@Autowired BookingService bookingService;

	/**
	 * @param request,
	 * @return model and view of Hotels
	 * This method returns list of hotels to be displayed
	 */
	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	public ModelAndView displayHotels(HttpServletRequest request) {
		List<Hotel> hotels = hotelService.getAllHotels();
		return new ModelAndView("hotels", "hotels", hotels);
	}

	/**
	 * @return hotel add Page
	 * This method is used to add Hotels
	 */
	@RequestMapping(value = "/hoteladd", method = RequestMethod.GET)
	public String getAddHotelPage() {
		return "hoteladd";
	}

	/**
	 * @param request,
	 * @return Booking View
	 * This method returns hotel booking status 
	 * It also displays model and view of the hotel
	 */
	@RequestMapping(value = "/hotelbook/*", method = RequestMethod.GET)
	public ModelAndView getBookView(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		Hotel hotel = hotelService.getHotelById(sId);
//		request.setAttribute("hotel", hotel);
		return new ModelAndView("hotelbook", "hotel", hotel);
	}

	/**
	 * @param request,
	 * @return Deleted hotels
	 * This method is used to delete Hotels by their Id
	 * It redirects to main page
	 */
	@RequestMapping(value = "/hoteldelete/*", method = RequestMethod.GET)
	public String deleteHotel(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		hotelService.removeHotelById(sId);
		return "redirect:/hotels";
	}

	/**
	 * @param request,
	 * @return updated hotels
	 * This method is used to update the hotels by their Id
	 */
	@RequestMapping(value = "/hotelupdate/*", method = RequestMethod.GET)
	public ModelAndView updateHotelView(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		Hotel hotel = hotelService.getHotelById(sId);
		return new ModelAndView("hotelupdate", "hotel", hotel);
	}

	/**
	 * @param request,
	 * @return Hotel Location,image,price and description
	 * It generates all the info of hotels
	 * -Name of Hotel
	 * -Hotel Location
	 * -Hotel Images
	 * -Hotels Pricing
	 * -Hotel Description
	 * After that it redirects to main page
	 */
	@RequestMapping(value = "/hotelupdate/*", method = RequestMethod.POST)
	public String updateHotel(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		System.out.println(id + " from hotel update post request");
		String hotelName = request.getParameter("hotelName");
		String location = request.getParameter("location");
		String image = request.getParameter("image");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		hotelService.updateHotelById(sId, hotelName, image, location, price, description);
		return "redirect:/hotels";
	}

	/**
	 * @param request,
	 * @return User and Booking details
	 * It displays details of the user
	 * -User Firstname
	 * -User Lastname
	 * -User Phone Number
	 * -Booking Date
	 * -Booked Hotel
	 * @throws Exception
	 * 
	 */
	
	@RequestMapping(value = "/hotelbook", method = RequestMethod.POST)
	public String bookHotel(HttpServletRequest request) throws Exception {

		// GET CURRENT USER
		HttpSession session = request.getSession();
		User curUser = (User) session.getAttribute("curUser");

		// GET OTHER ORDER ATTRIBUTE FROM FORM
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String date = request.getParameter("date");
		String hotelName = request.getParameter("hotelName");
		String phone = request.getParameter("phone");

		// create new order object and store in database
		Booking booking = new Booking();
		booking.setFirstName(firstName);
		booking.setLastName(lastName);
		booking.setHotelName(hotelName);
		booking.setPhone(phone);
		booking.setDate(date);
		booking.setCurUser(curUser.getUserEmail());

		try {
			Booking o = bookingService.addOrder(booking);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/hotels";
	}

	/**
	 * @param request,
	 * @return Hotel Details
	 * It displays hotel details by Hotel Id
	 */
	@RequestMapping(value = "/hoteldetail/*", method = RequestMethod.GET)
	public ModelAndView getHotelDetail(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		Hotel hotel = hotelService.getHotelById(sId);
		request.setAttribute("hotel", hotel);
		return new ModelAndView("hoteldetail", "hotel", hotel);
	}

	/**
	 * @param request,
	 * @return Hotel Details
	 * It is used to add new Hotel and its description
	 * -Name of the hotel
	 * -Hotel location
	 * Hotel Images
	 * -Hotel Pricing
	 * Description of the Hotel
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/hoteladd", method = RequestMethod.POST)
	public String addHotel(HttpServletRequest request) throws IllegalStateException {
		String hotelName = request.getParameter("hotelName");
		String location = request.getParameter("location");
		String image = request.getParameter("image");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		Hotel hotel = new Hotel();
		hotel.setHotelName(hotelName);
		hotel.setImage(image);
		hotel.setLocation(location);
		hotel.setPrice(price);
		hotel.setDescription(description);

		try {
			hotelService.addHotel(hotel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param request,
	 * @return Hotel Model and View 
	 * It displays info about booking details 
	 * The Model and View of Booking details are displayed
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewbooking", method = RequestMethod.GET)
	public ModelAndView viewBooking(HttpServletRequest request) throws Exception {
		// GET CURRENT USER
		HttpSession session = request.getSession();
		User curUser = (User) session.getAttribute("curUser");

		// GET ALL BOOKING UNDER THIS USER
		List<Booking> bookings = bookingService.findAllOrderByUserEmail(curUser.getUserEmail());
//		request.setAttribute("bookings", bookings);
		return new ModelAndView("viewbooking", "bookings", bookings);
	}
	
	/**
	 * @param request,
	 * @return Hotel Bookings
	 * This method is used to manage bookings of the user 
	 * @throws Exception
	 */
	@RequestMapping(value = "/managebooking", method = RequestMethod.GET)
	public ModelAndView manangeBookingView(HttpServletRequest request) throws Exception {
		List<Booking> bookings = bookingService.findAllOrder();
		for(Booking booking : bookings) {
			System.out.println(booking.getHotelName());
		}
		return new ModelAndView("managebooking", "bookings", bookings);
	}
	
	/**
	 * @param request,
	 * @return Deteled hotels by Id
	 * This method is used to Delete the bookings by their Id
	 * It redirects to Main page
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancelbooking/*", method = RequestMethod.GET)
	public String deleteBooking(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		bookingService.deleteBookingById(sId);
		return "redirect:/hotels";
	}

	/**
	 * @param request,
	 * @return Searched Hotels
	 * This method is used to Search hotels by their name
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelsearch", method = RequestMethod.POST)
	public ModelAndView searchHotel(HttpServletRequest request) throws Exception {
		String searchName = request.getParameter("searchName");
		List<Hotel> hotels = hotelService.getHotelByName(searchName);
		return new ModelAndView("hotels", "hotels", hotels);
	}
}