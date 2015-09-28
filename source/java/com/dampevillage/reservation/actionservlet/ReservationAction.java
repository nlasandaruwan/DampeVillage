package com.dampevillage.reservation.actionservlet;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.AccomodationMode;
import com.dampevillage.domain.Customer;
import com.dampevillage.domain.Payment;
import com.dampevillage.domain.Reservation;
import com.dampevillage.domain.RoomRate;
import com.dampevillage.manager.Manager;
import com.dampevillage.reservation.formbean.ReserConfirmationvationFormBean;
import com.dampevillage.reservation.formbean.ReservationFormBean;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class ReservationAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = "";
		ReservationFormBean reservationFormBean = (ReservationFormBean) form;
		try {

			manager = (Manager) BeanUtil.getBean("hotelmanager");

			// Check whether user login or not. If not redirect into Login page
			Integer logedInUser = (Integer) request.getSession().getAttribute(SessionConstants.Common.CURRENT_USER_ID);

			// User not log in to system
			if (logedInUser == null) {
				// set the incomplete reservation object into session and
				// redirect into login page
				request.getSession().setAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT,
						reservationFormBean);
				status = "pleaseLoginFirst";
			} else {

				// Payment successful and final reservation and payment records
				// are saving.
				if (request.getParameter("hdnMode") != null
						&& request.getParameter("hdnMode").equals("saveReservation")) {

					// validate session and user in session if not redirect into
					// login page.
					if (!CommonUtil.validateSessionUser(request)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					ReserConfirmationvationFormBean reserConfirmationvationFormBean = (ReserConfirmationvationFormBean) request
							.getSession().getAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT);

					Payment payment = (Payment) request.getSession().getAttribute(
							SessionConstants.Payment.TEMPORY_PAYMENT_OBJECT);

					Reservation reservation = populateReservationObjectFromConfirmationRequest(
							reserConfirmationvationFormBean, payment, request);
					manager.saveReservation(reservation);
					request.setAttribute("successMessage", resourceBundleUtil
							.getLocaleSpecificValue("reservation.success.save"));
					getAllReservationsAndSetRequest(request, logedInUser);

					// Removes the old payment object if .
					// Payment paymentInSession = (Payment)
					// request.getSession().getAttribute(
					// SessionConstants.Payment.OLD_PAYMENT_OBJECT);
					// if (paymentInSession != null) {
					// manager.deletePayment(paymentInSession);
					// }

					// Reset session data.
					request.getSession().setAttribute(SessionConstants.Payment.OLD_PAYMENT_OBJECT, null);
					request.getSession().setAttribute(SessionConstants.Payment.TEMPORY_PAYMENT_OBJECT, null);
					request.getSession().setAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT, null);

					reservationFormBean = null;
					status = "successReservation";

				}
				// No payment need and other updated reservation details needs
				// to save
				if (request.getParameter("hdnMode") != null && request.getParameter("hdnMode").equals("noPayment")) {

					// validate session and user in session if not redirect into
					// login page.
					if (!CommonUtil.validateSessionUser(request)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					ReserConfirmationvationFormBean reserConfirmationvationFormBean = (ReserConfirmationvationFormBean) request
							.getSession().getAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT);

					// Set the old payment object if .
					Payment paymentInSession = (Payment) request.getSession().getAttribute(
							SessionConstants.Payment.OLD_PAYMENT_OBJECT);

					Reservation reservation = populateReservationObjectFromConfirmationRequest(
							reserConfirmationvationFormBean, paymentInSession, request);
					manager.saveReservation(reservation);
					request.setAttribute("successMessage", resourceBundleUtil
							.getLocaleSpecificValue("reservation.success.save"));
					getAllReservationsAndSetRequest(request, logedInUser);
					reservationFormBean = null;

					// Reset session data.
					request.getSession().setAttribute(SessionConstants.Payment.OLD_PAYMENT_OBJECT, null);
					request.getSession().setAttribute(SessionConstants.Payment.TEMPORY_PAYMENT_OBJECT, null);
					request.getSession().setAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT, null);

					status = "successReservation";
				}

				// User view all reservations history from profile page.
				if (request.getParameter("hdnMode") != null
						&& request.getParameter("hdnMode").equals("viewAllReservations")) {

					// validate session and user in session if not redirect into
					// login page.
					if (!CommonUtil.validateSessionUser(request)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					getAllReservationsAndSetRequest(request, logedInUser);
					status = "successReservation";
				}

				// User selects a particular reservation and trying to Edit.
				if (request.getParameter("hdnMode") != null
						&& request.getParameter("hdnMode").equals("editReservation")) {

					// validate session and user in session if not redirect into
					// login page.
					if (!CommonUtil.validateSessionUser(request)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					// Check before cancel reservation whether it's 'from date'
					// equals to current date or past date to current date. Such
					// reservations are not allows to cancel.

					Date fromDate = CommonUtil.stringToDate(reservationFormBean.getFromDate());
					if ((fromDate.compareTo(Calendar.getInstance().getTime()) < 0)
							|| (fromDate.compareTo(Calendar.getInstance().getTime()) == 0)) {
						request.setAttribute("tooLateToEditReservation", resourceBundleUtil
								.getLocaleSpecificValue("reservation.tooLate.edit"));
						getAllReservationsAndSetRequest(request, logedInUser);
						return mapping.findForward("tooLateToEditReservation");
					}

					Reservation reservation = manager.getReservationById(reservationFormBean.getId());

					// set the previous payment record in reservation into
					// session
					request.getSession().setAttribute(SessionConstants.Payment.OLD_PAYMENT_OBJECT,
							reservation.getPayment());

					// set the old from date
					request.getSession().setAttribute(SessionConstants.Reservation.OLD_FROM_DATE,
							reservation.getFromDate());
					// set the old to date
					request.getSession()
							.setAttribute(SessionConstants.Reservation.OLD_TO_DATE, reservation.getToDate());

					// Redirect into reservation page
					request.setAttribute("reservationObject", reservation);
					// Set the no of rooms available in unedited reservation to
					// session to calculate no of rooms available when editing
					// reservation.
					request.getSession().setAttribute(
							SessionConstants.Reservation.NO_OF_ROOMS_AVAILABLE_IN_OLD_RESERVATION,
							reservation.getNoOfRooms());
					status = "initLoadPage";

				}

				// User selects a particular reservation and trying to Cancel.
				if (request.getParameter("hdnMode") != null
						&& request.getParameter("hdnMode").equals("deleteReservation")) {

					// validate session and user in session if not redirect into
					// login page.
					if (!CommonUtil.validateSessionUser(request)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					// Check before cancel reservation whether it's 'from date'
					// equals to current date or past date to current date. Such
					// reservations are not allows to cancel.

					Date fromDate = CommonUtil.stringToDate(reservationFormBean.getFromDate());
					if ((fromDate.compareTo(Calendar.getInstance().getTime()) < 0)
							|| (fromDate.compareTo(Calendar.getInstance().getTime()) == 0)) {
						request.setAttribute("tooLateToCancelReservation", resourceBundleUtil
								.getLocaleSpecificValue("reservation.tooLate.delete"));
						getAllReservationsAndSetRequest(request, logedInUser);
						return mapping.findForward("tooLateToCancelReservation");
					}

					Reservation reservationToDelete = new Reservation();
					reservationToDelete.setId(reservationFormBean.getId());
					reservationToDelete.setVersion(reservationFormBean.getVersion());

					manager.deleteReservation(reservationToDelete);

					request.setAttribute("successDeleteMessage", resourceBundleUtil
							.getLocaleSpecificValue("reservation.success.cancel"));

					request.getSession().setAttribute(SessionConstants.Payment.OLD_PAYMENT_OBJECT, null);
					request.getSession().setAttribute(SessionConstants.Payment.TEMPORY_PAYMENT_OBJECT, null);
					request.getSession().setAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT, null);

					getAllReservationsAndSetRequest(request, logedInUser);
					status = "successReservation";
				}

				// Staff Member selects a particular reservation and trying to
				// Confirm.
				if (request.getParameter("hdnMode") != null
						&& request.getParameter("hdnMode").equals("confirmReservation")) {

					// validate session and user in session if not redirect into
					// login page. Only Staff allow this operation.
					if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.STAFF_PRIVILAGE_ID)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					Reservation reservationToConfirm = new Reservation();
					reservationToConfirm.setId(reservationFormBean.getId());
					reservationToConfirm.setVersion(reservationFormBean.getVersion());

					manager.confirmReservation(reservationToConfirm);
					status = "successConfirmation";
				}

				// pop up for room confirmation. Staff trying to confirm
				// reservation
				if (request.getParameter("hdnMode") != null
						&& request.getParameter("hdnMode").equals("loadReservation")) {

					// validate session and user in session if not redirect into
					// login page.
					if (!CommonUtil.validateSessionUser(request)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					Reservation reservation = manager.getReservationById(reservationFormBean.getId());
					// Redirect into reservation pop up page
					request.setAttribute("reservationObject", reservation);
					status = "successPopUp";
				}

				// User successfully login after redirect into Login page and
				// come back into reservation page.
				if (request.getParameter("hdnMode") != null
						&& request.getParameter("hdnMode").equals("afterSuccessfulLogin")) {

					// Get the incomplete reservation object from session and
					// process
					reservationFormBean = null;
					reservationFormBean = (ReservationFormBean) request.getSession().getAttribute(
							SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT);

					setReservationObjectRequest(request, reservationFormBean);
					status = "initLoadPage";
				}
				// User already login before search rooms and normal scenario
				if (request.getParameter("hdnMode") != null && request.getParameter("hdnMode").equals("initLoadPage")) {

					setReservationObjectRequest(request, reservationFormBean);
					status = "initLoadPage";
				}
				// User saves the reservation by click save reservation button
				// in Reservation.jsp and redirecting into
				// ReservationConfirmation.jap page.
				if (request.getParameter("hdnMode") == null) {

					// validate session and user in session if not redirect into
					// login page.
					if (!CommonUtil.validateSessionUser(request)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					if (FormDataValidatorUtil.isRoomReservationDataValid(reservationFormBean, request)) {
						// Editing reservation
						if (reservationFormBean.getId() > 0) {

							// new from date
							Date newFromDate = CommonUtil.stringToDate(reservationFormBean.getFromDate());
							// new to date
							Date newToDate = CommonUtil.stringToDate(reservationFormBean.getToDate());

							// get the old from date
							Date oldFromDate = (Date) request.getSession().getAttribute(
									SessionConstants.Reservation.OLD_FROM_DATE);
							// get the old to date
							Date oldToDate = (Date) request.getSession().getAttribute(
									SessionConstants.Reservation.OLD_TO_DATE);

							// format old dates
							String oldFromDateString = CommonUtil.dateToString(oldFromDate);
							String oldToDateString = CommonUtil.dateToString(oldToDate);

							// format new dates
							String newFromDateString = CommonUtil.dateToString(newFromDate);
							String newToDateString = CommonUtil.dateToString(newToDate);

							// reassign dates

							newFromDate = null;
							newToDate = null;
							oldFromDate = null;
							oldToDate = null;

							newFromDate = CommonUtil.stringToDate(newFromDateString);
							newToDate = CommonUtil.stringToDate(newToDateString);

							oldFromDate = CommonUtil.stringToDate(oldFromDateString);
							oldToDate = CommonUtil.stringToDate(oldToDateString);

							// compare old from and to dates with new from and
							// to
							// dates.
							int fromDateChangeValue = oldFromDate.compareTo(newFromDate);
							int toDateChangeValue = oldToDate.compareTo(newToDate);

							// Both old and new from and two dates are equal (No
							// date change :))
							if (fromDateChangeValue == 0 && toDateChangeValue == 0) {

								status = checkRoomAvailability(request, reservationFormBean);

							} else {

								// Dates have been change in reservation page

								// new Dates are between old from and to dates
								if ((fromDateChangeValue < 0 && toDateChangeValue > 0)
										|| (fromDateChangeValue == 0 && toDateChangeValue > 0)
										|| (fromDateChangeValue < 0 && toDateChangeValue == 0)) {

									status = checkRoomAvailability(request, reservationFormBean);
								} else {

									// Both old and new from and two dates are
									// not
									// intercept with new dates.
									if ((fromDateChangeValue > 0 && toDateChangeValue > 0)
											|| (fromDateChangeValue < 0 && toDateChangeValue < 0)) {

										List<String> roomList = manager.searchRooms(newFromDate, newToDate,
												reservationFormBean.getRoomTypeId(), reservationFormBean
														.getRoomCategoryId());

										int roomsAvailableForNewDates = getAvailableRooms(roomList);

										if (roomsAvailableForNewDates < reservationFormBean.getNoOfRooms()) {
											request.setAttribute("noRoomsAvailableForNewCriteria",
													"No Rooms available for given dates.");
											setReservationObjectRequest(request, reservationFormBean);
											status = "initLoadPage";
										} else {

											Long charge = getRoomCharge(reservationFormBean);
											if (charge == 0) {
												setReservationObjectRequest(request, reservationFormBean);
												request.setAttribute("noRoomsRatesAvailableForDates",
														"Room rates are not defined for given date range.");
												status = "initLoadPage";
											} else {
												setReservationObjectRequest(request, reservationFormBean);
												request.setAttribute("charge", charge + "");
												status = "reservationConfirmation";
											}
										}
									} else {
										// Either old or new from and two dates
										// are intercept with new dates.

										request.setAttribute("noRoomsAvailableForNewCriteria",
												"No Rooms available for given dates.");
										setReservationObjectRequest(request, reservationFormBean);
										status = "initLoadPage";
									}
								}
							}

						} else {

							// Adding new reservation

							// Check room availability for given date range by
							// reservation page.

							Date fromDate = CommonUtil.stringToDate(reservationFormBean.getFromDate());
							Date toDate = CommonUtil.stringToDate(reservationFormBean.getToDate());

							List<String> roomList = manager.searchRooms(fromDate, toDate, reservationFormBean
									.getRoomTypeId(), reservationFormBean.getRoomCategoryId());

							if (roomList == null || roomList.isEmpty()) {

								// Date range has been change by reservation
								// page and new date range do not have rooms
								// available.
								request.setAttribute("roonNumberExceed",
										"No of rooms you try to reserve exceeds total no of rooms available");
								setReservationObjectRequest(request, reservationFormBean);
								status = "initLoadPage";

							} else {

								// Date range has been change by reservation
								// page or not.And date range have rooms
								// available.
								int noOfRoomsAvailable = Integer.valueOf(
										(String) request.getSession().getAttribute(
												SessionConstants.Reservation.NO_OF_ROOMS_AVAILABLE)).intValue();

								int noOfRoomsBooking = reservationFormBean.getNoOfRooms();

								// No of rooms booking is more than available no
								// of
								// rooms.
								if (noOfRoomsBooking > noOfRoomsAvailable) {
									request.setAttribute("roonNumberExceed",
											"No of rooms you try to reserve exceeds total no of rooms available");
									setReservationObjectRequest(request, reservationFormBean);
									status = "initLoadPage";
								} else {

									Long charge = getRoomCharge(reservationFormBean);
									if (charge == 0) {
										request.setAttribute("noRoomsRatesAvailableForDates",
												"Room rates are not defined for given date range.");
										setReservationObjectRequest(request, reservationFormBean);
										status = "initLoadPage";
									} else {
										setReservationObjectRequest(request, reservationFormBean);
										request.setAttribute("charge", charge + "");
										status = "reservationConfirmation";
									}

								}
							}
						}

					} else {
						setReservationObjectRequest(request, reservationFormBean);
						status = "invalidRoomReservationData";
					}
				}
			}

		} catch (MessagingException e) {
			status = "emailException";
		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}

	private String checkRoomAvailability(HttpServletRequest request, ReservationFormBean reservationFormBean) {
		String status;
		int roomsAlreadyInReservation;
		// Get the rooms already available.
		roomsAlreadyInReservation = (Integer) request.getSession().getAttribute(
				SessionConstants.Reservation.NO_OF_ROOMS_AVAILABLE_IN_OLD_RESERVATION);

		// No of rooms booking is more than available no
		// of
		// rooms.
		if (roomsAlreadyInReservation < reservationFormBean.getNoOfRooms()) {
			request.setAttribute("roonNumberExceed",
					"No of rooms you try to reserve exceeds total no of rooms available");
			setReservationObjectRequest(request, reservationFormBean);
			status = "initLoadPage";
		} else {

			Long charge = getRoomCharge(reservationFormBean);
			if (charge == 0) {
				request.setAttribute("noRoomsRatesAvailableForDates",
						"Room rates are not defined for given date range.");
				setReservationObjectRequest(request, reservationFormBean);
				status = "initLoadPage";
			} else {
				setReservationObjectRequest(request, reservationFormBean);
				request.setAttribute("charge", charge + "");
				status = "reservationConfirmation";
			}

		}
		return status;
	}

	private int getAvailableRooms(List<String> roomList) {
		String[] arrRoomDetails = null;

		if (roomList != null && !roomList.isEmpty()) {
			arrRoomDetails = roomList.get(0).split("-");
		}
		// Rooms are available for given criteria.
		int roomsNotAllocated = 0;
		if (arrRoomDetails != null) {
			roomsNotAllocated = Integer.valueOf(arrRoomDetails[4]).intValue();
		}
		return roomsNotAllocated;
	}

	private void getAllReservationsAndSetRequest(HttpServletRequest request, Integer logedInUser) {
		List<Reservation> reservations = manager.getAllReservation(logedInUser.intValue());
		request.setAttribute("allReservationsForUser", reservations);
	}

	// This method calculate room rates by providing from date to date room
	// category room type and accommodation mode.
	private Long getRoomCharge(ReservationFormBean reservationFormBean) {

		long rate = 0;
		List<RoomRate> roomRates = manager.searchRoomRate(CommonUtil.stringToDate(reservationFormBean.getFromDate()),
				CommonUtil.stringToDate(reservationFormBean.getToDate()), reservationFormBean.getRoomCategoryId(),
				reservationFormBean.getRoomTypeId(), reservationFormBean.getAccomodationMode());

		Long roomCharge = 0L;

		// get room rates for each day. and calculate.
		if (roomRates != null && !roomRates.isEmpty()) {

			for (RoomRate currentRoomRate : roomRates) {
				rate += currentRoomRate.getPrice();
			}
			// rates are define rate calculation multiply from no of rooms to
			// calculate total amount
			// for all rooms.
			roomCharge = new Long(rate * reservationFormBean.getNoOfRooms());
		}
		return roomCharge;
	}

	// This method creates a reservation object to send back into
	// Reservation.jsp and ReservationConfirmation.jasp file with all data needs
	// to re-populate
	private void setReservationObjectRequest(HttpServletRequest request, ReservationFormBean reservationFormBean) {

		Reservation reservation = new Reservation();

		if (validateDateFormat(reservationFormBean.getFromDate())) {

			if (reservationFormBean.getFromDate() != null && !reservationFormBean.getFromDate().equals("")) {
				// Set From date
				Date dateFromDate = CommonUtil.stringToDate(reservationFormBean.getFromDate());
				reservation.setFromDate(dateFromDate);
			}

		}/*
		 * else { reservation.setFromDate(Calendar.getInstance().getTime()); }
		 */

		if (validateDateFormat(reservationFormBean.getToDate())) {
			if (reservationFormBean.getToDate() != null && !reservationFormBean.getToDate().equals("")) {

				// Set To date
				Date dateToDate = CommonUtil.stringToDate(reservationFormBean.getToDate());
				reservation.setToDate(dateToDate);
			}

		} /*
		 * else { reservation.setFromDate(Calendar.getInstance().getTime()); }
		 */

		// Set accommodation mode
		AccomodationMode accomodationMode = new AccomodationMode();
		accomodationMode.setId(reservationFormBean.getAccomodationMode());
		reservation.setAccomodationMode(accomodationMode);
		// Set Adults
		reservation.setAdults(reservationFormBean.getAdults());
		// Airport Pickup
		reservation.setAirportPickup(reservationFormBean.getAirportPickup());
		// Arrive from
		reservation.setArrivalFrom(reservationFormBean.getArrivalFrom());
		// Arrive for
		reservation.setArriveFor(reservationFormBean.getArriveFor());
		// Children
		reservation.setChildren(reservationFormBean.getChildren());
		// Leave after
		reservation.setLeaveAfter(reservationFormBean.getLeaveAfter());
		// Nationality
		reservation.setNationality(reservationFormBean.getNationality());
		// No of Rooms
		reservation.setNoOfRooms(reservationFormBean.getNoOfRooms());
		// Room Category
		reservation.setRoomCategory(reservationFormBean.getRoomCategory());
		// Room Type
		reservation.setRoomType(reservationFormBean.getRoomType());
		// Room Category Id
		reservation.setRoomCategoryId(reservationFormBean.getRoomCategoryId());
		// Room Type Id
		reservation.setRoomTypeId(reservationFormBean.getRoomTypeId());
		// reservation Id
		reservation.setId(reservationFormBean.getId());
		// reservation Version
		reservation.setVersion(reservationFormBean.getVersion());

		request.setAttribute("reservationObject", reservation);
	}

	// This method creates a reservation object to send back into
	// Reservation.jsp and ReservationConfirmation.jasp file with all data needs
	// to re-populate
	private Reservation populateReservationObjectFromConfirmationRequest(
			ReserConfirmationvationFormBean reserConfirmationvationFormBean, Payment payment, HttpServletRequest request) {
		Reservation reservation = new Reservation();

		AccomodationMode accomodationMode = new AccomodationMode();
		accomodationMode.setId(reserConfirmationvationFormBean.getAccomodationMode());
		reservation.setAccomodationMode(accomodationMode);

		reservation.setAdults(reserConfirmationvationFormBean.getAdults());
		reservation.setAirportPickup(reserConfirmationvationFormBean.getAirportPickup());
		reservation.setArrivalFrom(reserConfirmationvationFormBean.getArrivalFrom());
		reservation.setArriveFor(reserConfirmationvationFormBean.getArriveFor());
		reservation.setChildren(reserConfirmationvationFormBean.getChildren());

		Customer customer = new Customer();
		customer.setId(((Integer) request.getSession().getAttribute(SessionConstants.Common.CURRENT_USER_ID))
				.intValue());
		reservation.setCustomer(customer);

		reservation.setFromDate(CommonUtil.stringToDate(reserConfirmationvationFormBean.getFromDate()));
		reservation.setId(reserConfirmationvationFormBean.getId());
		reservation.setLeaveAfter(reserConfirmationvationFormBean.getLeaveAfter());
		reservation.setNationality(reserConfirmationvationFormBean.getNationality());
		reservation.setNoOfRooms(reserConfirmationvationFormBean.getNoOfRooms());

		if (payment != null) {
			payment.setPaymentAmount(reserConfirmationvationFormBean.getCharge());
			reservation.setPayment(payment);
		}

		reservation.setToDate(CommonUtil.stringToDate(reserConfirmationvationFormBean.getToDate()));
		reservation.setVersion(reserConfirmationvationFormBean.getVersion());
		reservation.setRoomCategory(reserConfirmationvationFormBean.getRoomCategory());
		reservation.setRoomCategoryId(reserConfirmationvationFormBean.getRoomCategoryId());
		reservation.setRoomType(reserConfirmationvationFormBean.getRoomType());
		reservation.setRoomTypeId(reserConfirmationvationFormBean.getRoomTypeId());
		// New reservation object set status as pending.
		reservation.setReservationStatus("PENDING");

		return reservation;
	}

	private static boolean validateDateFormat(String date) {

		// validation format 2010/01/01
		String dateFormat1 = "^[0-9]{4}/(((0[1-9]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

		// validation format 2010/1/1
		String dateFormat2 = "^[0-9]{4}/((([1-9]|(10|12))/([1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

		boolean isMatch = false;
		if (date != null) {
			isMatch = date.trim().matches(dateFormat1);
			if (!isMatch) {
				isMatch = date.trim().matches(dateFormat2);
			}
		}
		return isMatch;
	}
}
