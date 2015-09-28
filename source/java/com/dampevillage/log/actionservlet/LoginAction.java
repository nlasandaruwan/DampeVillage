package com.dampevillage.log.actionservlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.Customer;
import com.dampevillage.log.formbean.LoginFormBean;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class LoginAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = "";
		LoginFormBean loginFormBean = (LoginFormBean) form;

		try {

			// User trying to Logout
			if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("logout")) {
				request.getSession().invalidate();
				status = "logOut";
			} else {
				// Reservation page redirect here login user to accomplish
				// reservation.
				if (request.getParameter(SessionConstants.Common.HDNMODE) != null
						&& request.getParameter(SessionConstants.Common.HDNMODE).equals("reservationRedirect")) {
					// set this flag to login jsp to distinglish normal login
					// and
					// force login by reservation page
					request.setAttribute("reservationRedirect", "reservationRedirect");
					status = "reservationRedirect";

				} else {

					// Both normal login and force login comes into this part to
					// check user validity.

					// Check login data validity

					if (FormDataValidatorUtil.isLoginDataValid(loginFormBean, request)) {
						Customer customerFromUi = new Customer();
						customerFromUi.setUserName(loginFormBean.getUserName());
						customerFromUi.setPassword(loginFormBean.getPassword());

						manager = (Manager) BeanUtil.getBean("hotelmanager");

						Customer customer = manager.isValidUser(customerFromUi);
						if (customer != null) {

							CommonUtil.setUserSessionData(request, customer);

							// Normal user login and this status will redirect
							// user
							// into
							// user profile.
							if (loginFormBean.getReservationRedirect() == null
									|| loginFormBean.getReservationRedirect().equals("")) {
								if (customer.getPrivilage().getId() == 4) {
									status = "successStaff";
								} else {
									status = "successUser";
								}

							} else {
								// Force user login and this status will
								// redirect
								// user
								// into back to incomplete reservation process
								if (customer.getPrivilage().getId() == 4) {
									status = "successStaff";
								} else {
									status = "backToReservationPage";
								}
							}
						}
					} else {
						if (loginFormBean.getReservationRedirect() != null
								&& loginFormBean.getReservationRedirect().equals("reservationRedirect")) {
							// Invalid Login attempt in force login of
							// reservation process.
							request.setAttribute("reservationRedirect", "reservationRedirect");
						}
						status = "invalideUserData";
					}
				}
			}

		} catch (Exception e) {

			// Exception throws when user enter incorrect credentals.

			if (loginFormBean.getReservationRedirect() != null
					&& loginFormBean.getReservationRedirect().equals("reservationRedirect")) {
				// Invalid Login attempt in force login of reservation process.
				request.setAttribute("reservationRedirect", "reservationRedirect");
			}
			// redirect back into login page regardless of normal or force login
			// attempt.
			status = "invalideUser";
			request.setAttribute("exceptionOccured", resourceBundleUtil.getLocaleSpecificValue("login.invalid.user"));
		}
		return mapping.findForward(status);
	}
}
