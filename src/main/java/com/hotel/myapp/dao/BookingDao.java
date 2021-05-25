package com.hotel.myapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hotel.myapp.pojo.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This interface models a Data Access Object for Booking pojo
 * @author Priyanka
 *
 */
@Repository
@Transactional
public class BookingDao {
	@Autowired  
    private SessionFactory sessionFactory; 
	
	/**
	 * @param booking,
	 * @return Bookings  
	 * This method returns all the Bookings of the User after adding the hotel
	 * @throws Exception 
	 * It returns booking details of the user
	 */
	public Booking addOrder(Booking booking) throws Exception{
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			session.save(booking);
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return booking;
	}
	
	/**
	 * @param userEmail,
	 * @return  booking from user 
	 * This Method Returns all the orders by User Email
	 * This method is used to find all the orders with user Email
	 * @throws Exception
	 * 
	 */
	public List<Booking> findAllOrderByUserEmail(String userEmail) throws Exception{
		List<Booking> result = null;
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<Booking> query = session.createQuery("from Booking where curUser = ?", Booking.class);
			query.setParameter(0, userEmail);
			result = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return result;
	}
	
	/**
	 * @return Booking list
	 * This Method returns all the orders from Booking class
	 * This method is used to return the result of Bookings booked by the user
	 * @throws Exception
	 * 
	 */
	public List<Booking> findAllOrder() throws Exception{
		List<Booking> result = null;
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<Booking> query = session.createQuery("from Booking", Booking.class);
			result = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return result;
	}
	
	/**
	 * @param id,
	 * This method is used to delete booking by Id
	 * It is used to delete the bookings by Id given by the user
	 */
	public void deleteBookingById(String id){
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			session.createQuery("delete Booking where id = ?").setParameter(0, Long.valueOf(id)).executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
	}
}