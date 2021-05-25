package com.hotel.myapp.dto;

/**
 *  * This interface models a Data Transfer Object for Hotel pojo

 * @author Priyanka
 *
 */
public class HotelDto {

	private long id;
	
	private String name;

	private String description;

	private String website;

	private String contact;

	private String email;

	private String type;

    private String rating;

	private String address;

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
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return Hotel Contact Info
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
	 * @return Hotel Type
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