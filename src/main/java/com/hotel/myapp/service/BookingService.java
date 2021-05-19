package com.hotel.myapp.service;

import com.hotel.myapp.dao.BookingDao;
import com.hotel.myapp.dao.DAO;
import com.hotel.myapp.pojo.Booking;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService{

	@Autowired BookingDao bookingDao;

	public Booking addOrder(Booking booking) throws Exception{
		return bookingDao.addOrder(booking);
	}
	
	public List<Booking> findAllOrderByUserEmail(String userEmail) throws Exception{
		return bookingDao.findAllOrderByUserEmail(userEmail);
	}
	
	public List<Booking> findAllOrder() throws Exception{
		return bookingDao.findAllOrder();
	}
	
	public void deleteBookingById(String id){
		bookingDao.deleteBookingById(id);
	}
}
