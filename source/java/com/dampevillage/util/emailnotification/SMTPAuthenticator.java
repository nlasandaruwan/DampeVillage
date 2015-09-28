package com.dampevillage.util.emailnotification;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	private EmailDataDTO emailDataDTO;

	public SMTPAuthenticator(EmailDataDTO emailDataDTO) {
		this.emailDataDTO = emailDataDTO;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(emailDataDTO.getUserName(), emailDataDTO.getPassword());
	}
}
