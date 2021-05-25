package com.hotel.myapp.dto;

import java.util.Date;

/**
 *  * This interface models a Data Transfer Object for Booking pojo

 * @author Priyanka
 *
 */
public class BookingDto {

	/**
	 * 
	 */
	private String firstName;

	private String lastName;

	private String phone;

	private String hotelId;

	private String hotelName;

	private String curUser;

	private String comments;

	private Date checkIn;

	private Date checkOut;

	private Integer noAdults;

	private Integer noChildren;

	private String roomInfo;

	/**
	 * @return User First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return User Last Name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return Current User
	 */ 
	public String getCurUser() {
		return curUser;
	}

	/**
	 * @param curUser
	 */
	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	/**
	 * @return Phone Number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return Comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return CheckIn date
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/**
	 * @param checkIn
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * @return CheckOut date
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/**
	 * @param checkOut
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	/**
	 * @return Hotel Id
	 */
	public String getHotelId() {
		return hotelId;
	}

	/**
	 * @param hotelId
	 */
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * @return No. of Adults
	 */
	public Integer getNoAdults() {
		return noAdults;
	}

	/**
	 * @param noAdults
	 */
	public void setNoAdults(Integer noAdults) {
		this.noAdults = noAdults;
	}

	/**
	 * @return No. of Childrens
	 */
	public Integer getNoChildren() {
		return noChildren;
	}

	/**
	 * @param noChildren
	 */
	public void setNoChildren(Integer noChildren) {
		this.noChildren = noChildren;
	}

	/**
	 * @return Room Info
	 */
	public String getRoomInfo() {
		return roomInfo;
	}

	/**
	 * @param roomInfo
	 */
	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}
}