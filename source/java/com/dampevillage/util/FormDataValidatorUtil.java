package com.dampevillage.util;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.dampevillage.admin.hotelprofile.formbean.HotelProfileFormBean;
import com.dampevillage.admin.room.formbean.RoomFormBean;
import com.dampevillage.admin.roomcatagory.formbean.RoomCategoryFormBean;
import com.dampevillage.admin.roompool.formbean.RoomPoolFormBean;
import com.dampevillage.admin.roomrate.formbean.RoomRateFormBean;
import com.dampevillage.admin.roomtype.formbean.RoomTypeFormBean;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.customer.formbean.CustomerFormBean;
import com.dampevillage.log.formbean.LoginFormBean;
import com.dampevillage.log.formbean.PasswordRecoverFormBean;
import com.dampevillage.payment.formbean.PaymentFormBean;
import com.dampevillage.reservation.formbean.ReservationFormBean;
import com.dampevillage.reservation.formbean.SearchReservationFormBean;
import com.dampevillage.search.formbean.SearchBean;

public class FormDataValidatorUtil {

	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	// ---------------------- Report --------------------------------

	// ------------------------------------------------------------------------------------------------------------------------
	public static boolean isReportDateRangeDataValid(HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkDateRangeMandatoryFields(request, isValid);

		if (isValid) {
			isValid = checkReportDateRangeLength(request, isValid);
		}

		if (isValid) {
			// Validate date formats

			if (!validateDateFormat(request.getParameter("fromDate").trim())) {

				request.setAttribute("fromInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "From Date"));

				isValid = false;
			}
			if (!validateDateFormat(request.getParameter("toDate").trim())) {

				request.setAttribute("toInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "To Date"));

				isValid = false;
			}
		}

		if (isValid) {
			// Check date range
			isValid = validateDateRange(request.getParameter("fromDate"), request.getParameter("toDate"), request,
					isValid);
		}
		return isValid;
	}

	private static boolean checkReportDateRangeLength(HttpServletRequest request, boolean isValid) {

		if (isFieldLengthExceed(request.getParameter("fromDate").trim(), 10)) {
			request.setAttribute("fromDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "From Date", 10));
			isValid = false;
		}

		if (isFieldLengthExceed(request.getParameter("toDate").trim(), 10)) {
			request.setAttribute("toDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "To Date", 10));
			isValid = false;
		}
		return isValid;
	}

	private static boolean checkDateRangeMandatoryFields(HttpServletRequest request, boolean isValid) {

		if (isNullOrEmpty(request.getParameter("fromDate"))) {
			request.setAttribute("fromDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "From Date"));
			isValid = false;
		}

		if (isNullOrEmpty(request.getParameter("toDate"))) {
			request.setAttribute("toDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "To Date"));
			isValid = false;
		}
		return isValid;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------

	public static boolean isReportFormDataValid(HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkReportMandatoryFields(request, isValid);

		if (isValid) {
			isValid = checkReportFieldLength(request, isValid);
		}
		if (isValid) {

			// Check first name
			if (!validateName(request.getParameter("firstname").trim())) {
				request.setAttribute("firstNameInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.name"));
				isValid = false;
			}

			// Check last name
			if (!validateName(request.getParameter("lastname").trim())) {
				request.setAttribute("lastNameInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.name"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean checkReportFieldLength(HttpServletRequest request, boolean isValid) {
		if (isFieldLengthExceed(request.getParameter("firstname").trim(), 25)) {
			request.setAttribute("firstNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "First Name", 25));
			isValid = false;
		}

		if (isFieldLengthExceed(request.getParameter("lastname").trim(), 25)) {
			request.setAttribute("lastNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Last Name", 25));
			isValid = false;
		}
		return isValid;
	}

	private static boolean checkReportMandatoryFields(HttpServletRequest request, boolean isValid) {
		if (isNullOrEmpty(request.getParameter("firstname"))) {
			request.setAttribute("firstNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "First name"));
			isValid = false;
		}

		if (isNullOrEmpty(request.getParameter("lastname"))) {
			request.setAttribute("lastNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Last Name"));
			isValid = false;
		}
		return isValid;
	}

	// ----------------------- End of Report -----------------------------

	// ----------------------------- Email Notification
	// ------------------------------------------------------------
	public static boolean isEmailNotificationDataValid(HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkEmailNotificationMandatoryFields(request, isValid);

		if (isValid) {

			String toMailList = (String) request.getParameter("to");
			String ccMailList = (String) request.getParameter("cc");
			String bccMailList = (String) request.getParameter("bcc");

			// Check email
			if ((toMailList != null && !toMailList.equals(""))
					&& !validateEmailList(((String) request.getParameter("to")))) {
				request.setAttribute("toMmailInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.email"));
				isValid = false;
			}
			if ((ccMailList != null && !ccMailList.equals(""))
					&& !validateEmailList(((String) request.getParameter("cc")))) {
				request.setAttribute("ccMmailInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.email"));
				isValid = false;
			}
			if ((bccMailList != null && !bccMailList.equals(""))
					&& !validateEmailList(((String) request.getParameter("bcc")))) {
				request.setAttribute("bccMmailInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.email"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean validateEmailList(String mailList) {

		boolean isValid = true;

		String arrMailList[] = mailList.split(",");
		for (String currentMail : arrMailList) {
			if (!validateEmail(currentMail)) {
				isValid = false;
			}
		}
		return isValid;
	}

	private static boolean checkEmailNotificationMandatoryFields(HttpServletRequest request, boolean isValid) {
		// Null check

		if (isNullOrEmpty(request.getParameter("all"))
				&& (isNullOrEmpty(request.getParameter("to")) && isNullOrEmpty(request.getParameter("cc")) && isNullOrEmpty(request
						.getParameter("bcc")))) {
			request.setAttribute("recipientEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Recipient"));
			isValid = false;
		}

		if (isNullOrEmpty(request.getParameter("subject"))) {
			request.setAttribute("subjectEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Subject"));
			isValid = false;
		}

		if (isNullOrEmpty(request.getParameter("rte1"))) {
			request.setAttribute("messageEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Message"));
			isValid = false;
		}

		if (isNullOrEmpty(request.getParameter("system")) && isNullOrEmpty(request.getParameter("password"))) {
			request.setAttribute("passwordEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Password"));
			isValid = false;
		}

		return isValid;
	}

	// ----------------------------- Email Notification
	// ------------------------------------------------------------

	// ----------------------------- password recover
	// ------------------------------------------------------------
	public static boolean isPasswordRecoverDataValid(PasswordRecoverFormBean passwordRecoverFormBean,
			HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkPasswordRecoverMandatoryFields(passwordRecoverFormBean, request, isValid);

		if (isValid) {
			isValid = checkPasswordRecoverFieldsLength(passwordRecoverFormBean, request, isValid);
		}

		if (isValid) {
			// Check email
			if (!validateEmail(passwordRecoverFormBean.getEmail())) {
				request.setAttribute("emailInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.email"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean checkPasswordRecoverMandatoryFields(PasswordRecoverFormBean passwordRecoverFormBean,
			HttpServletRequest request, boolean isValid) {
		// Null check

		if (isNullOrEmpty(passwordRecoverFormBean.getEmail())) {
			request.setAttribute("emailEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Email"));
			isValid = false;
		}

		if (isNullOrEmpty(passwordRecoverFormBean.getRecoverAnswer())) {
			request.setAttribute("answerEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Answer"));
			isValid = false;
		}

		if (isNullOrEmpty(passwordRecoverFormBean.getRecoverQuestion())) {
			request.setAttribute("questionEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Question"));
			isValid = false;
		}

		if (isNullOrEmpty(passwordRecoverFormBean.getUserName())) {
			request.setAttribute("userNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "User Name"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkPasswordRecoverFieldsLength(PasswordRecoverFormBean passwordRecoverFormBean,
			HttpServletRequest request, boolean isValid) {

		if (isFieldLengthExceed(passwordRecoverFormBean.getEmail(), 100)) {
			request.setAttribute("emailTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Email", 100));
			isValid = false;
		}

		if (isFieldLengthExceed(passwordRecoverFormBean.getRecoverAnswer(), 100)) {
			request.setAttribute("answerTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Answer", 100));
			isValid = false;
		}

		if (isFieldLengthExceed(passwordRecoverFormBean.getUserName(), 15)) {
			request.setAttribute("userNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "User Name", 15));
			isValid = false;
		}

		return isValid;
	}

	// ----------------------------- End of password recover
	// ------------------------------------------------------------

	// -------------------------------------------------- Payment

	public static boolean isPaymentDataValid(PaymentFormBean paymentFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkPaymentMandatoryFields(paymentFormBean, request, isValid);

		if (isValid) {
			isValid = checkPaymentFieldsLength(paymentFormBean, request, isValid);
		}

		if (isValid) {
			// Check email
			if (!validateEmail(paymentFormBean.getEmail())) {
				request.setAttribute("emailInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.email"));
				isValid = false;
			}

			// Card Holder Name
			if (!validateName(paymentFormBean.getCardHolderName())) {
				request.setAttribute("cardHolderNameError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.name"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean checkPaymentMandatoryFields(PaymentFormBean paymentFormBean, HttpServletRequest request,
			boolean isValid) {
		// Null check

		if (isNullOrEmpty(paymentFormBean.getCardHolderName())) {
			request.setAttribute("holderNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Card Holder Name"));
			isValid = false;
		}

		if (isNullOrEmpty(paymentFormBean.getCardNumber())) {
			request.setAttribute("cardNumberEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Card Number"));
			isValid = false;
		}

		if (isNullOrEmpty(paymentFormBean.getCardType())) {
			request.setAttribute("cardTypeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Card Type"));
			isValid = false;
		}

		if (isNullOrEmpty(paymentFormBean.getEmail())) {
			request.setAttribute("emailEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Email"));
			isValid = false;
		}

		if (isNullOrEmpty(paymentFormBean.getExpireMonth())) {
			request.setAttribute("expireMonthEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Card Expire Month"));
			isValid = false;
		}

		if (isNullOrEmpty(paymentFormBean.getExpireYear())) {
			request.setAttribute("expireYearEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Card Expire Year"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkPaymentFieldsLength(PaymentFormBean paymentFormBean, HttpServletRequest request,
			boolean isValid) {

		if (isFieldLengthExceed(paymentFormBean.getCardHolderName(), 25)) {
			request.setAttribute("holderNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Card Holder Name", 25));
			isValid = false;
		}

		if (isFieldLengthExceed(paymentFormBean.getEmail(), 45)) {
			request.setAttribute("emailTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Email", 45));
			isValid = false;
		}

		return isValid;
	}

	// -------------------------------- End of Payment
	// -------------------------------------------------

	// ----------------------------- Reservation Search
	// ------------------------------------------------------------
	public static boolean isReservationSearchDataValid(SearchReservationFormBean searchReservationFormBean,
			HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkReservationSearchMandatoryFields(searchReservationFormBean, request, isValid);

		if (isValid) {
			isValid = checkReservationSearchFieldsLength(searchReservationFormBean, request, isValid);
		}

		if (isValid) {
			// Validate date formats

			if (!validateDateFormat(searchReservationFormBean.getFromDate())) {

				request.setAttribute("fromInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "From Date"));

				isValid = false;
			}
			if (!validateDateFormat(searchReservationFormBean.getToDate())) {

				request.setAttribute("toInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "To Date"));

				isValid = false;
			}
		}

		if (isValid) {
			// Check date range
			isValid = validateDateRange(searchReservationFormBean.getFromDate(), searchReservationFormBean.getToDate(),
					request, isValid);
		}

		return isValid;
	}

	private static boolean checkReservationSearchMandatoryFields(SearchReservationFormBean searchReservationFormBean,
			HttpServletRequest request, boolean isValid) {
		// Null check

		if (isNullOrEmpty(searchReservationFormBean.getCustomerName())) {
			request.setAttribute("userNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "User name"));
			isValid = false;
		}

		if (isNullOrEmpty(searchReservationFormBean.getFromDate())) {
			request.setAttribute("fromDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "From Date"));
			isValid = false;
		}

		if (isNullOrEmpty(searchReservationFormBean.getToDate())) {
			request.setAttribute("toDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "To Date"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkReservationSearchFieldsLength(SearchReservationFormBean searchReservationFormBean,
			HttpServletRequest request, boolean isValid) {

		if (isFieldLengthExceed(searchReservationFormBean.getCustomerName(), 15)) {
			request.setAttribute("userNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "User Name", 15));
			isValid = false;
		}

		if (isFieldLengthExceed(searchReservationFormBean.getFromDate(), 10)) {
			request.setAttribute("fromDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "From Date", 10));
			isValid = false;
		}

		if (isFieldLengthExceed(searchReservationFormBean.getToDate(), 10)) {
			request.setAttribute("toDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "To Date", 10));
			isValid = false;
		}

		return isValid;
	}

	// -------------------------------- End of Reservation Search
	// -------------------------------------------------

	// ----------------------------- Room Available Check
	// ------------------------------------------------------------
	public static boolean isRoomAvailableCheckDataValid(SearchBean searchBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkRoomAvailableCheckMandatoryFields(searchBean, request, isValid);

		if (isValid) {
			isValid = checkRoomAvailableCheckFieldsLength(searchBean, request, isValid);
		}

		if (isValid) {
			// Validate date formats

			if (!validateDateFormat(searchBean.getFromDateSearch().trim())) {

				request.setAttribute("fromInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "From Date"));

				isValid = false;
			}
		}
		if (isValid) {
			if (!validateDateFormat(searchBean.getToDateSearch().trim())) {

				request.setAttribute("toInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "To Date"));

				isValid = false;
			}
		}

		if (isValid && !isGreaterThanToday(searchBean.getFromDateSearch())) {
			// from date after second.
			request.setAttribute("fromDateGreaterThanTodayError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.date.less"), "From Date"));
			isValid = false;
		}

		if (isValid && !isGreaterThanToday(searchBean.getToDateSearch())) {
			request.setAttribute("toDateGreaterThanTodayError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.date.less"), "To Date"));
			isValid = false;
		}

		if (isValid) {
			// Check date range
			isValid = validateDateRange(searchBean.getFromDateSearch(), searchBean.getToDateSearch(), request, isValid);
		}

		return isValid;
	}

	private static boolean checkRoomAvailableCheckMandatoryFields(SearchBean searchBean, HttpServletRequest request,
			boolean isValid) {
		// Null check

		if (isNullOrEmpty(searchBean.getRoomCategory())) {
			request.setAttribute("roomCategoryEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Category"));
			isValid = false;
		}

		if (isNullOrEmpty(searchBean.getRoomType())) {
			request.setAttribute("roomTypeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Type"));
			isValid = false;
		}

		if (isNullOrEmpty(searchBean.getFromDateSearch())) {
			request.setAttribute("fromDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "From Date"));
			isValid = false;
		}

		if (isNullOrEmpty(searchBean.getToDateSearch())) {
			request.setAttribute("toDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "To Date"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkRoomAvailableCheckFieldsLength(SearchBean searchBean, HttpServletRequest request,
			boolean isValid) {

		if (isFieldLengthExceed(searchBean.getRoomType(), 20)) {
			request.setAttribute("roomTypeTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Room Type", 20));
			isValid = false;
		}

		if (isFieldLengthExceed(searchBean.getRoomCategory(), 20)) {
			request.setAttribute("roomCategoryTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Room Category", 20));
			isValid = false;
		}

		if (isFieldLengthExceed(searchBean.getFromDateSearch().trim(), 10)) {
			request.setAttribute("fromDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "From Date", 10));
			isValid = false;
		}

		if (isFieldLengthExceed(searchBean.getToDateSearch().trim(), 10)) {
			request.setAttribute("toDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "To Date", 10));
			isValid = false;
		}

		return isValid;
	}

	// -------------------------------- End Room Available Check
	// -------------------------------------------------

	// ----------------------------- Room Reservation
	// ------------------------------------------------------------
	public static boolean isRoomReservationDataValid(ReservationFormBean reservationFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkRoomReservationMandatoryFields(reservationFormBean, request, isValid);

		if (isValid) {
			isValid = checkRoomReservationFieldsLength(reservationFormBean, request, isValid);
		}

		if (isValid) {

		}

		if (isValid) {

			// Check date range
			isValid = validateDateRange(reservationFormBean.getFromDate().trim(), reservationFormBean.getToDate().trim(), request,
					isValid);
		}

		if (isValid && !isGreaterThanToday(reservationFormBean.getFromDate().trim())) {
			// from date after second.
			request.setAttribute("fromDateGreaterThanTodayError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.date.less"), "Check-in Date"));
			isValid = false;
		}

		if (isValid && !isGreaterThanToday(reservationFormBean.getToDate().trim())) {
			request.setAttribute("toDateGreaterThanTodayError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.date.less"), "Check-out Date"));
			isValid = false;
		}

		if (isValid) {
			int roomTypeId = reservationFormBean.getRoomTypeId();
			int noOfAdults = reservationFormBean.getAdults();
			int children = reservationFormBean.getChildren();
			int noOfRooms = reservationFormBean.getNoOfRooms();

			// Single room max 1 adult.
			if (roomTypeId == 1 && noOfAdults > noOfRooms) {
				request.setAttribute("adultsExceedsError", fillMaximumExceedErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("reservation.adults.exceed"), reservationFormBean.getRoomType(), 1));
				isValid = false;
			}

			// Double room max 2 adult.
			if (roomTypeId == 2 && noOfAdults > (noOfRooms * 2)) {
				request.setAttribute("adultsExceedsError", fillMaximumExceedErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("reservation.adults.exceed"), reservationFormBean.getRoomType(), 2));
				isValid = false;
			}

			// Triple room max 3 adult.
			if (roomTypeId == 3 && noOfAdults > (noOfRooms * 3)) {
				request.setAttribute("adultsExceedsError", fillMaximumExceedErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("reservation.adults.exceed"), reservationFormBean.getRoomType(), 3));
				isValid = false;
			}

			// Single room max 2 children.
			if (roomTypeId == 1 && children > (noOfRooms * 2)) {
				request.setAttribute("childrenExceedsError", fillMaximumExceedErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("reservation.children.exceed"), reservationFormBean.getRoomType(), 2));
				isValid = false;
			}

			// Double room max 2 children.
			if (roomTypeId == 2 && children > (noOfRooms * 2)) {
				request.setAttribute("childrenExceedsError", fillMaximumExceedErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("reservation.children.exceed"), reservationFormBean.getRoomType(), 2));
				isValid = false;
			}

			// Triple room max 3 children.
			if (roomTypeId == 3 && children > (noOfRooms * 3)) {
				request.setAttribute("childrenExceedsError", fillMaximumExceedErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("reservation.children.exceed"), reservationFormBean.getRoomType(), 3));
				isValid = false;
			}
		}
		return isValid;
	}

	private static boolean checkRoomReservationMandatoryFields(ReservationFormBean reservationFormBean,
			HttpServletRequest request, boolean isValid) {
		// Null check

		if (isNullOrEmpty(reservationFormBean.getAirportPickup())) {
			request.setAttribute("airportPickupEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Airport Pickup"));
			isValid = false;
		}

		if (isNullOrEmpty(reservationFormBean.getArrivalFrom())) {
			request.setAttribute("arrivalFromEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Arrival From"));
			isValid = false;
		}

		if (isNullOrEmpty(reservationFormBean.getArriveFor())) {
			request.setAttribute("arriveForEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Arrive Fro"));
			isValid = false;
		}

		if (isNullOrEmpty(reservationFormBean.getFromDate())) {
			request.setAttribute("fromDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Check-in Date"));
			isValid = false;
		}

		if (isNullOrEmpty(reservationFormBean.getLeaveAfter())) {
			request.setAttribute("leaveAfterEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Leave After"));
			isValid = false;
		}

		if (isNullOrEmpty(reservationFormBean.getNationality())) {
			request.setAttribute("nationalityEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Nationality"));
			isValid = false;
		}

		if (isNullOrEmpty(reservationFormBean.getToDate())) {
			request.setAttribute("toDateEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Check-out Date"));
			isValid = false;
		}

		if (isZeroOrMinus(reservationFormBean.getAccomodationMode())) {
			request.setAttribute("accommodationModeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Accommodation"));
			isValid = false;
		}

