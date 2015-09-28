package com.dampevillage.admin.roomrate.actionservlet;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.admin.roomrate.formbean.RoomRateFormBean;
import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.AccomodationMode;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomRate;
import com.dampevillage.domain.RoomType;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class RoomRateAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login page.
		if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)
				&& !CommonUtil.validateSessionUser(request, SessionConstants.Common.MANAGER_PRIVILAGE_ID)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		String status = "";
		manager = (Manager) BeanUtil.getBean("hotelmanager");

		RoomRateFormBean roomRateFormBean = (RoomRateFormBean) form;
		try {

			// Occation of insert a new room category or edit existing one after
			// modify values.
			if (request.getParameter(SessionConstants.Common.HDNMODE) == null) {

				if (FormDataValidatorUtil.isRoomRateDataValid(roomRateFormBean, request)) {

					boolean isDateRangeOverLap = manager.isRateDatesOverlap(CommonUtil.stringToDate(roomRateFormBean
							.getValidFrom()), CommonUtil.stringToDate(roomRateFormBean.getValidTo()), Integer.valueOf(
							roomRateFormBean.getRoomCategory().trim()).intValue(), Integer.valueOf(
							roomRateFormBean.getRoomType().trim()).intValue(), Integer.valueOf(
							roomRateFormBean.getAccomodationMode().trim()).intValue());

					// Date ranges are not overlap.

					if (!isDateRangeOverLap) {
						RoomRate roomRateToSave = populateRoomRate(roomRateFormBean, false, request);

						manager.saveOrUpdateRoomRate(roomRateToSave);
						request.setAttribute("successMessage", resourceBundleUtil
								.getLocaleSpecificValue("room.rate.success.save"));
						getAllRoomRates(request);
						status = ActionForwardConstants.SUCCESS;

					} else {

						// date range of room rate going to define is overlap
						// with
						// existing room rate. Should not allows to insert new
						// room
						// rate range

						// populate room rate from ui object.
						RoomRate roomRateLoaded = populateRoomRate(roomRateFormBean, true, request);

						request.setAttribute("roomRateObject", roomRateLoaded);
						request.setAttribute("rateDatesOverLap", resourceBundleUtil
								.getLocaleSpecificValue("roomRate.overlap"));

						getAllRoomRates(request);
						status = "dateOverlapException";
					}

				} else {

					request.setAttribute("roomRateObject", populateRoomRate(roomRateFormBean, false, request));
					getAllRoomRates(request);
					status = "invalidRoomRateData";

				}
			}

			// Load the page from administrator profile
			if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("getAllRoomRates")) {

				getAllRoomRates(request);
				status = ActionForwardConstants.SUCCESS;

			} else {
				// Request for edit an existing room rate. Load particular
				// rate by
				// id. and send back to user interface
				if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("editRoomRate")) {
					Integer idOfEditingRoomRate = Integer.valueOf(request.getParameter("id"));
					RoomRate roomRateLoaded = manager.getRoomRateById(idOfEditingRoomRate);

					request.setAttribute("roomRateObject", roomRateLoaded);
					getAllRoomRates(request);
					status = ActionForwardConstants.SUCCESS;

				} else {
					// delete an existing room Rate.
					if (request.getParameter(SessionConstants.Common.HDNMODE) != null
							&& request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteRoomRate")) {

						// get the id of object need to delete
						Integer idOfDeletingRoomRate = Integer.valueOf(request.getParameter("id"));

						// get the version of object need to delete
						Integer versionOfDeletingRoomRate = Integer.valueOf(request.getParameter("version"));

						// Load the existing object need to delete
						RoomRate roomRateLoaded = manager.getRoomRateById(idOfDeletingRoomRate);

						// set the version of ui side object to loaded object.
						roomRateLoaded.setVersion(versionOfDeletingRoomRate);

						manager.deleteRoomRate(roomRateLoaded);
						request.setAttribute("successDeleteMessage", resourceBundleUtil
								.getLocaleSpecificValue("room.rate.success.delete"));
						getAllRoomRates(request);
						status = ActionForwardConstants.SUCCESS;
					}
				}
			}

		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}

	private RoomRate populateRoomRate(RoomRateFormBean roomRateFormBean, boolean isDateOverLap,
			HttpServletRequest request) {
		RoomRate roomRateToSave = new RoomRate();

		// Default room category
		RoomCategory roomCategory = new RoomCategory();

		if (roomRateFormBean.getRoomCategory() != null && !roomRateFormBean.getRoomCategory().equals("")) {
			roomCategory.setId(Integer.valueOf(roomRateFormBean.getRoomCategory().trim()).intValue());
		}
		roomRateToSave.setRoomCategory(roomCategory);

		// Default Room type
		RoomType roomType = new RoomType();

		if (roomRateFormBean.getRoomType() != null && !roomRateFormBean.getRoomType().equals("")) {
			roomType.setId(Integer.valueOf(roomRateFormBean.getRoomType().trim()).intValue());
		}
		roomRateToSave.setRoomType(roomType);

		// Default accomodation mode
		AccomodationMode accomodationMode = new AccomodationMode();

		if (roomRateFormBean.getAccomodationMode() != null && !roomRateFormBean.getAccomodationMode().equals("")) {
			accomodationMode.setId(Integer.valueOf(roomRateFormBean.getAccomodationMode().trim()).intValue());
		}
		roomRateToSave.setAccomodationMode(accomodationMode);

		if (validateDateFormat(roomRateFormBean.getValidFrom())) {
			// Valid from
			if (roomRateFormBean.getValidFrom() != null && !roomRateFormBean.getValidFrom().equals("")) {
				Date fromDate = CommonUtil.stringToDate(roomRateFormBean.getValidFrom());
				roomRateToSave.setValidFrom(fromDate);
			} else {
				roomRateToSave.setValidFrom(null);
			}
		}
		if (validateDateFormat(roomRateFormBean.getValidTo())) {
			// Valid to
			if (roomRateFormBean.getValidTo() != null && !roomRateFormBean.getValidTo().equals("")) {
				Date toDate = CommonUtil.stringToDate(roomRateFormBean.getValidTo());
				roomRateToSave.setValidTo(toDate);

			} else {
				roomRateToSave.setValidTo(null);
			}
		}

		roomRateToSave.setPrice(roomRateFormBean.getPrice());

		// Editing an existing object with new values.
		if (roomRateFormBean.getId() != 0) {
			// set editing object ID
			roomRateToSave.setId(roomRateFormBean.getId());
			// set editing object version
			roomRateToSave.setVersion(roomRateFormBean.getVersion());
		}
		return roomRateToSave;
	}

	private void getAllRoomRates(HttpServletRequest request) {
		List<RoomRate> roomRates = manager.getAllRoomRates();

		request.setAttribute("roomRateList", roomRates);
	}

	private static boolean validateDateFormat(String date) {

		// validation format 2010/01/01
		String dateFormat1 = "^[0-9]{4}/(((0[1-9]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

		// validation format 2010/1/1
		String dateFormat2 = "^[0-9]{4}/((([1-9]|(10|12))/([1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

		return date.trim().matches(dateFormat1) || date.trim().matches(dateFormat2);
	}
}
