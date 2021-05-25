package com.hotel.myapp.rest;

import com.hotel.myapp.dto.BookingDto;
import com.hotel.myapp.dto.HotelDto;
import com.hotel.myapp.pojo.Booking;
import com.hotel.myapp.pojo.Hotel;
import com.hotel.myapp.service.BookingService;
import com.hotel.myapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a  Rest Controller part of Myapp application. It is responsible for Administration tab in the app
 * @author Varun
 * 
 *
 */
@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelRestController {

	/**
	 * 
	 */
	@Autowired HotelService hotelService;

	@Autowired BookingService bookingService;

	/**
	 * @param dto
	 * @return Booking details from dto
	 * -User First name
	 * -User Lastname
	 * -User Phone number
	 * -Hotel Id and Name
	 * -Check in and Check out details 
	 * -Room Info
	 * -This method is used to Add and book the orders by the user from data transfer object
	 * -It is used to display the details of the user by first name,last name,phone number,hotel id,check in details,checkout details and room information
	 */
	@PostMapping("/rest/booking")
	public ResponseEntity<Boolean> addBookingOrder(@RequestBody BookingDto dto) {

		Booking booking = new Booking();
		booking.setFirstName(dto.getFirstName());
		booking.setLastName(dto.getLastName());
		booking.setPhone(dto.getPhone());
		booking.setCurUser(dto.getCurUser());
		booking.setHotelId(dto.getHotelId());
		booking.setHotelName(dto.getHotelName());
		booking.setCheckIn(dto.getCheckIn());
		booking.setCheckOut(dto.getCheckOut());
		booking.setNoAdults(dto.getNoAdults());
		booking.setNoChildren(dto.getNoChildren());
		booking.setRoomInfo(dto.getRoomInfo());

		try {
			Booking u = bookingService.addOrder(booking);
			if (u != null) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @param request
	 * @return Hotel details 
	 * -Name of the Hotel 
	 * -Description of the Hotel
	 * -Hotel Website and Email
	 * -Hotel type with address and rating
	 * -This method is used to display hotels list by hotel id,hotel name,hotel description,hotel website,hotel contact,hotel email,hotel type,hotel rating and hotel address.
	 */
	@GetMapping(value = "/rest/hotels")
	public ResponseEntity<List<HotelDto>> displayHotels(HttpServletRequest request) {
		List<Hotel> hotels = hotelService.getAllHotels();
		List<HotelDto> hotelsDto = new ArrayList<>();
		for (Hotel hotel : hotels) {
			HotelDto item = new HotelDto();
			item.setId(hotel.getId());
			item.setName(hotel.getHotelName());
			item.setDescription(hotel.getDescription());
			item.setWebsite(hotel.getWebsite());
			item.setContact(hotel.getContact());
			item.setEmail(hotel.getEmail());
			item.setType(hotel.getType());
			item.setRating(hotel.getRating());
			item.setAddress(hotel.getAddress());
			
			hotelsDto.add(item);
		}
		return new ResponseEntity<>(hotelsDto, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return  Hotel details by their Id
	 * -Name of the Hotel 
	 * -Description of the Hotel
	 * -Hotel Website and Email
	 * -Hotel type with address and rating
	 * -This method is used to display hotels list by hotel id,hotel name,hotel description,hotel website,hotel contact,hotel email,hotel type,hotel rating and hotel address.
	 */
	@GetMapping(value = "/rest/hotel/{id}")
	public ResponseEntity<HotelDto> getHotelDetail(@PathVariable String id) {
		String sId = id.replaceAll("'", "");
		Hotel hotel = hotelService.getHotelById(sId);
		
		HotelDto item = new HotelDto();
		item.setId(hotel.getId());
		item.setName(hotel.getHotelName());
		item.setDescription(hotel.getDescription());
		item.setWebsite(hotel.getWebsite());
		item.setContact(hotel.getContact());
		item.setEmail(hotel.getEmail());
		item.setType(hotel.getType());
		item.setRating(hotel.getRating());
		item.setAddress(hotel.getAddress());
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return deleted hotels by their Id
	 * This method is used to delete the hotels by id. after deleting its displays success message on the screen
	 */
	@DeleteMapping(value = "/rest/delete/{id}")
	public String deleteHotel(@PathVariable String id) {
		String sId = id.replaceAll("'", "");
		hotelService.removeHotelById(sId);
		return "success";
	}


	/**
	 * @param hotel
	 * @return Updated Hotel List
	 * This method is used to update hotel by hotel id,hotel name,hotel location,hotel image,hotel price,hotel description,after updating , it displays success message
	 */
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

	/**
	 * @param request
	 * @return List of Added Hotels
	 * -Name of the Hotel
	 * -Location of the Hotel
	 * -Images of the Hotel
	 * -Price and Description of the Hotel
	 * @throws IllegalStateException
	 * This method is used to add hotel by hotel id,hotel name,hotel location,hotel image,hotel price,hotel description,after adding , it displays success message
	 */
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

	/**
	 * @param email
	 * @return Booking details by User Email
	 * @throws Exception
	 */
	@GetMapping(value = "/rest/booking/{email}")
	public ModelAndView viewBooking(@PathVariable String email) throws Exception {
		// GET ALL BOOKING UNDER THIS USER
		List<Booking> bookings = bookingService.findAllOrderByUserEmail(email);
//		request.setAttribute("bookings", bookings);
		return new ModelAndView("viewbooking", "bookings", bookings);
	}

	/** 
	 * @return Model and View of the Booking
	 * This method is used to manage Booking details
	 * 
	 * @throws Exception
	 */
	@GetMapping(value = "/rest/bookingsall")
	public ModelAndView manangeBookingView() throws Exception {
		List<Booking> bookings = bookingService.findAllOrder();
		for(Booking booking : bookings) {
			System.out.println(booking.getHotelName());
		}
		return new ModelAndView("managebooking", "bookings", bookings);
	}

	/**
	 * @param id
	 * @return Deleted Bookings by given Id
	 * @throws Exception
	 */
	@DeleteMapping(value = "/rest/bookinddelete/{id}")
	public String deleteBooking(@PathVariable String id) throws Exception{
		bookingService.deleteBookingById(id);
		return "redirect:/hotels";
	}

	/**
	 * @param searchName
	 * @return Searched Hotels by Hotel Name
	 * @throws Exception
	 */
	@GetMapping(value = "/rest/hotelsearch/{query}")
	public ModelAndView searchHotel(@PathVariable String searchName) throws Exception {
		List<Hotel> hotels = hotelService.getHotelByName(searchName);
		return new ModelAndView("hotels", "hotels", hotels);
	}
}