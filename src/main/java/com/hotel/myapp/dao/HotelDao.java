package com.hotel.myapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hotel.myapp.pojo.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *  This interface models a Data Access Object for Hotel pojo

 * @author Priyanka
 *
 */
@Repository
@Transactional
public class HotelDao {
	@Autowired  
    private SessionFactory sessionFactory;
	
	/**
	 * @param h
	 * @return Session
	 * This Method is used to add Hotels
	 * It is used to add the Hotels by Admin
	 * @throws Exception
	 */
	public Hotel addHotel(Hotel h) throws Exception {
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			session.save(h);
			return h;
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return h;
	}

	/**
	 * @return All the Hotels list from Hotel class. It also returns result of the all Hotels
	 */
	public List<Hotel> getAllHotels() {
		List<Hotel> result = null;
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<Hotel> query = session.createQuery("from Hotel", Hotel.class);
			result = query.list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return result;
	}

	/**
	 * @param searchName
	 * @return Searh results for given Hotel name
	 * This Method is used to Search for a Hotel By Hotel Name 
	 * 
	 */
	public List<Hotel> getHotelByName(String searchName) {
		List<Hotel> result = null;
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<Hotel> query = session.createQuery("from Hotel where hotelName like ?", Hotel.class);
			query.setParameter(0, '%'+searchName+'%');
			result = query.list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return result;
	}

	/**
	 * @param id
	 * @param hotelName
	 * @param image
	 * @param location
	 * @param price
	 * @param description
	 * This Method is used to update hotel by given Hotel Name,Hotel Image,Price of the Hotel,Location of the Hotel and Description of the Hotel
	 * 
	 */
	public void updateHotelById(String id, String hotelName, String image, String location, String price,
			String description) {
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<Hotel> query = session.createQuery("from Hotel where id = ?", Hotel.class);
			query.setParameter(0, Long.valueOf(id));
			Hotel h = (Hotel) query.uniqueResult();

			if (h != null) {
				h.setHotelName(hotelName);
				h.setImage(image);
				h.setLocation(location);
				h.setPrice(price);
				h.setDescription(description);
				session.update(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}

	}

	/**
	 * @param id
	 * @param hotelName
	 * @param image
	 * @param location
	 * @param price
	 * @param description
	 * 	  This Method is used to update hotel by given Id from Hotel class
	* This Method is used to update hotel by given Hotel Name,Hotel Image,Price of the Hotel,Location of the Hotel and Description of the Hotel
	 */
	public void updateHotelById(Long id, String hotelName, String image, String location, String price,
			String description) {
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<Hotel> query = session.createQuery("from Hotel where id = ?", Hotel.class);
			query.setParameter(0, id);
			Hotel h = (Hotel) query.uniqueResult();

			if (h != null) {
				h.setHotelName(hotelName);
				h.setImage(image);
				h.setLocation(location);
				h.setPrice(price);
				h.setDescription(description);
				session.update(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}

	}

	/**
	 * @param id
	 * @return Hotel List
	 * This method is used to return Hotels by Hotel Id
	 */
	public Hotel getHotelById(String id) {
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<Hotel> query = session.createQuery("from Hotel where id = ?", Hotel.class);
			query.setParameter(0, Long.valueOf(id));
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return null;
	}

	/**
	 * @param id
	 */
	public void removeHotelById(String id) {
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			session.createQuery("delete Hotel where id = ?").setParameter(0, Long.valueOf(id)).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
	}

}