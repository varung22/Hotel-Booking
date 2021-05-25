package com.hotel.myapp.service;

import com.hotel.myapp.dao.BookingDao;
import com.hotel.myapp.pojo.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This Service part of Myapp is responsible for implementing the Business Logic
 * @author Pradeep
 */
@Service
public class BookingService{

	@Autowired BookingDao bookingDao;

	/**
	 * @param booking
	 * @return Order booking from Dao
	 * 
	 * @throws Exception
	 */
	public Booking addOrder(Booking booking) throws Exception{
		return bookingDao.addOrder(booking);
	}
	
	/**
	 * @param userEmail
	 * @return All Orders by User Email
	 * @throws Exception
	 */
	public List<Booking> findAllOrderByUserEmail(String userEmail) throws Exception{
		return bookingDao.findAllOrderByUserEmail(userEmail);
	}
	
	/**
	 * @return All Orders from Dao
	 * @throws Exception
	 */
	public List<Booking> findAllOrder() throws Exception{
		return bookingDao.findAllOrder();
	}
	
	/**
	 * @param id
	 * This method is used to delete the Booking by Id
	 */
	public void deleteBookingById(String id){
		bookingDao.deleteBookingById(id);
	}
}