		if (isZeroOrMinus(reservationFormBean.getAdults())) {
			request.setAttribute("adultsEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.zero.minus"), "Adults"));
			isValid = false;
		}

		if (isMinus(reservationFormBean.getChildren())) {
			request.setAttribute("childernEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.minus"), "Childern"));
			isValid = false;
		}

		if (isZeroOrMinus(reservationFormBean.getNoOfRooms())) {
			request.setAttribute("roomsEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.zero.minus"), "No of Rooms"));
			isValid = false;
		}

		// Validate date formats

		if (!validateDateFormat(reservationFormBean.getFromDate())) {

			request.setAttribute("fromInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.invalid.date.format"), "Check-in Date"));

			isValid = false;
		}
		if (!validateDateFormat(reservationFormBean.getToDate())) {

			request.setAttribute("toInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.invalid.date.format"), "Check-out Date"));

			isValid = false;
		}
		return isValid;
	}

	private static boolean checkRoomReservationFieldsLength(ReservationFormBean reservationFormBean,
			HttpServletRequest request, boolean isValid) {

		if (isFieldLengthExceed(reservationFormBean.getFromDate().trim(), 10)) {
			request.setAttribute("fromDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Check-in Date", 10));
			isValid = false;
		}

		if (isFieldLengthExceed(reservationFormBean.getToDate().trim(), 10)) {
			request.setAttribute("toDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Check-out Date", 10));
			isValid = false;
		}
		return isValid;
	}

	// -------------------------------- End of Room Reservation
	// -------------------------------------------------

	// ----------------------------- Room Pool
	// ------------------------------------------------------------
	public static boolean isRoomPoolDataValid(RoomPoolFormBean roomPoolFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkRoomPoolMandatoryFields(roomPoolFormBean, request, isValid);

		if (isValid) {
			isValid = checkRoomPoolFieldsLength(roomPoolFormBean, request, isValid);
		}

		return isValid;
	}

	private static boolean checkRoomPoolMandatoryFields(RoomPoolFormBean roomPoolFormBean, HttpServletRequest request,
			boolean isValid) {
		// Null check

		if (isNullOrEmpty(roomPoolFormBean.getRoomCategory())) {
			request.setAttribute("roomCategoryEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Category"));
			isValid = false;
		}

		if (isNullOrEmpty(roomPoolFormBean.getRoomType())) {
			request.setAttribute("roomTypeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Type"));
			isValid = false;
		}

		if (isZeroOrMinus(roomPoolFormBean.getTotalRooms())) {
			request.setAttribute("totalRoomsEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.zero.minus"), "No of Rooms"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkRoomPoolFieldsLength(RoomPoolFormBean roomPoolFormBean, HttpServletRequest request,
			boolean isValid) {
		if (isFieldLengthExceed(roomPoolFormBean.getRoomType(), 20)) {
			request.setAttribute("roomTypeTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Room Type", 20));
			isValid = false;
		}

		if (isFieldLengthExceed(roomPoolFormBean.getRoomCategory(), 20)) {
			request.setAttribute("roomCategoryTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Room Category", 20));
			isValid = false;
		}
		return isValid;
	}

	// -------------------------------- End of Room pool
	// -------------------------------------------------

	// ---------- User registration -----------------------------------
	public static boolean isUserRegisrationDataValid(CustomerFormBean customerFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkUserRegisrationMandatoryFields(customerFormBean, request, isValid);

		if (isValid) {
			isValid = checkUserRegisrationFieldsLength(customerFormBean, request, isValid);
		}

		if (isValid) {
			// Check is 2 passwords match
			if (!(customerFormBean.getPassword().equals(customerFormBean.getPasswordRe()))) {
				request.setAttribute("twoPasswordsMismatchError", resourceBundleUtil
						.getLocaleSpecificValue("user.registration.passwords.mismatch"));
				request.setAttribute("passwordRe", customerFormBean.getPasswordRe());
				isValid = false;
			}

			// Check first name
			if (!validateName(customerFormBean.getFirstName())) {
				request.setAttribute("firstNameInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.name"));
				isValid = false;
			}

			// Check last name
			if (!validateName(customerFormBean.getLasteName())) {
				request.setAttribute("lastNameInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.name"));
				isValid = false;
			}

			// Check email
			if (!validateEmail(customerFormBean.getEmail())) {
				request.setAttribute("emailInvalidError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.email"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean checkUserRegisrationMandatoryFields(CustomerFormBean customerFormBean,
			HttpServletRequest request, boolean isValid) {
		// Null check
		if (isNullOrEmpty(customerFormBean.getAddressOne())) {
			request.setAttribute("addressOneEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Address One"));
			isValid = false;
		}
		
		if (isNullOrEmpty(customerFormBean.getAddressTwo())) {
			request.setAttribute("addressTwoEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Address Two"));
			isValid = false;
		}
		
		if (isNullOrEmpty(customerFormBean.getStreetNumber())) {
			request.setAttribute("streeteEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Street Number"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getContactMobile())) {
			request.setAttribute("contactMobileEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Contact Mobile"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getCountry())) {
			request.setAttribute("countryEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Country"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getEmail())) {
			request.setAttribute("emailEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Email"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getFirstName())) {
			request.setAttribute("firstNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "First Name"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getLasteName())) {
			request.setAttribute("lastNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Last Name"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getNicPassportNumber())) {
			request.setAttribute("nicEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "NIC/Passport Number"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getPassword())) {
			request.setAttribute("passwordEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Password"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getPasswordRe())) {
			request.setAttribute("passwordReEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Password re Enter"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getRecoverAnswer())) {
			request.setAttribute("recoverAnswerEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Recover Answer"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getRecoverQuestion())) {
			request.setAttribute("recoverQuestionEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Recover Question"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getTitle())) {
			request.setAttribute("titleEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Title"));
			isValid = false;
		}

		if (isNullOrEmpty(customerFormBean.getUserName())) {
			request.setAttribute("userNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "User Name"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkUserRegisrationFieldsLength(CustomerFormBean customerFormBean,
			HttpServletRequest request, boolean isValid) {

		// Address 1
		if (isFieldLengthExceed(customerFormBean.getAddressOne(), 255)) {
			request.setAttribute("addressOneTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Address One", 255));
			isValid = false;
		}
		
		// Address 2
		if (isFieldLengthExceed(customerFormBean.getAddressTwo(), 255)) {
			request.setAttribute("addressTwoTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Address Two", 255));
			isValid = false;
		}
		
		// Street
		if (isFieldLengthExceed(customerFormBean.getStreetNumber(), 4)) {
			request.setAttribute("streetTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Street", 4));
			isValid = false;
		}

		// Address
		if (isFieldLengthExceed(customerFormBean.getTitle(), 5)) {
			request.setAttribute("addressTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Title", 5));
			isValid = false;
		}

		// contact land
		if (isFieldLengthExceed(customerFormBean.getContactLand(), 45)) {
			request.setAttribute("contactLandTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Contact Land", 45));
			isValid = false;
		}

		// mobile
		if (isFieldLengthExceed(customerFormBean.getContactMobile(), 45)) {
			request.setAttribute("contactMobileTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Contact Mobile", 45));
			isValid = false;
		}

		// email
		if (isFieldLengthExceed(customerFormBean.getEmail(), 45)) {
			request.setAttribute("emailTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Email", 45));
			isValid = false;
		}
		// f name
		if (isFieldLengthExceed(customerFormBean.getFirstName(), 25)) {
			request.setAttribute("firstNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "First Name", 25));
			isValid = false;
		}

		// l name
		if (isFieldLengthExceed(customerFormBean.getLasteName(), 25)) {
			request.setAttribute("lastNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Last Name", 25));
			isValid = false;
		}

		// nic
		if (isFieldLengthExceed(customerFormBean.getNicPassportNumber(), 30)) {
			request.setAttribute("nicTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "NIC/Passport number", 30));
			isValid = false;
		}

		// pwd
		if (isFieldLengthExceed(customerFormBean.getPassword(), 20)) {
			request.setAttribute("passwordTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Password", 20));
			isValid = false;
		}

		// pwd re
		if (customerFormBean.getPassword().length() < 5) {
			request.setAttribute("passwordTooShortError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooShort"), "Password", 5));
			isValid = false;
		}
		// pwd re
		if ((isFieldLengthExceed(customerFormBean.getPasswordRe(), 20))) {
			request.setAttribute("passwordReTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Password re enter", 20));
			isValid = false;
		}

		if (customerFormBean.getPasswordRe().length() < 5) {
			request.setAttribute("passwordReTooShortError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooShort"), "Password", 5));
			isValid = false;
		}

		// postal code
		if (isFieldLengthExceed(customerFormBean.getPostalCode(), 20)) {
			request.setAttribute("postalCodeTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Postal Code", 20));
			isValid = false;
		}

		// recover answer
		if (isFieldLengthExceed(customerFormBean.getRecoverAnswer(), 30)) {
			request.setAttribute("recoverAnswerTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Recover Answer", 30));
			isValid = false;
		}

		// state
		if (isFieldLengthExceed(customerFormBean.getState(), 20)) {
			request.setAttribute("stateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "State", 20));
			isValid = false;
		}
		// u name
		if (isFieldLengthExceed(customerFormBean.getUserName(), 20)) {
			request.setAttribute("userNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "User name", 20));
			isValid = false;
		}

		return isValid;
	}

	// ---------- End of User registration -----------------------------------

	// ---------- Room Rate -----------------------------------
	public static boolean isRoomRateDataValid(RoomRateFormBean roomRateFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkRoomRateMandatoryFields(roomRateFormBean, request, isValid);

		if (isValid) {
			isValid = checkRoomRateFieldsLength(roomRateFormBean, request, isValid);
		}

		if (isValid) {
			if (!validateDateFormat(roomRateFormBean.getValidFrom())) {
				request.setAttribute("fromInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "Valid From"));
				isValid = false;
			}

			if (!validateDateFormat(roomRateFormBean.getValidTo())) {
				request.setAttribute("toInvalidDateFormatError", fillEmptyErrorMessage(resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.date.format"), "Valid To"));
				isValid = false;
			}
		}

		if (isValid) {

			isValid = validateDateRange(roomRateFormBean.getValidFrom(), roomRateFormBean.getValidTo(), request,
					isValid);

			if (roomRateFormBean.getPrice() <= 0) {
				request.setAttribute("roomPriceZeroError", resourceBundleUtil
						.getLocaleSpecificValue("roomRate.price.zero"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean checkRoomRateMandatoryFields(RoomRateFormBean roomRateFormBean, HttpServletRequest request,
			boolean isValid) {
		// Null check
		if (isNullOrEmpty(roomRateFormBean.getAccomodationMode())) {
			request.setAttribute("accormodationModeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Accomodation Mode"));
			isValid = false;
		}

		if (isNullOrEmpty(roomRateFormBean.getRoomCategory())) {
			request.setAttribute("roomCategoryEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Category"));
			isValid = false;
		}

		if (isNullOrEmpty(roomRateFormBean.getRoomType())) {
			request.setAttribute("roomTypeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Type"));
			isValid = false;
		}

		if (isNullOrEmpty(roomRateFormBean.getValidFrom())) {
			request.setAttribute("validFromEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Valid From"));
			isValid = false;
		}

		if (isNullOrEmpty(roomRateFormBean.getValidTo())) {
			request.setAttribute("validToEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Valid To"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkRoomRateFieldsLength(RoomRateFormBean roomRateFormBean, HttpServletRequest request,
			boolean isValid) {
		if (isFieldLengthExceed(roomRateFormBean.getValidFrom(), 10)) {
			request.setAttribute("fromDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Valid From", 10));
			isValid = false;
		}
		if (isFieldLengthExceed(roomRateFormBean.getValidTo(), 10)) {
			request.setAttribute("toDateTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Valid To", 10));
			isValid = false;
		}
		return isValid;
	}

	// ---------------------- End of Room Rate -----------------------
	// ----------------------------- Room Type
	// ------------------------------------------------------------
	public static boolean isRoomTypeDataValid(RoomTypeFormBean roomTypeFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkRoomTypeMandatoryFields(roomTypeFormBean, request, isValid);

		if (isValid) {
			isValid = checkRoomTypeFieldsLength(roomTypeFormBean, request, isValid);
		}

		if (isValid) {
			if (!validateName(roomTypeFormBean.getRoomType())) {
				request.setAttribute("invalidTypeNameError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.name"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean checkRoomTypeMandatoryFields(RoomTypeFormBean roomTypeFormBean, HttpServletRequest request,
			boolean isValid) {
		// Null check
		if (isNullOrEmpty(roomTypeFormBean.getRoomType())) {
			request.setAttribute("roomTypeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Type"));
			isValid = false;
		}

		if (isNullOrEmpty(roomTypeFormBean.getDescription())) {
			request.setAttribute("descriptionEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Description"));
			isValid = false;
		}
		return isValid;
	}

	private static boolean checkRoomTypeFieldsLength(RoomTypeFormBean roomTypeFormBean, HttpServletRequest request,
			boolean isValid) {
		if (isFieldLengthExceed(roomTypeFormBean.getRoomType(), 45)) {
			request.setAttribute("roomTypeTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Room Type", 45));
			isValid = false;
		}
		if (isFieldLengthExceed(roomTypeFormBean.getDescription(), 4500)) {
			request.setAttribute("descriptionTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Description", 4500));
			isValid = false;
		}
		return isValid;
	}

	// -------------------------------- End of Room Type
	// -------------------------------------------------

	// ------------------------------- Room Category
	// ------------------------------

	public static boolean isRoomCaregoryDataValid(RoomCategoryFormBean roomCategoryFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkRoomCategoryMandatoryFields(roomCategoryFormBean, request, isValid);

		if (isValid) {
			isValid = checkRoomCategoryFieldsLength(roomCategoryFormBean, request, isValid);
		}

		if (isValid) {
			if (!validateName(roomCategoryFormBean.getCategoryType())) {
				request.setAttribute("invalidCategoryNameError", resourceBundleUtil
						.getLocaleSpecificValue("room.invalid.category.name"));
				isValid = false;
			}
		}

		return isValid;
	}

	private static boolean checkRoomCategoryMandatoryFields(RoomCategoryFormBean roomCategoryFormBean,
			HttpServletRequest request, boolean isValid) {
		// Null check
		if (isNullOrEmpty(roomCategoryFormBean.getCategoryType())) {
			request.setAttribute("roomCategoryEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Category"));
			isValid = false;
		}

		if (isNullOrEmpty(roomCategoryFormBean.getDescription())) {
			request.setAttribute("descriptionEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Description"));
			isValid = false;
		}
		return isValid;
	}

	private static boolean checkRoomCategoryFieldsLength(RoomCategoryFormBean roomCategoryFormBean,
			HttpServletRequest request, boolean isValid) {
		if (isFieldLengthExceed(roomCategoryFormBean.getCategoryType(), 45)) {
			request.setAttribute("roomCategoryTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Category Type", 45));
			isValid = false;
		}
		if (isFieldLengthExceed(roomCategoryFormBean.getDescription(), 4500)) {
			request.setAttribute("descriptionTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Description", 4500));
			isValid = false;
		}
		return isValid;
	}

	// ------------------------------- End of Room Category
	// ---------------------------

	// ------------------------ Room

	public static boolean isRoomDataValid(RoomFormBean roomFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkRoomDataMandatoryFields(roomFormBean, request, isValid);

		if (isValid) {
			isValid = checkRoomFieldsLength(roomFormBean, request, isValid);
		}

		if (isValid) {

		}

		return isValid;
	}

	private static boolean checkRoomDataMandatoryFields(RoomFormBean roomFormBean, HttpServletRequest request,
			boolean isValid) {

		// category
		if (isNullOrEmpty(roomFormBean.getRoomCategory())) {
			request.setAttribute("roomCategoryEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Category"));
			isValid = false;
		}

		// type
		if (isNullOrEmpty(roomFormBean.getRoomType())) {
			request.setAttribute("roomTypeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Type"));
			isValid = false;
		}

		// description
		if (isNullOrEmpty(roomFormBean.getDescription())) {
			request.setAttribute("roomDescriptionEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Description"));
			isValid = false;
		}

		// room number
		if (isNullOrEmpty(roomFormBean.getRoomNumber())) {
			request.setAttribute("roomNumberEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Room Number"));
			isValid = false;
		}

		// floor number
		if (isMinus(roomFormBean.getFloorNumber())) {
			request.setAttribute("floorNumberMinusError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.minus"), "Room Number"));
			isValid = false;
		}

		return isValid;
	}

	private static boolean checkRoomFieldsLength(RoomFormBean roomFormBean, HttpServletRequest request, boolean isValid) {

		if (isFieldLengthExceed(roomFormBean.getDescription(), 4500)) {
			request.setAttribute("descriptionTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Description", 4500));
			isValid = false;
		}

		if (isFieldLengthExceed(roomFormBean.getRoomNumber(), 20)) {
			request.setAttribute("roomNumberTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Room Number", 20));
			isValid = false;
		}

		if (roomFormBean.getFloorNumber() > 10) {
			request.setAttribute("floorNumberLongError", resourceBundleUtil
					.getLocaleSpecificValue("room.floor.number.exceed"));
			isValid = false;
		}

		return isValid;
	}

	// ----------------------- End of Room --------------------------------

	// ---------------------- Login --------------------------------
	public static boolean isLoginDataValid(LoginFormBean loginFormBean, HttpServletRequest request) {
		boolean isValid = true;

		isValid = checkMandatoryLoginData(loginFormBean, request, isValid);

		return isValid;
	}

	private static boolean checkMandatoryLoginData(LoginFormBean loginFormBean, HttpServletRequest request,
			boolean isValid) {
		if (isNullOrEmpty(loginFormBean.getUserName())) {
			request.setAttribute("usernameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "User name"));
			isValid = false;
		}

		if (isNullOrEmpty(loginFormBean.getPassword())) {
			request.setAttribute("passwordEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Password"));
			isValid = false;
		}
		return isValid;
	}

	// ----------------------- End of Login -----------------------------

	// ------------------------- Hotel Profile ---------------------------

	public static boolean isHotelProfileDataValid(HotelProfileFormBean hotelProfileFormBean, HttpServletRequest request) {
		boolean isValid = true;

		// Check mandatory data first.
		isValid = checkHotelProfileMandatoryFields(hotelProfileFormBean, request, isValid);

		if (isValid) {
			// All mandatory data filed and Check field length.
			isValid = checkHotelProfileFieldsLength(hotelProfileFormBean, request, isValid);
		}
		if (isValid) {
			if (!validateHotelName(hotelProfileFormBean.getHotelName())) {
				request.setAttribute("invalidNameError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.name"));
				isValid = false;
			}
			if (!validateEmail(hotelProfileFormBean.getEmail())) {
				request.setAttribute("emailFormatError", resourceBundleUtil
						.getLocaleSpecificValue("common.invalid.email"));
				isValid = false;
			}
		}
		return isValid;
	}

	private static boolean checkHotelProfileMandatoryFields(HotelProfileFormBean hotelProfileFormBean,
			HttpServletRequest request, boolean isValid) {

		if (isNullOrEmpty(hotelProfileFormBean.getAddressOne())) {
			request.setAttribute("address1EmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Address one"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getCity())) {
			request.setAttribute("cityEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "City"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getContactNumberOne())) {
			request.setAttribute("contNum1EmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Contact number 1"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getCountry())) {
			request.setAttribute("countryEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Country"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getDescription())) {
			request.setAttribute("descriptionEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Description"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getDirection())) {
			request.setAttribute("directionEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Direction"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getEmail())) {
			request.setAttribute("emailEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Email"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getHotelName())) {
			request.setAttribute("hotelNameEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Hotel name"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getPostalCode())) {
			request.setAttribute("postalCodeEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Postal Code"));
			isValid = false;
		}
		if (isNullOrEmpty(hotelProfileFormBean.getFax())) {
			request.setAttribute("faxEmptyError", fillEmptyErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.empty"), "Fax"));
			isValid = false;
		}
		return isValid;
	}

	private static boolean checkHotelProfileFieldsLength(HotelProfileFormBean hotelProfileFormBean,
			HttpServletRequest request, boolean isValid) {

		if (isFieldLengthExceed(hotelProfileFormBean.getAddressOne(), 450)) {
			request.setAttribute("address1TooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Address one", 450));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getAddressTwo(), 450)) {
			request.setAttribute("address2TooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Address two", 450));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getCity(), 100)) {
			request.setAttribute("cityTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "City", 100));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getContactNumberOne(), 45)) {
			request.setAttribute("contNum1TooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Contact number 1", 45));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getContactNumberTwo(), 45)) {
			request.setAttribute("contNum2TooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Contact number 2", 45));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getCountry(), 20)) {
			request.setAttribute("countryTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Country", 20));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getDescription(), 4500)) {
			request.setAttribute("descriptionTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Description", 4500));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getDirection(), 4500)) {
			request.setAttribute("directionTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Direction", 4500));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getEmail(), 45)) {
			request.setAttribute("emailTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Email", 45));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getHotelName(), 100)) {
			request.setAttribute("hotelNameTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Hotel name", 100));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getPostalCode(), 20)) {
			request.setAttribute("postalCodeTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Postal Code", 20));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getOtherWeb(), 100)) {
			request.setAttribute("otherWebTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Other web", 100));
			isValid = false;
		}
		if (isFieldLengthExceed(hotelProfileFormBean.getFax(), 45)) {
			request.setAttribute("faxTooLongError", fillMaximumExceedErrorMessage(resourceBundleUtil
					.getLocaleSpecificValue("common.property.tooLong"), "Fax", 45));
			isValid = false;
		}
		return isValid;
	}

	// ----------------------- End Hotel Profile -----------------------------

	// --------------- Common validator methods --------------------

	private static boolean validateDateRange(String fromDateString, String toDateString, HttpServletRequest request,
			boolean isValid) {

		/*
		 * if(results > 0) System.out.println("First Date is after second");
		 * else if (results < 0)
		 * System.out.println("First Date is before second"); else
		 * System.out.println("Both dates are equal"); }
		 */

		Date fromDate = CommonUtil.stringToDate(fromDateString);
		Date toDate = CommonUtil.stringToDate(toDateString);
		int comparator = fromDate.compareTo(toDate);

		if (comparator > 0) {
			// from date after second.
			request.setAttribute("fromDateLessThanToError", resourceBundleUtil
					.getLocaleSpecificValue("common.invalid.date.range"));
			isValid = false;
		}
		return isValid;
	}

	private static boolean isGreaterThanToday(String dateString) {

		boolean isValid = true;
		Date date = CommonUtil.stringToDate(dateString);

		Calendar todayCalender = Calendar.getInstance();
		Date today = todayCalender.getTime();

		int comparator = date.compareTo(today);
		if (comparator < 0) {
			isValid = false;
		}
		return isValid;
	}

	private static boolean validateDateFormat(String date) {

		// validation format 2010/01/01
		String dateFormat1 = "^[0-9]{4}/(((0[1-9]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

		// validation format 2010/1/1
		String dateFormat2 = "^[0-9]{4}/((([1-9]|(10|12))/([1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

		return date.trim().matches(dateFormat1) || date.trim().matches(dateFormat2);
	}

	private static boolean isNullOrEmpty(String value) {
		return value == null || value.equals("");
	}

	private static boolean isZeroOrMinus(int value) {
		return value <= 0;
	}

	private static boolean isMinus(int value) {
		return value < 0;
	}

	private static boolean isFieldLengthExceed(String field, int maxLength) {
		return field.length() > maxLength;
	}

	private static boolean validateName(String name) {
		String nameToken = "\\p{Upper}(\\p{Lower}+\\s?)";
		return name.matches(nameToken);
	}

	private static boolean validateHotelName(String name) {
		String nameToken = "\\p{Upper}(\\p{Lower}+\\s?)";
		String namePattern = "(" + nameToken + "){2,3}";
		return name.matches(namePattern);
	}

	private static boolean validateEmail(String email) {
		String emailPattern = ".+@.+\\.[a-z]+";
		return email.matches(emailPattern);
	}

	private static String fillEmptyErrorMessage(String original, String replacing) {
		CharSequence delemeter = "$1";
		return original = original.replace(delemeter, replacing);
	}

	private static String fillMaximumExceedErrorMessage(String original, String replacingPropertyName, int maximumLength) {

		CharSequence propertyName = "$1";
		CharSequence maxLength = "$2";

		original = original.replace(propertyName, replacingPropertyName);
		original = original.replace(maxLength, maximumLength + "");

		return original;
	}
}
