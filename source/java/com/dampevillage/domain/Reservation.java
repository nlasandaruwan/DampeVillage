package com.dampevillage.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dampevillage.common.entity.BaseEntity;

public class Reservation extends BaseEntity {

	private Date fromDate;
	private Date toDate;
	private Customer customer;
	private Payment payment;
	private String arrivalFrom;
	private String arriveFor;
	private String leaveAfter;
	private String airportPickup;
	private AccomodationMode accomodationMode;
	private String nationality;
	private int noOfRooms;
	private int adults;
	private int children;
	private String roomCategory;
	private String roomType;
	private int roomCategoryId;
	private int roomTypeId;
	private String reservationStatus;
	private Set<Room> rooms = new HashSet<Room>();

	/**
	 * @return the reservationStatus
	 */
	public String getReservationStatus() {
		return reservationStatus;
	}

	/**
	 * @param reservationStatus
	 *            the reservationStatus to set
	 */
	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	/**
	 * @return the rooms
	 */
	public Set<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms
	 *            the rooms to set
	 */
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	/**
	 * @return the roomCategoryId
	 */
	public int getRoomCategoryId() {
		return roomCategoryId;
	}

	/**
	 * @param roomCategoryId
	 *            the roomCategoryId to set
	 */
	public void setRoomCategoryId(int roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	/**
	 * @return the roomTypeId
	 */
	public int getRoomTypeId() {
		return roomTypeId;
	}

	/**
	 * @param roomTypeId
	 *            the roomTypeId to set
	 */
	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	/**
	 * @return the roomCategory
	 */
	public String getRoomCategory() {
		return roomCategory;
	}

	/**
	 * @param roomCategory
	 *            the roomCategory to set
	 */
	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * @param payment
	 *            the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	/**
	 * @return the arrivalFrom
	 */
	public String getArrivalFrom() {
		return arrivalFrom;
	}

	/**
	 * @param arrivalFrom
	 *            the arrivalFrom to set
	 */
	public void setArrivalFrom(String arrivalFrom) {
		this.arrivalFrom = arrivalFrom;
	}

	/**
	 * @return the arriveFor
	 */
	public String getArriveFor() {
		return arriveFor;
	}

	/**
	 * @param arriveFor
	 *            the arriveFor to set
	 */
	public void setArriveFor(String arriveFor) {
		this.arriveFor = arriveFor;
	}

	/**
	 * @return the leaveAfter
	 */
	public String getLeaveAfter() {
		return leaveAfter;
	}

	/**
	 * @param leaveAfter
	 *            the leaveAfter to set
	 */
	public void setLeaveAfter(String leaveAfter) {
		this.leaveAfter = leaveAfter;
	}

	/**
	 * @return the airportPickup
	 */
	public String getAirportPickup() {
		return airportPickup;
	}

	/**
	 * @param airportPickup
	 *            the airportPickup to set
	 */
	public void setAirportPickup(String airportPickup) {
		this.airportPickup = airportPickup;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the noOfRooms
	 */
	public int getNoOfRooms() {
		return noOfRooms;
	}

	/**
	 * @param noOfRooms
	 *            the noOfRooms to set
	 */
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	/**
	 * @return the adults
	 */
	public int getAdults() {
		return adults;
	}

	/**
	 * @param adults
	 *            the adults to set
	 */
	public void setAdults(int adults) {
		this.adults = adults;
	}

	/**
	 * @return the children
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(int children) {
		this.children = children;
	}

	/**
	 * @return the accomodationMode
	 */
	public AccomodationMode getAccomodationMode() {
		return accomodationMode;
	}

	/**
	 * @param accomodationMode
	 *            the accomodationMode to set
	 */
	public void setAccomodationMode(AccomodationMode accomodationMode) {
		this.accomodationMode = accomodationMode;
	}

	public void addRoom(Room room) {
		this.rooms.add(room);
		room.getReservations().add(this);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof Reservation))
			return false;
		Reservation that = (Reservation) other;
		return (this.getId() == that.getId());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
