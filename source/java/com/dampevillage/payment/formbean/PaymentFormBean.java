package com.dampevillage.payment.formbean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PaymentFormBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int version;
	private int paymentAmount;
	private String cardNumber;
	private String cardHolderName;
	private String expireMonth;
	private String expireYear;
	private String email;
	private String cardType;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		return actionErrors;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the paymentAmount
	 */
	public int getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount
	 *            the paymentAmount to set
	 */
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
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
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber
	 *            the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName
	 *            the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the expireMonth
	 */
	public String getExpireMonth() {
		return expireMonth;
	}

	/**
	 * @param expireMonth
	 *            the expireMonth to set
	 */
	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}

	/**
	 * @return the expireYear
	 */
	public String getExpireYear() {
		return expireYear;
	}

	/**
	 * @param expireYear
	 *            the expireYear to set
	 */
	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
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

}
