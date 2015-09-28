package com.dampevillage.domain;

import java.util.HashSet;
import java.util.Set;

import com.dampevillage.common.entity.BaseEntity;

public class Room extends BaseEntity {

	private RoomType roomType;
	private String roomDescription;
	private RoomCategory roomCategory;
	private String roomNumber;
	private int floorNumber;
	private Set<Reservation> reservations = new HashSet<Reservation>();

	/**
	 * @return the reservations
	 */
	public Set<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * @param reservations
	 *            the reservations to set
	 */
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	/**
	 * @return the roomNumber
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber
	 *            the roomNumber to set
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	/**
	 * @param floorNumber
	 *            the floorNumber to set
	 */
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	/**
	 * @return the roomCategory
	 */
	public RoomCategory getRoomCategory() {
		return roomCategory;
	}

	/**
	 * @param roomCategory
	 *            the roomCategory to set
	 */
	public void setRoomCategory(RoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	/**
	 * @return the roomType
	 */
	public RoomType getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the roomDescription
	 */
	public String getRoomDescription() {
		return roomDescription;
	}

	/**
	 * @param roomDescription
	 *            the roomDescription to set
	 */
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

}
