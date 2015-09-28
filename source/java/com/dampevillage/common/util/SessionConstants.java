package com.dampevillage.common.util;

public class SessionConstants {

	public interface Common {
		String CURRENT_USER_NAME = "currentUserName";
		String CURRENT_USER_ID = "currentUserId";
		String CURRENT_USER_PRIVILAGE_ID = "currentUserPrivilage";
		String CURRENT_USER_EMAIL = "currentUserEmail";

		Integer ADMIN_PRIVILAGE_ID = new Integer(1);
		Integer MANAGER_PRIVILAGE_ID = new Integer(2);
		Integer CUSTOMER_PRIVILAGE_ID = new Integer(3);
		Integer STAFF_PRIVILAGE_ID = new Integer(4);

		String HDNMODE = "hdnMode";
		
		String SESSION_INVALID_GLOBAL_FORWARD = "invalidSessionOrUser";
	}

	public interface Reservation {
		String TEMPORY_RESERVATION_OBJECT = "temporyReservationObject";
		String NO_OF_ROOMS_AVAILABLE = "noOfRoomsAvailable";
		String NO_OF_ROOMS_AVAILABLE_IN_OLD_RESERVATION = "noOfRoomsAvailableInOldReservation";
		String OLD_FROM_DATE = "oldFromDate";
		String OLD_TO_DATE = "oldToDate";
	}

	public interface Payment {
		String TEMPORY_PAYMENT_OBJECT = "temporyPaymentObject";
		String OLD_PAYMENT_OBJECT = "oldPaymentObject";
		String OLD_CHARGE = "oldcharge";
		
	}
}
