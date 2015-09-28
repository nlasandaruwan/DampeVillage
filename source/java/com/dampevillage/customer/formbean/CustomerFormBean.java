package com.dampevillage.customer.formbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CustomerFormBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int version;
	private int privilageId;
	private String firstName;
	private String lasteName;
	private String addressOne;
	private String addressTwo;
	private String streetNumber;
	private String country;
	private String postalCode;
	private String email;
	private String state;
	private String contactMobile;
	private String userName;
	private String password;
	private String passwordRe;
	private String contactLand;
	private String title;
	private String nicPassportNumber;
	private String recoverQuestion;
	private String recoverAnswer;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		String nameToken = "\\p{Upper}(\\p{Lower}+\\s?)";
		String namePattern = "(" + nameToken + "){2,3}";

		// Validate First Name
		if (getFirstName() != null && getFirstName().matches(namePattern)) {
			actionErrors.add("firstName", new ActionMessage("invalide.first.name"));
		}

		// Validate Second Name
		if (getLasteName() != null && getLasteName().matches(namePattern)) {
			actionErrors.add("lastName", new ActionMessage("invalide.last.name"));
		}

		// Validate Email adress
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given email with the pattern
		if (getEmail() != null) {
			Matcher m = p.matcher(getEmail());
			if (!m.matches()) {
				actionErrors.add("email", new ActionMessage("invalide.email"));
			}
		}

		// validate passwords
		if (getPassword() != null && getPasswordRe() != null && !getPassword().equals(getPasswordRe())) {
			actionErrors.add("password", new ActionMessage("invalide.password"));
		}
		return actionErrors;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the recoverQuestion
	 */
	public String getRecoverQuestion() {
		return recoverQuestion;
	}

	/**
	 * @param recoverQuestion
	 *            the recoverQuestion to set
	 */
	public void setRecoverQuestion(String recoverQuestion) {
		this.recoverQuestion = recoverQuestion;
	}

	/**
	 * @return the recoverAnswer
	 */
	public String getRecoverAnswer() {
		return recoverAnswer;
	}

	/**
	 * @param recoverAnswer
	 *            the recoverAnswer to set
	 */
	public void setRecoverAnswer(String recoverAnswer) {
		this.recoverAnswer = recoverAnswer;
	}

	/**
	 * @return the privilageId
	 */
	public int getPrivilageId() {
		return privilageId;
	}

	/**
	 * @param privilageId
	 *            the privilageId to set
	 */
	public void setPrivilageId(int privilageId) {
		this.privilageId = privilageId;
	}

	/**
	 * @return the passwordRe
	 */
	public String getPasswordRe() {
		return passwordRe;
	}

	/**
	 * @param passwordRe
	 *            the passwordRe to set
	 */
	public void setPasswordRe(String passwordRe) {
		this.passwordRe = passwordRe;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lasteName
	 */
	public String getLasteName() {
		return lasteName;
	}

	/**
	 * @param lasteName
	 *            the lasteName to set
	 */
	public void setLasteName(String lasteName) {
		this.lasteName = lasteName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the contactMobile
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile
	 *            the contactMobile to set
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
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

	/**
	 * @return the contactLand
	 */
	public String getContactLand() {
		return contactLand;
	}

	/**
	 * @param contactLand
	 *            the contactLand to set
	 */
	public void setContactLand(String contactLand) {
		this.contactLand = contactLand;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the nicPassportNumber
	 */
	public String getNicPassportNumber() {
		return nicPassportNumber;
	}

	/**
	 * @param nicPassportNumber
	 *            the nicPassportNumber to set
	 */
	public void setNicPassportNumber(String nicPassportNumber) {
		this.nicPassportNumber = nicPassportNumber;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
