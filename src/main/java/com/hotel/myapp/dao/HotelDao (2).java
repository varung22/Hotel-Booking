package com.hotel.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.hotel.myapp.pojo.Hotel;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDao extends DAO {
	public Hotel addHotel(Hotel h) throws Exception {
		try {
			begin();
			getSession().save(h);
			return h;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return h;
	}

	public List<Hotel> getAllHotels() {
		List<Hotel> result = null;
		try {
			begin();
			Query query = getSession().createQuery("from Hotel");
			result = query.list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return result;
	}

	public List<Hotel> getHotelByName(String searchName) {
		List<Hotel> result = null;
		try {
			begin();
			Query query = getSession().createQuery("from Hotel where hotelName like ?");
			query.setParameter(0, '%'+searchName+'%');
			result = query.list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return result;
	}

	public void updateHotelById(String id, String hotelName, String image, String location, String price,
			String description) {
		try {
			begin();
			Query query = getSession().createQuery("from Hotel where id = ?");
			query.setParameter(0, Long.valueOf(id));
			Hotel h = (Hotel) query.uniqueResult();

			if (h != null) {
				h.setHotelName(hotelName);
				h.setImage(image);
				h.setLocation(location);
				h.setPrice(price);
				h.setDescription(description);
				getSession().update(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}

	}

	public void updateHotelById(Long id, String hotelName, String image, String location, String price,
			String description) {
		try {
			begin();
			Query query = getSession().createQuery("from Hotel where id = ?");
			query.setParameter(0, id);
			Hotel h = (Hotel) query.uniqueResult();

			if (h != null) {
				h.setHotelName(hotelName);
				h.setImage(image);
				h.setLocation(location);
				h.setPrice(price);
				h.setDescription(description);
				getSession().update(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}

	}

	public Hotel getHotelById(String id) {
		try {
			begin();
			Query query = getSession().createQuery("from Hotel where id = ?");
			query.setParameter(0, Long.valueOf(id));
			Hotel hotel = (Hotel) query.uniqueResult();
			return hotel;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return null;
	}

	public void removeHotelById(String id) {
		try {
			begin();
			Query query = getSession().createQuery("delete Hotel where id = ?");
			query.setParameter(0, Long.valueOf(id));
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
	}

}
