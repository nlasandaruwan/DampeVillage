package com.dampevillage.domain;

import com.dampevillage.common.entity.BaseEntity;

public class HotelProfile extends BaseEntity {

	private String addressOne;
	private String addressTwo;
	private String description;
	private String direction;
	private String city;
	private String hotelName;
	private String country;
	private String contactNumberOne;
	private String contactNumberTwo;
	private String postalCode;
	private String otherWeb;
	private String email;
	private String fax;

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the addressOne
	 */
	public String getAddressOne() {
		return addressOne;
	}

	/**
	 * @param addressOne
	 *            the addressOne to set
	 */
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	/**
	 * @return the addressTwo
	 */
	public String getAddressTwo() {
		return addressTwo;
	}

	/**
	 * @param addressTwo
	 *            the addressTwo to set
	 */
	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName
	 *            the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the contactNumberOne
	 */
	public String getContactNumberOne() {
		return contactNumberOne;
	}

	/**
	 * @param contactNumberOne
	 *            the contactNumberOne to set
	 */
	public void setContactNumberOne(String contactNumberOne) {
		this.contactNumberOne = contactNumberOne;
	}

	/**
	 * @return the contactNumberTwo
	 */
	public String getContactNumberTwo() {
		return contactNumberTwo;
	}

	/**
	 * @param contactNumberTwo
	 *            the contactNumberTwo to set
	 */
	public void setContactNumberTwo(String contactNumberTwo) {
		this.contactNumberTwo = contactNumberTwo;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the otherWeb
	 */
	public String getOtherWeb() {
		return otherWeb;
	}

	/**
	 * @param otherWeb
	 *            the otherWeb to set
	 */
	public void setOtherWeb(String otherWeb) {
		this.otherWeb = otherWeb;
	}

}
