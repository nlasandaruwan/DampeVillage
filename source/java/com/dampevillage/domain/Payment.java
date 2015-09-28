package com.dampevillage.domain;

import java.util.Date;

import com.dampevillage.common.entity.BaseEntity;

public class Payment extends BaseEntity {

	private long paymentAmount;
	private Date paymentDate;
	private String cardHolderName;
	private String cardExpirityYear;
	private String cardExpirityMonth;
	private String email;
	private String cardNumber;
	private String cardType;

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	 * @return the paymentAmount
	 */
	public long getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount
	 *            the paymentAmount to set
	 */
	public void setPaymentAmount(long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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
	 * @return the cardExpirityYear
	 */
	public String getCardExpirityYear() {
		return cardExpirityYear;
	}

	/**
	 * @param cardExpirityYear
	 *            the cardExpirityYear to set
	 */
	public void setCardExpirityYear(String cardExpirityYear) {
		this.cardExpirityYear = cardExpirityYear;
	}

	/**
	 * @return the cardExpirityMonth
	 */
	public String getCardExpirityMonth() {
		return cardExpirityMonth;
	}

	/**
	 * @param cardExpirityMonth
	 *            the cardExpirityMonth to set
	 */
	public void setCardExpirityMonth(String cardExpirityMonth) {
		this.cardExpirityMonth = cardExpirityMonth;
	}

}
