package com.dampevillage.admin.notification.inquiryFormBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InquiryFormBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String recoverQuestion;
	private String recoverAnswer;
	private String email;
	private String userName;

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		return actionErrors;
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

}
