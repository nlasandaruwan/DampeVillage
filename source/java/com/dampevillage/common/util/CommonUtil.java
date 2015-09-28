package com.dampevillage.common.util;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dampevillage.domain.Customer;

public class CommonUtil {

	public static void setUserSessionData(HttpServletRequest request, Customer customer) {
		request.getSession().setAttribute(SessionConstants.Common.CURRENT_USER_NAME,
				customer.getFirstName() + " " + customer.getLasteName());
		request.getSession().setAttribute(SessionConstants.Common.CURRENT_USER_ID, customer.getId());
		request.getSession().setAttribute(SessionConstants.Common.CURRENT_USER_EMAIL, customer.getEmail());
		request.getSession().setAttribute(SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID,
				customer.getPrivilage().getId());
	}

	public static Date stringToDate(String date) {
		String fromDateString = date;
		String[] fromDateStringArr = fromDateString.split("/");

		if (fromDateStringArr.length == 1) {
			fromDateStringArr = fromDateString.split("-");
		}

		int fromDate = Integer.parseInt(fromDateStringArr[2]);
		int fromMonth = Integer.parseInt(fromDateStringArr[1]);
		int fromYear = Integer.parseInt(fromDateStringArr[0]);

		Calendar fromCalender = Calendar.getInstance();

		fromCalender.set(fromYear, fromMonth - 1, fromDate, 00, 00, 00);

		return fromCalender.getTime();

	}

	public static String dateToString(Date date) {

		String toReturn = "";
		if (date != null) {
			StringBuffer dateFormated = new StringBuffer();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);

			dateFormated.append(year + "/");
			dateFormated.append(month + 1 + "/");
			dateFormated.append(day);

			toReturn = dateFormated.toString();
		}
		return toReturn;
	}

	public static boolean validateSessionUser(HttpServletRequest request) {

		HttpSession session = request.getSession();
		return session != null && session.getAttribute(SessionConstants.Common.CURRENT_USER_ID) != null ? true : false;
	}

	public static boolean validateSessionUser(HttpServletRequest request, Integer expectedPrivilageId) {

		HttpSession session = request.getSession();
		boolean isValid = false;

		if (session != null && session.getAttribute(SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID) != null) {
			Integer logprivId = (Integer) session.getAttribute(SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID);
			isValid = logprivId.equals(expectedPrivilageId);
		}
		return isValid;
	}
}
