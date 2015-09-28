package com.dampevillage.domain;

import java.util.Date;

import com.dampevillage.common.entity.BaseEntity;

public class RoomRate extends BaseEntity {

	private RoomCategory roomCategory;
	private RoomType roomType;
	private AccomodationMode accomodationMode;
	private Date validFrom;
	private Date validTo;
	private long price;

	/**
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
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

	/**
	 * @return the validFrom
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom
	 *            the validFrom to set
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the validTo
	 */
	public Date getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo
	 *            the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

}
