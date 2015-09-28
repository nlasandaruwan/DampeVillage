package com.dampevillage.domain;

import com.dampevillage.common.entity.BaseEntity;

public class RoomPool extends BaseEntity {

	private RoomCategory roomCategory;
	private RoomType roomType;
	private int totalNumberOfRooms;
	private int roomsAvailable;

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
	 * @return the totalNumberOfRooms
	 */
	public int getTotalNumberOfRooms() {
		return totalNumberOfRooms;
	}

	/**
	 * @param totalNumberOfRooms
	 *            the totalNumberOfRooms to set
	 */
	public void setTotalNumberOfRooms(int totalNumberOfRooms) {
		this.totalNumberOfRooms = totalNumberOfRooms;
	}

	/**
	 * @return the roomsAvailable
	 */
	public int getRoomsAvailable() {
		return roomsAvailable;
	}

	/**
	 * @param roomsAvailable
	 *            the roomsAvailable to set
	 */
	public void setRoomsAvailable(int roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}
}
