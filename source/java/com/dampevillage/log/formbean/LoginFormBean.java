package com.dampevillage.log.formbean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginFormBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String reservationRedirect;

	/**
	 * @return the reservationRedirect
	 */
	public String getReservationRedirect() {
		return reservationRedirect;
	}

	/**
	 * @param reservationRedirect
	 *            the reservationRedirect to set
	 */
	public void setReservationRedirect(String reservationRedirect) {
		this.reservationRedirect = reservationRedirect;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		return actionErrors;
	}
}
