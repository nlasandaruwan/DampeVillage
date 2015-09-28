package com.dampevillage.log.actionservlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.domain.Customer;
import com.dampevillage.log.formbean.PasswordRecoverFormBean;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class PassworRecoverAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = "";
		PasswordRecoverFormBean passwordRecoverFormBean = (PasswordRecoverFormBean) form;

		try {

			if (FormDataValidatorUtil.isPasswordRecoverDataValid(passwordRecoverFormBean, request)) {
				manager = (Manager) BeanUtil.getBean("hotelmanager");

				Customer customer = new Customer();
				customer.setRecoverQuestion(passwordRecoverFormBean.getRecoverQuestion());
				customer.setRecoverAnswer(passwordRecoverFormBean.getRecoverAnswer());
				customer.setEmail(passwordRecoverFormBean.getEmail());
				customer.setUserName(passwordRecoverFormBean.getUserName());

				Customer customerLoaded = manager.getUserToRecoverPassword(customer);

				if (customerLoaded == null) {
					request.setAttribute("invalidDetails", resourceBundleUtil
							.getLocaleSpecificValue("password.recover.fail"));
					status = ActionForwardConstants.SUCCESS;
				} else {

					double d = Math.random();
					String newPassword = "RECOVPWD" + Math.round(d);
					customerLoaded.setPassword(newPassword);
					manager.recoverUser(customerLoaded);

					request.setAttribute("recoverd", resourceBundleUtil
							.getLocaleSpecificValue("password.recover.success"));
					status = ActionForwardConstants.SUCCESS;
				}
			} else {

				request.setAttribute("passwordRecoverFormBean", passwordRecoverFormBean);
				status = "invalidPasswordRecoverData";
			}

		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}
		return mapping.findForward(status);
	}
}
