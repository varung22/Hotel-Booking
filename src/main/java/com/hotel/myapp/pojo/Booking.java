package com.hotel.myapp.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The class models the generic Booking for the Hotel. The annotations included allows to store the object as an pojo in the DB.
 * @author Vidhusha
 * 
 */
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private long id;
	
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="curUser")
	private String curUser;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="date")
	private String date;
	
	@Column(name="hotelId")
	private String hotelId;
	
	@Column(name="hotelName")
	private String hotelName;
	
	@Column(name="checkIn")
	private Date checkIn;
	
	@Column(name="checkOut")
	private Date checkOut;
	
	@Column(name="noAdults")
	private Integer noAdults;
	
	@Column(name="noChildren")
	private Integer noChildren;
	
	@Column(name="roomInfo")
	private String roomInfo;

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
	 * @return  Current User
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
	 * @return Id
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
	 * @return First Name
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
	 * @return Last name
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
	 * @return Checkout date
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
	 * @return No.of Adults
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
	 * @return No.of Childrens
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

	/**
	 * @return Date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
}