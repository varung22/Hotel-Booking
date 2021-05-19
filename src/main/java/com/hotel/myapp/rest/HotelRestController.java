package com.hotel.myapp.rest;

import com.hotel.myapp.pojo.Booking;
import com.hotel.myapp.pojo.Hotel;
import com.hotel.myapp.pojo.User;
import com.hotel.myapp.service.BookingService;
import com.hotel.myapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelRestController {

	@Autowired HotelService hotelService;

	@Autowired BookingService bookingService;

	@GetMapping(value = "/rest/hotels")
	public List<Hotel> displayHotels(HttpServletRequest request) {
		List<Hotel> hotels = hotelService.getAllHotels();
		return hotels;
	}

	@DeleteMapping(value = "/rest/delete/{id}")
	public String deleteHotel(@PathVariable String id) {
		String sId = id.replaceAll("'", "");
		hotelService.removeHotelById(sId);
		return "success";
	}


	@PostMapping(value = "/rest/hotelupdate")
	public String updateHotel(Hotel hotel) {
		Long id = hotel.getId();
		String hotelName = hotel.getHotelName();
		String location = hotel.getLocation();
		String image = hotel.getImage();
		String price = hotel.getPrice();
		String description = hotel.getDescription();
		hotelService.updateHotelById(id, hotelName, image, location, price, description);
		return "success";
	}

	@PostMapping(value = "/rest/hotelbook")
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

	@GetMapping(value = "/rest/hotel/{id}")
	public ModelAndView getHotelDetail(@PathVariable String id) {
		String sId = id.replaceAll("'", "");
		Hotel hotel = hotelService.getHotelById(sId);
		return new ModelAndView("hoteldetail", "hotel", hotel);
	}

	@PostMapping(value = "/rest/hoteladd")
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

	@GetMapping(value = "/rest/booking/{email}")
	public ModelAndView viewBooking(@PathVariable String email) throws Exception {
		// GET ALL BOOKING UNDER THIS USER
		List<Booking> bookings = bookingService.findAllOrderByUserEmail(email);
//		request.setAttribute("bookings", bookings);
		return new ModelAndView("viewbooking", "bookings", bookings);
	}

	@GetMapping(value = "/rest/bookingsall")
	public ModelAndView manangeBookingView() throws Exception {
		List<Booking> bookings = bookingService.findAllOrder();
		for(Booking booking : bookings) {
			System.out.println(booking.getHotelName());
		}
		return new ModelAndView("managebooking", "bookings", bookings);
	}

	@DeleteMapping(value = "/rest/bookinddelete/{id}")
	public String deleteBooking(@PathVariable String id) throws Exception{
		bookingService.deleteBookingById(id);
		return "redirect:/hotels";
	}

	@GetMapping(value = "/rest/hotelsearch/{query}")
	public ModelAndView searchHotel(@PathVariable String searchName) throws Exception {
		List<Hotel> hotels = hotelService.getHotelByName(searchName);
		return new ModelAndView("hotels", "hotels", hotels);
	}
}
