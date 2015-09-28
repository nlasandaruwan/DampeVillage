package com.dampevillage.admin.notification.actionservlet;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;
import com.dampevillage.util.emailnotification.EmailDataDTO;
import com.dampevillage.util.emailnotification.EmailNotificationUtil;

public class NotificationAction extends Action {

	private Manager manager;
	List<String> customerEmailList;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = "";
		manager = (Manager) BeanUtil.getBean("hotelmanager");

		String all = request.getParameter("all");
		String systemGen = request.getParameter("system");

		try {

			if (request.getParameter(SessionConstants.Common.HDNMODE) == null || request.getParameter(SessionConstants.Common.HDNMODE).equals("")) {
				// request coming from email notification interface.

				// validate session and user in session if not redirect into
				// login page.
				if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)
						&& !CommonUtil.validateSessionUser(request, SessionConstants.Common.MANAGER_PRIVILAGE_ID)) {
					return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
				}

				if (FormDataValidatorUtil.isEmailNotificationDataValid(request)) {

					if (all != null && (!all.equals(""))) {

						// Mail to all customers
						if (customerEmailList == null) {
							customerEmailList = manager.getAllCustomerEmails();
						}

						EmailDataDTO emailDataDTO;

						// set all customer emails to bcc list.
						if (customerEmailList != null && !customerEmailList.isEmpty()) {

							emailDataDTO = new EmailDataDTO();

							StringBuffer bccBuffer = new StringBuffer();

							for (String email : customerEmailList) {
								bccBuffer.append(email + ",");
							}

							emailDataDTO.setBcc(bccBuffer.toString());

							emailDataDTO.setMessage((String) request.getParameter("rte1"));

							if (systemGen == null || (systemGen.equals(""))) {
								// User generated mail.

								emailDataDTO.setFrom((String) request.getSession().getAttribute(
										SessionConstants.Common.CURRENT_USER_EMAIL));

								emailDataDTO.setUserName((String) request.getSession().getAttribute(
										SessionConstants.Common.CURRENT_USER_EMAIL));

								emailDataDTO.setPassword((String) request.getParameter("password"));

								emailDataDTO.setSubject((String) request.getParameter("subject"));

							} else {
								// System generation mail

								emailDataDTO.setSubject((String) request.getParameter("subject"));
							}

							EmailNotificationUtil.sendSpecificEmail(emailDataDTO);

							status = "success";
						}

					} else {

						// mail to particular customers.
						EmailDataDTO emailDataDTO = new EmailDataDTO();

						String message = (String) request.getParameter("rte1");
						String to = (String) request.getParameter("to");
						String cc = (String) request.getParameter("cc");
						String bcc = (String) request.getParameter("bcc");

						emailDataDTO.setMessage(message);

						if (to != null && (!to.equals(""))) {
							emailDataDTO.setTo(to);
						}

						if (cc != null && (!cc.equals(""))) {
							emailDataDTO.setCc(cc);
						}

						if (bcc != null && (!bcc.equals(""))) {
							emailDataDTO.setBcc(bcc);
						}

						if (systemGen == null || (systemGen.equals(""))) {
							// User generated mail.

							emailDataDTO.setFrom((String) request.getSession().getAttribute(
									SessionConstants.Common.CURRENT_USER_EMAIL));

							emailDataDTO.setUserName((String) request.getSession().getAttribute(
									SessionConstants.Common.CURRENT_USER_EMAIL));

							emailDataDTO.setPassword((String) request.getParameter("password"));

							emailDataDTO.setSubject((String) request.getParameter("subject"));

						} else {
							// System generation mail

							emailDataDTO.setSubject((String) request.getParameter("subject"));
						}

						EmailNotificationUtil.sendSpecificEmail(emailDataDTO);

						status = "success";
					}
				} else {

					request.setAttribute("to", request.getParameter("to"));
					request.setAttribute("cc", request.getParameter("cc"));
					request.setAttribute("bcc", request.getParameter("bcc"));
					request.setAttribute("subject", request.getParameter("subject"));
					request.setAttribute("message", request.getParameter("rte1"));

					status = "invalidData";
				}
			} else if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("inquire")) {
				// Request coming from send inquire form.
				EmailNotificationUtil.sendInquire(request);
				request.setAttribute("successInquery", resourceBundleUtil.getLocaleSpecificValue("success.Inquery"));
				status = "successInquery";
			}

		} catch (MessagingException e) {
			status = "emailException";

		} catch (Exception e) {
			status = "generalException";
		}
		return mapping.findForward(status);
	}
}