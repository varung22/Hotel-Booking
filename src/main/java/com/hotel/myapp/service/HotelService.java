package com.hotel.myapp.service;

import com.hotel.myapp.dao.HotelDao;
import com.hotel.myapp.pojo.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This Service part of Myapp is responsible for implementing the Business Logic
 * @author Pradeep
 *
 */
@Service
public class HotelService {

	@Autowired HotelDao hotelDao;

	/**
	 * @param h
	 * @return Added Hotels from Dao
	 * @throws Exception
	 */
	public Hotel addHotel(Hotel h) throws Exception {
		return hotelDao.addHotel(h);
	}

	/**
	 * @return All Hotels from Dao
	 */
	public List<Hotel> getAllHotels() {
		return hotelDao.getAllHotels();
	}

	/**
	 * @param searchName
	 * @return Searched Hotels by their name from Dao
	 */
	public List<Hotel> getHotelByName(String searchName) {
		return hotelDao.getHotelByName(searchName);
	}

	/**
	 * @param id
	 * @param hotelName
	 * @param image
	 * @param location
	 * @param price
	 * @param description
	 * This method is used to update hotels by their Id.
	 */
	public void updateHotelById(String id, String hotelName, String image, String location, String price,
			String description) {
		hotelDao.updateHotelById(id, hotelName, image, location, price, description);
	}

	/**
	 * @param id
	 * @param hotelName
	 * @param image
	 * @param location
	 * @param price
	 * @param description
	 * 	This method is used to update hotels by their Id.

	 */
	public void updateHotelById(Long id, String hotelName, String image, String location, String price,
			String description) {
		hotelDao.updateHotelById(id, hotelName, image, location, price, description);
	}

	/**
	 * @param id
	 * @return Hotel by given Id from Dao
	 */
	public Hotel getHotelById(String id) {
		return hotelDao.getHotelById(id);
	}

	/**
	 * @param id
	 * This method is used to remove hotel by given Id.
	 */
	public void removeHotelById(String id) {
		hotelDao.removeHotelById(id);
	}

}