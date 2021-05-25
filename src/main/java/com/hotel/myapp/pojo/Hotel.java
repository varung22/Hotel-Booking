package com.hotel.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  The class models the generic  Hotel. The annotations included allows to store the object as an pojo in the DB.
 * @author Vidhusha
 *
 */
@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique= true, nullable = false)
	private long id;
	
	@Column(name= "hotelName")
	private String hotelName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="website")
	private String website;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="email")
	private String email;
	
	@Column(name="type")
	private String type;

	@Column(name="rating")
    private String rating;
	
	@Column(name="address")
	private String address;
	
	@Column(name="location")
	private String location;
	
	@Column(name="image")
	private String image;

	@Column(name="price")
	private String price;
	
	/**
	 * @return Hotel Image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return Hotel Id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Hotel Name
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @return Hotel Location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return Hotel Price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return Hotel Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Hotel Website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return Hotel Contact details
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return Hotel Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Hotel type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Hotel Rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return Hotel Address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}