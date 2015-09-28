package com.dampevillage.search.formbean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fromDateSearch;
	private String toDateSearch;
	private String roomCategory;
	private String roomType;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		return actionErrors;
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
	 * @return the fromDateSearch
	 */
	public String getFromDateSearch() {
		return fromDateSearch;
	}

	/**
	 * @param fromDateSearch
	 *            the fromDateSearch to set
	 */
	public void setFromDateSearch(String fromDateSearch) {
		this.fromDateSearch = fromDateSearch;
	}

	/**
	 * @return the toDateSearch
	 */
	public String getToDateSearch() {
		return toDateSearch;
	}

	/**
	 * @param toDateSearch
	 *            the toDateSearch to set
	 */
	public void setToDateSearch(String toDateSearch) {
		this.toDateSearch = toDateSearch;
	}

}
