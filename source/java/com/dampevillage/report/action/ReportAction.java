package com.dampevillage.report.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.report.util.JasperUtil;
import com.dampevillage.util.FormDataValidatorUtil;

public class ReportAction extends Action {

	private static String VIEW_ALL_CUSTOMERS_File = "ViewAllCustomers";
	private static String VIEW_CUSTOMERS_BY_COUNTRY_File = "ViewCustomersByCountry";
	private static String VIEW_CUSTOMER_DETAILS_File = "ViewCustomerDetails";

	private static String VIEW_ALL_RESERVATIONS_STATUS_File = "AllReservationStatus";
	private static String VIEW_CUSTOMER_RESERVATIONS_File = "CustomerReservations";
	private static String VIEW_CUSTOMER_RESERVATION_STATUS_File = "AllReservationStatusByCustomer";
	private static String VIEW_CUSTOMER_RESERVATION_DATE_RANGE_File = "AllReservationForGivenDateRange";
	private static String VIEW_INCOME_FOR_DATE_RANGE_File = "Income";

	private static String ADMIN_REPORT = "admin";
	private static String MANAGER_REPORT = "manager";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login page.
		if (!CommonUtil.validateSessionUser(request)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		String hdnMode = request.getParameter(SessionConstants.Common.HDNMODE);
		String status = "";

		try {

			// view All Customers
			if (hdnMode != null && hdnMode.equals("viewAllCustomers")) {

				JasperUtil.runReport(VIEW_ALL_CUSTOMERS_File, ADMIN_REPORT, null);
				status = "successGetAllCustomers";
			}

			// view customers by country
			if (hdnMode != null && hdnMode.equals("viewCustomersByCountry")) {

				JasperUtil.runReport(VIEW_CUSTOMERS_BY_COUNTRY_File, ADMIN_REPORT, null);
				status = "successGetAllCustomersByCountry";
			}

			// view All reservation status
			if (hdnMode != null && hdnMode.equals("allReservationStatus")) {
				JasperUtil.runReport(VIEW_ALL_RESERVATIONS_STATUS_File, MANAGER_REPORT, null);
				status = "AllReservationStatus";
			}

			// view customers details
			if (hdnMode != null && hdnMode.equals("viewCustomerDetails")) {

				if (FormDataValidatorUtil.isReportFormDataValid(request)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("First_Name", request.getParameter("firstname"));
					map.put("Last_Name", request.getParameter("lastname"));

					JasperUtil.runReport(VIEW_CUSTOMER_DETAILS_File, ADMIN_REPORT, map);
					status = "successCustomerDetails";

				} else {
					request.setAttribute("firstName", request.getParameter("firstname"));
					request.setAttribute("lastName", request.getParameter("lastname"));
					status = "errorCustomerDetails";
				}
			}

			// view customer reservations
			if (hdnMode != null && hdnMode.equals("customerReservations")) {

				if (FormDataValidatorUtil.isReportFormDataValid(request)) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("First_Name", request.getParameter("firstname"));
					map.put("Last_Name", request.getParameter("lastname"));

					JasperUtil.runReport(VIEW_CUSTOMER_RESERVATIONS_File, MANAGER_REPORT, map);
					status = "successCustomerReservations";

				} else {
					request.setAttribute("firstName", request.getParameter("firstname"));
					request.setAttribute("lastName", request.getParameter("lastname"));
					status = "errorCustomerReservations";
				}
			}

			// View Customer Reservation Status
			if (hdnMode != null && hdnMode.equals("customerReservationStatus")) {

				if (FormDataValidatorUtil.isReportFormDataValid(request)) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("First_Name", request.getParameter("firstname"));
					map.put("Last_Name", request.getParameter("lastname"));

					JasperUtil.runReport(VIEW_CUSTOMER_RESERVATION_STATUS_File, MANAGER_REPORT, map);
					status = "successCustomerReservations";

				} else {
					request.setAttribute("firstName", request.getParameter("firstname"));
					request.setAttribute("lastName", request.getParameter("lastname"));
					status = "errorCustomerReservationStatus";
				}
			}

			// View All reservations for date range
			if (hdnMode != null && hdnMode.equals("allRervationsForDateRange")) {

				if (FormDataValidatorUtil.isReportDateRangeDataValid(request)) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("From_Date", request.getParameter("fromDate"));
					map.put("To_Date", request.getParameter("toDate"));

					JasperUtil.runReport(VIEW_CUSTOMER_RESERVATION_DATE_RANGE_File, MANAGER_REPORT, map);
					status = "successReservationsForDateRange";

				} else {
					request.setAttribute("fromDate", request.getParameter("fromDate"));
					request.setAttribute("toDate", request.getParameter("toDate"));
					status = "errorReservationsForDateRange";
				}
			}

			// View income for date range
			if (hdnMode != null && hdnMode.equals("incomeForDateRange")) {

				if (FormDataValidatorUtil.isReportDateRangeDataValid(request)) {

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("From_Date", request.getParameter("fromDate"));
					map.put("To_Date", request.getParameter("toDate"));

					JasperUtil.runReport(VIEW_INCOME_FOR_DATE_RANGE_File, MANAGER_REPORT, map);
					status = "successIncomeForDateRange";

				} else {
					request.setAttribute("fromDate", request.getParameter("fromDate"));
					request.setAttribute("toDate", request.getParameter("toDate"));
					status = "errorIncomeForDateRange";
				}
			}

		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}
}
