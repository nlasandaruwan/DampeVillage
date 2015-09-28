package com.dampevillage.util.emailnotification;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	public static void sendmail(EmailDataDTO emailDataDTO) throws MessagingException, MessagingException {

		// Build the mail properties.
		Properties props = buildProperties(emailDataDTO);

		Authenticator auth = new SMTPAuthenticator(emailDataDTO);
		Session session = Session.getInstance(props, auth);
		// session.setDebug(true);

		//
		// This HTML mail have to 2 part, the BODY and the embedded image
		//
		MimeMultipart multipart = new MimeMultipart("related");

		// first part (the html)
		BodyPart messageBodyPart = new MimeBodyPart();
		String htmlText = "<img src=\"cid:image\"><br>" + emailDataDTO.getMessage();
		messageBodyPart.setContent(htmlText, "text/html");

		// add it
		multipart.addBodyPart(messageBodyPart);

		// second part (the image)
		messageBodyPart = new MimeBodyPart();

		DataSource fds = new FileDataSource(emailDataDTO.getLogoLocation());
		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<image>");

		// add it
		multipart.addBodyPart(messageBodyPart);

		MimeMessage msg = new MimeMessage(session);
		// put everything together
		msg.setContent(multipart);

		msg.setSubject(emailDataDTO.getSubject());
		msg.setFrom(new InternetAddress(emailDataDTO.getFrom()));

		if (emailDataDTO.getTo() != null && !emailDataDTO.getTo().equals("")) {

			String toList = emailDataDTO.getTo();
			String[] toArray = toList.split(",");

			if (toArray.length > 0) {
				for (String to : toArray) {
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				}
			}
		}

		if (emailDataDTO.getCc() != null && !emailDataDTO.getCc().equals("")) {

			String ccList = emailDataDTO.getCc();
			String[] ccArray = ccList.split(",");

			if (ccArray.length > 0) {
				for (String cc : ccArray) {
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}
			}
		}

		if (emailDataDTO.getBcc() != null && !emailDataDTO.getBcc().equals("")) {

			String bccList = emailDataDTO.getBcc();
			String[] bccArray = bccList.split(",");

			if (bccArray.length > 0) {
				for (String bcc : bccArray) {
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
				}
			}
		}

		Transport.send(msg);
	}

	private static Properties buildProperties(EmailDataDTO emailDataDTO) {

		Properties props = null;

		if (props == null) {
			props = new Properties();
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.host", emailDataDTO.getMailServer());
			// To see what is going on behind the scene
			props.put("mail.debug", "true");
		}

		return props;
	}
}
