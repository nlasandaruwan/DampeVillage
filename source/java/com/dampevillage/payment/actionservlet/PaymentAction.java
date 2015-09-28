package com.dampevillage.payment.actionservlet;

import java.util.Calendar;

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
import com.dampevillage.payment.formbean.PaymentFormBean;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class PaymentAction extends Action {

	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login page.
		if (!CommonUtil.validateSessionUser(request)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		String status = "";
		PaymentFormBean paymentFormBean = (PaymentFormBean) form;
		try {
			if (FormDataValidatorUtil.isPaymentDataValid(paymentFormBean, request)) {

				Payment payment = populatePaymentObject(paymentFormBean);
				request.getSession().setAttribute(SessionConstants.Payment.TEMPORY_PAYMENT_OBJECT, payment);
				status = "successPayment";

			} else {
				request.setAttribute("paymentObject", populatePaymentObject(paymentFormBean));
				status = "invalidPaymentData";
			}

		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}

	private Payment populatePaymentObject(PaymentFormBean paymentFormBean) {

		Payment payment = new Payment();

		payment.setCardExpirityMonth(paymentFormBean.getExpireMonth());
		payment.setCardExpirityYear(paymentFormBean.getExpireYear());
		payment.setCardHolderName(paymentFormBean.getCardHolderName());
		payment.setCardNumber(paymentFormBean.getCardNumber());
		payment.setEmail(paymentFormBean.getEmail());
		payment.setPaymentAmount(paymentFormBean.getPaymentAmount());
		payment.setPaymentDate(Calendar.getInstance().getTime());
		payment.setId(paymentFormBean.getId());
		payment.setVersion(paymentFormBean.getVersion());
		payment.setCardType(paymentFormBean.getCardType());

		return payment;
	}
}
