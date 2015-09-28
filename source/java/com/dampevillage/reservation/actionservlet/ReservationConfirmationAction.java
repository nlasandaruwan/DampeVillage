package com.dampevillage.reservation.actionservlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.Payment;
import com.dampevillage.reservation.formbean.ReserConfirmationvationFormBean;

public class ReservationConfirmationAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login page.
		if (!CommonUtil.validateSessionUser(request)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		String status = "";
		ReserConfirmationvationFormBean reserConfirmationvationFormBean = (ReserConfirmationvationFormBean) form;

		try {

			// set the incomplete reservation object into session and
			// redirect into login page
			request.getSession().setAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT, null);
			request.getSession().setAttribute(SessionConstants.Reservation.TEMPORY_RESERVATION_OBJECT,
					reserConfirmationvationFormBean);

			Payment oldPayment = (Payment) request.getSession().getAttribute(
					SessionConstants.Payment.OLD_PAYMENT_OBJECT);

			// Either new reservation object save or old charge and new charge
			// are different and needs to re payment.
			if ((oldPayment == null) || oldPayment != null
					&& (oldPayment.getPaymentAmount() != reserConfirmationvationFormBean.getCharge())) {
				status = "paymentPage";
			} else {

				// Both are equal no need to pay.
				status = "noPayments";
			}

		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}
}
