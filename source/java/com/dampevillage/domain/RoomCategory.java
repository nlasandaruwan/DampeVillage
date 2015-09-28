package com.dampevillage.domain;

import com.dampevillage.common.entity.BaseEntity;

public class RoomCategory extends BaseEntity {

	private String roomCategoryType;
	private String description;
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the roomCategoryType
	 */
	public String getRoomCategoryType() {
		return roomCategoryType;
	}

	/**
	 * @param roomCategoryType
	 *            the roomCategoryType to set
	 */
	public void setRoomCategoryType(String roomCategoryType) {
		this.roomCategoryType = roomCategoryType;
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

}
