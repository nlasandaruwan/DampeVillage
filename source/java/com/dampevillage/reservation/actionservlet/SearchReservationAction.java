package com.dampevillage.reservation.actionservlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.Reservation;
import com.dampevillage.manager.Manager;
import com.dampevillage.reservation.formbean.SearchReservationFormBean;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;

public class SearchReservationAction extends Action {

	private Manager manager;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login page.
		if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.STAFF_PRIVILAGE_ID)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		String status = "";
		SearchReservationFormBean searchReservationFormBean = (SearchReservationFormBean) form;

		try {

			if (FormDataValidatorUtil.isReservationSearchDataValid(searchReservationFormBean, request)) {

				manager = (Manager) BeanUtil.getBean("hotelmanager");

				List<Reservation> reservations = manager.searchReservations(CommonUtil
						.stringToDate(searchReservationFormBean.getFromDate()), CommonUtil
						.stringToDate(searchReservationFormBean.getToDate()), searchReservationFormBean
						.getCustomerName());

				request.setAttribute("allReservationsForUser", reservations);
				status = "success";

			} else {
				status = "invalidReservationSearchData";
			}

		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}
}
