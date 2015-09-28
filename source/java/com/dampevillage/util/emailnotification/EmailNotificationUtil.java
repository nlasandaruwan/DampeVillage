package com.dampevillage.util.emailnotification;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.Customer;
import com.dampevillage.domain.Reservation;
import com.dampevillage.util.PropertyUtil;

public class EmailNotificationUtil {

	private static PropertyUtil propertyUtil;
	private static String managerEmailList;
	private static String adminEmail;
	private static String systemEmail;
	private static String mailServer;

	private static String userName;
	private static String password;

	private static String logoLocation;

	static {
		try {
			propertyUtil = new PropertyUtil();

			// Manager email list
			managerEmailList = propertyUtil.getValue("manager.email");

			// Administrator email
			adminEmail = propertyUtil.getValue("admin.email");

			// System email message
			systemEmail = propertyUtil.getValue("system.email");

			// mail server
			mailServer = propertyUtil.getValue("mail.smtp.host");

			// user name
			userName = propertyUtil.getValue("system.email.userName");

			// password
			password = propertyUtil.getValue("system.email.password");

			// Logo Location
			logoLocation = propertyUtil.getValue("logo.image.location");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void notifyUserCreation(Customer customer) throws ClassNotFoundException, IOException,
			MessagingException {
		if (customer.getPrivilage().getId() == SessionConstants.Common.CUSTOMER_PRIVILAGE_ID) {
			notifyCustomerCreation(customer);
		}
		if (customer.getPrivilage().getId() == SessionConstants.Common.STAFF_PRIVILAGE_ID) {
			notifyStaffCreation(customer);
		}
		if (customer.getPrivilage().getId() == SessionConstants.Common.MANAGER_PRIVILAGE_ID) {
			notifyManagerCreation(customer);
		}
	}

	// ---------------------------- Customer Creation
	// -----------------------------------------
	private static void notifyCustomerCreation(Customer customer) throws MessagingException {
		// customer creation email message
		String customerCreateMessage = propertyUtil.getValue("customer.creation.message");
		String customerCreateFilledMessage = fillCustomerCreationData(customerCreateMessage, customer);

		// Customer creation subject.
		String customerCreationSubject = propertyUtil.getValue("customer.creation.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		dataDTO.setTo(customer.getEmail());

		// cc
		dataDTO.setCc(adminEmail + "," + managerEmailList);

		// mail server
		dataDTO.setMailServer(mailServer);

		// user name
		dataDTO.setUserName(userName);

		// password
		dataDTO.setPassword(password);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage(customerCreateFilledMessage);
		dataDTO.setSubject(customerCreationSubject + " " + customer.getTitle() + " " + customer.getFirstName());

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	private static String fillCustomerCreationData(String emptyMessage, Customer customer) {

		CharSequence name = "$1", country = "$2", email = "$3", contact = "$4";

		String fullName = customer.getTitle() + " " + customer.getFirstName() + " " + customer.getLasteName();

		emptyMessage = emptyMessage.replace(name, fullName);
		emptyMessage = emptyMessage.replace(country, customer.getCountry());
		emptyMessage = emptyMessage.replace(email, customer.getEmail());
		emptyMessage = emptyMessage.replace(contact, customer.getContactMobile());

		return emptyMessage;
	}

	// ---------------------------- End of Customer Creation
	// -----------------------------------------

	// ---------------------------- Staff Creation
	// -----------------------------------------
	private static void notifyStaffCreation(Customer customer) throws MessagingException {
		// customer creation email message
		String staffCreateMessage = propertyUtil.getValue("staff.creation.message");
		String staffCreateFilledMessage = fillStaffCreationData(staffCreateMessage, customer);

		// Customer creation subject.
		String staffCreationSubject = propertyUtil.getValue("staff.creation.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		dataDTO.setTo(customer.getEmail());

		// cc
		dataDTO.setCc(adminEmail);

		// mail server
		dataDTO.setMailServer(mailServer);

		// password
		dataDTO.setPassword(password);

		// user name
		dataDTO.setUserName(userName);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage(staffCreateFilledMessage);
		dataDTO.setSubject(staffCreationSubject + " " + customer.getTitle() + " " + customer.getFirstName());

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	private static String fillStaffCreationData(String emptyMessage, Customer customer) {

		CharSequence name = "$1", email = "$2", contact = "$3", userName = "$4", password = "$5";

		String fullName = customer.getTitle() + " " + customer.getFirstName() + " " + customer.getLasteName();

		emptyMessage = emptyMessage.replace(name, fullName);
		emptyMessage = emptyMessage.replace(email, customer.getEmail());
		emptyMessage = emptyMessage.replace(contact, customer.getContactMobile());
		emptyMessage = emptyMessage.replace(userName, customer.getUserName());
		emptyMessage = emptyMessage.replace(password, customer.getPassword());

		return emptyMessage;
	}

	// ---------------------------- End of Staff Creation
	// -----------------------------------------

	// ---------------------------- Manager Creation
	// -----------------------------------------
	private static void notifyManagerCreation(Customer customer) throws MessagingException {
		// customer creation email message
		String managerCreateMessage = propertyUtil.getValue("manager.creation.message");
		String managerCreateFilledMessage = fillManagerCreationData(managerCreateMessage, customer);

		// Customer creation subject.
		String managerCreationSubject = propertyUtil.getValue("manager.creation.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		dataDTO.setTo(customer.getEmail());

		// cc
		dataDTO.setCc(adminEmail);

		// mail server
		dataDTO.setMailServer(mailServer);

		// password
		dataDTO.setPassword(password);

		// user name
		dataDTO.setUserName(userName);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage(managerCreateFilledMessage);
		dataDTO.setSubject(managerCreationSubject + " " + customer.getTitle() + " " + customer.getFirstName());

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	private static String fillManagerCreationData(String emptyMessage, Customer customer) {

		CharSequence name = "$1", email = "$2", contact = "$3", userName = "$4", password = "$5";

		String fullName = customer.getTitle() + " " + customer.getFirstName() + " " + customer.getLasteName();

		emptyMessage = emptyMessage.replace(name, fullName);
		emptyMessage = emptyMessage.replace(email, customer.getEmail());
		emptyMessage = emptyMessage.replace(contact, customer.getContactMobile());
		emptyMessage = emptyMessage.replace(userName, customer.getUserName());
		emptyMessage = emptyMessage.replace(password, customer.getPassword());

		return emptyMessage;
	}

	// ---------------------------- End of Manager Creation
	// -----------------------------------------

	// ---------------------------- Reservation Creation
	// -----------------------------------------
	public static void notifySaveOrUpdateReservation(Reservation reservation) throws MessagingException {
		// reservation creation email message
		String reservationCreateMessage = propertyUtil.getValue("reservation.creation.message");
		String reservationCreateFilledMessage = fillSaveOrUpdateReservationData(reservationCreateMessage, reservation);

		// reservation creation subject.
		String reservationCreationSubject = propertyUtil.getValue("reservation.creation.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		dataDTO.setTo(reservation.getCustomer().getEmail());

		// cc
		dataDTO.setCc(adminEmail + "," + managerEmailList);

		// mail server
		dataDTO.setMailServer(mailServer);

		// password
		dataDTO.setPassword(password);

		// user name
		dataDTO.setUserName(userName);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage(reservationCreateFilledMessage);
		dataDTO.setSubject(reservationCreationSubject + reservation.getCustomer().getTitle() + " "
				+ reservation.getCustomer().getFirstName());

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	public static void notifyCancelReservation(Reservation reservation) throws MessagingException {
		// reservation creation email message
		// String reservationCreateMessage =
		// propertyUtil.getValue("reservation.delete");
		// String reservationCreateFilledMessage =
		// fillSaveOrUpdateReservationData(reservationCreateMessage,
		// reservation);

		// reservation creation subject.
		String reservationCreationSubject = propertyUtil.getValue("reservation.delete.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		// dataDTO.setTo(reservation.getCustomer().getEmail());

		// cc
		dataDTO.setCc(adminEmail + "," + managerEmailList);

		// mail server
		dataDTO.setMailServer(mailServer);

		// password
		dataDTO.setPassword(password);

		// user name
		dataDTO.setUserName(userName);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage("Please be inform Reservation Cancelled By User");
		dataDTO.setSubject("Reservation Cancelled");

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	private static String fillSaveOrUpdateReservationData(String emptyMessage, Reservation reservation) {

		CharSequence arrivalDate = "$1", departureDate = "$2", customerName = "$3", nationality = "$4", roomCategory = "$5";
		CharSequence roomType = "$6", arriveFrom = "$7", arriveFor = "$8", leaveAfter = "$9", accommodation = "$10";
		CharSequence noOfRooms = "$11", noOfAdults = "$12", noOfChildren = "$13", payment = "$14";

		emptyMessage = emptyMessage.replace(arrivalDate, CommonUtil.dateToString(reservation.getFromDate()));
		emptyMessage = emptyMessage.replace(departureDate, CommonUtil.dateToString(reservation.getToDate()));

		Customer customer = reservation.getCustomer();
		String fullName = customer.getTitle() + " " + customer.getFirstName() + " " + customer.getLasteName();
		emptyMessage = emptyMessage.replace(customerName, fullName);

		// emptyMessage = emptyMessage.replace(nationality,
		// reservation.getNationality());
		emptyMessage = emptyMessage.replace(roomCategory, reservation.getRoomCategory());
		emptyMessage = emptyMessage.replace(roomType, reservation.getRoomType());
		emptyMessage = emptyMessage.replace(arriveFrom, reservation.getArrivalFrom());

		emptyMessage = emptyMessage.replace(arriveFor, reservation.getArriveFor());
		emptyMessage = emptyMessage.replace(leaveAfter, reservation.getLeaveAfter());
		// emptyMessage = emptyMessage.replace(accommodation,
		// reservation.getAccomodationMode().getAccomodation());

		// emptyMessage = emptyMessage.replace(noOfRooms,
		// reservation.getNoOfRooms() + "");
		// emptyMessage = emptyMessage.replace(noOfAdults,
		// reservation.getAdults() + "");
		// emptyMessage = emptyMessage.replace(noOfChildren,
		// reservation.getChildren() + "");
		// emptyMessage = emptyMessage.replace(payment,
		// reservation.getPayment().getPaymentAmount() + "");

		return emptyMessage;
	}

	// ---------------------------- End of Reservation Creation
	// -----------------------------------------

	// ---------------------------- Recover Password
	// -----------------------------------------
	public static void notifyPasswordRecovery(Customer customer) throws MessagingException {
		// customer creation email message
		String passwordRecoveryMessage = propertyUtil.getValue("password.recovery.message");
		String passwordRecoveryFilledMessage = fillPasswordRecoveryData(passwordRecoveryMessage, customer);

		// Customer creation subject.
		String passwordRecoverySubject = propertyUtil.getValue("password.recovery.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		dataDTO.setTo(customer.getEmail());

		// mail server
		dataDTO.setMailServer(mailServer);

		// user name
		dataDTO.setUserName(userName);

		// password
		dataDTO.setPassword(password);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage(passwordRecoveryFilledMessage);
		dataDTO.setSubject(customer.getTitle() + " " + customer.getFirstName() + " " + passwordRecoverySubject);

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	private static String fillPasswordRecoveryData(String emptyMessage, Customer customer) {

		CharSequence name = "$1", userName = "$2", newPassword = "$3";

		String fullName = customer.getTitle() + " " + customer.getFirstName() + " " + customer.getLasteName();

		emptyMessage = emptyMessage.replace(name, fullName);
		emptyMessage = emptyMessage.replace(userName, customer.getUserName());
		emptyMessage = emptyMessage.replace(newPassword, customer.getPassword());

		return emptyMessage;
	}

	// ---------------------------- End of Recover Password
	// -----------------------------------------

	public static void sendSpecificEmail(EmailDataDTO emailDataDTO) throws MessagingException {
		// mail server
		emailDataDTO.setMailServer(mailServer);

		if (emailDataDTO.getFrom() == null || emailDataDTO.getFrom().equals("")) {
			// from
			emailDataDTO.setFrom(systemEmail);

			// user name
			emailDataDTO.setUserName(userName);

			// user name
			emailDataDTO.setPassword(password);

		}

		emailDataDTO.setLogoLocation(logoLocation);
		EmailUtil.sendmail(emailDataDTO);
	}

	// ---------------------------- Inquiry.
	// -----------------------------------------
	public static void sendInquire(HttpServletRequest httpServletRequest) throws MessagingException {
		// customer creation email message
		String inquiryMessage = propertyUtil.getValue("inquiry.message");
		String inquiryFilledMessage = fillInquireData(inquiryMessage, httpServletRequest);

		// Customer creation subject.
		String inquirySubject = propertyUtil.getValue("inquiry.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		dataDTO.setTo(adminEmail);

		// mail server
		dataDTO.setMailServer(mailServer);

		// user name
		dataDTO.setUserName(userName);

		// password
		dataDTO.setPassword(password);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage(inquiryFilledMessage);
		dataDTO.setSubject(inquirySubject + " " + httpServletRequest.getParameter("name"));

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	private static String fillInquireData(String emptyMessage, HttpServletRequest httpServletRequest) {

		CharSequence senderName = "$1", contactNumber = "$2", email = "$3", inquiry = "$4";

		emptyMessage = emptyMessage.replace(senderName, httpServletRequest.getParameter("name"));
		emptyMessage = emptyMessage.replace(contactNumber, httpServletRequest.getParameter("phone"));
		emptyMessage = emptyMessage.replace(email, httpServletRequest.getParameter("email"));
		emptyMessage = emptyMessage.replace(inquiry, httpServletRequest.getParameter("description"));

		return emptyMessage;
	}

	// ---------------------------- Customer Creation
	// -----------------------------------------
	public static void notifyCustomerDeletion(Customer customer) throws MessagingException {

		String deletionMessage = propertyUtil.getValue("user.remove.message");
		String deletionFilledMessage = fillUserDeleteData(deletionMessage, customer);

		// Customer deletion subject.
		String deletionSubject = propertyUtil.getValue("user.remove.subject");

		EmailDataDTO dataDTO = new EmailDataDTO();

		// from
		dataDTO.setFrom(systemEmail);

		// to
		dataDTO.setTo(customer.getEmail());

		// mail server
		dataDTO.setMailServer(mailServer);

		// user name
		dataDTO.setUserName(userName);

		// password
		dataDTO.setPassword(password);

		dataDTO.setLogoLocation(logoLocation);

		dataDTO.setMessage(deletionFilledMessage);
		dataDTO.setSubject(deletionSubject);

		// from,to,CC,message,subject,host
		EmailUtil.sendmail(dataDTO);
	}

	private static String fillUserDeleteData(String emptyMessage, Customer customer) {

		CharSequence userName = "$1";

		emptyMessage = emptyMessage.replace(userName, customer.getFirstName());

		return emptyMessage;
	}
}
