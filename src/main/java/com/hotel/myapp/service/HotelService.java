package com.hotel.myapp.service;

import com.hotel.myapp.dao.DAO;
import com.hotel.myapp.dao.HotelDao;
import com.hotel.myapp.pojo.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

	@Autowired HotelDao hotelDao;

	public Hotel addHotel(Hotel h) throws Exception {
		return hotelDao.addHotel(h);
	}

	public List<Hotel> getAllHotels() {
		return hotelDao.getAllHotels();
	}

	public List<Hotel> getHotelByName(String searchName) {
		return hotelDao.getHotelByName(searchName);
	}

	public void updateHotelById(String id, String hotelName, String image, String location, String price,
			String description) {
		hotelDao.updateHotelById(id, hotelName, image, location, price, description);
	}

	public void updateHotelById(Long id, String hotelName, String image, String location, String price,
			String description) {
		hotelDao.updateHotelById(id, hotelName, image, location, price, description);
	}

	public Hotel getHotelById(String id) {
		return hotelDao.getHotelById(id);
	}

	public void removeHotelById(String id) {
		hotelDao.removeHotelById(id);
	}

}
