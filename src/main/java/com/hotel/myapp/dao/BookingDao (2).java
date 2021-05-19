package com.hotel.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.hotel.myapp.pojo.Booking;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDao extends DAO{
	public Booking addOrder(Booking booking) throws Exception{
		try {
			begin();
			getSession().save(booking);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return booking;
	}
	
	public List<Booking> findAllOrderByUserEmail(String userEmail) throws Exception{
		List<Booking> result = null;
		try {
			begin();
			Query query = getSession().createQuery("from Booking where curUser = ?");
			query.setParameter(0, userEmail);
			result = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return result;
	}
	
	public List<Booking> findAllOrder() throws Exception{
		List<Booking> result = null;
		try {
			begin();
			Query query = getSession().createQuery("from Booking");
			result = query.list();

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return result;
	}
	
	public void deleteBookingById(String id){
		try {
			begin();
			Query query = getSession().createQuery("delete Booking where id = ?");
			query.setParameter(0, Long.valueOf(id));
			query.executeUpdate();
		}
		catch (Exception e) {
			rollback();
			e.printStackTrace();
		}finally {
			commit();
		}
	}
}
