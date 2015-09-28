package com.dampevillage.search.actionservlet;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.manager.Manager;
import com.dampevillage.search.formbean.SearchBean;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class SearchAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = "";
		SearchBean searchBean = (SearchBean) form;

		manager = (Manager) BeanUtil.getBean("hotelmanager");

		try {

			Integer logedInUserPrivilage = (Integer) request.getSession().getAttribute(
					SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID);

			if (logedInUserPrivilage != null && logedInUserPrivilage.intValue() == 4) {
				status = "backToStaffProfile";
			} else {

				if (FormDataValidatorUtil.isRoomAvailableCheckDataValid(searchBean, request)) {
					Date fromDate = CommonUtil.stringToDate(searchBean.getFromDateSearch());
					Date toDate = CommonUtil.stringToDate(searchBean.getToDateSearch());

					int roomType = Integer.valueOf(searchBean.getRoomType());
					int roomCategory = Integer.valueOf(searchBean.getRoomCategory());

					List<String> roomList = manager.searchRooms(fromDate, toDate, roomType, roomCategory);

					if (roomList != null && !roomList.isEmpty()) {
						String[] arrRoomDetails = roomList.get(0).split("-");
						request.getSession().setAttribute(SessionConstants.Reservation.NO_OF_ROOMS_AVAILABLE,
								arrRoomDetails[4]);

						request.setAttribute("roomDetails", roomList);
					} else {
						request.setAttribute("noRoomsAvailable", resourceBundleUtil
								.getLocaleSpecificValue("search.rooms.noroom"));
					}

					status = ActionForwardConstants.SUCCESS;

				} else {
					status = "invalidSearchData";
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
