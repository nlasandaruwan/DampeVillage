package com.dampevillage.reservation.formbean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ReserConfirmationvationFormBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int version;
	private String fromDate;
	private String toDate;
	private String arrivalFrom;
	private String arriveFor;
	private String leaveAfter;
	private String airportPickup;
	private int accomodationMode;
	private String nationality;
	private int noOfRooms;
	private int adults;
	private int children;
	private String roomCategory;
	private String roomType;
	private int roomCategoryId;
	private int roomTypeId;
	private long charge;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		return actionErrors;
	}

	/**
	 * @return the charge
	 */
	public long getCharge() {
		return charge;
	}

	/**
	 * @param charge
	 *            the charge to set
	 */
	public void setCharge(long charge) {
		this.charge = charge;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
	 * @return the accomodationMode
	 */
	public int getAccomodationMode() {
		return accomodationMode;
	}

	/**
	 * @param accomodationMode
	 *            the accomodationMode to set
	 */
	public void setAccomodationMode(int accomodationMode) {
		this.accomodationMode = accomodationMode;
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

}
