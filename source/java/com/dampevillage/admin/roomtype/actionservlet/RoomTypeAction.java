package com.dampevillage.admin.roomtype.actionservlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;

import com.dampevillage.admin.roomtype.formbean.RoomTypeFormBean;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.RoomType;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class RoomTypeAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login page.
		if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		String status = "";
		manager = (Manager) BeanUtil.getBean("hotelmanager");

		RoomTypeFormBean roomTypeFormBean = (RoomTypeFormBean) form;
		try {

			// Occation of insert a new room category or edit existing one after
			// modify values.
			if (request.getParameter("hdnMode") == null) {

				if (FormDataValidatorUtil.isRoomTypeDataValid(roomTypeFormBean, request)) {
					RoomType roomTypeToSave = populateRoomType(roomTypeFormBean);

					manager.saveOrUpdateRoomType(roomTypeToSave);
					request.setAttribute("successMessage", resourceBundleUtil
							.getLocaleSpecificValue("room.type.success.save"));
					getAllRoomTypes(request);

					status = "success";
				} else {

					request.setAttribute("roomTypeObject", populateRoomType(roomTypeFormBean));
					getAllRoomTypes(request);
					status = "invalidRoomTypeData";
				}

			}

			// Load the page from administrator profile
			if (request.getParameter("hdnMode") != null && request.getParameter("hdnMode").equals("getAllRoomType")) {

				getAllRoomTypes(request);
				status = "success";

			} else {
				// Request for edit an existing room category. Load particular
				// category by
				// id. and send back to user interface
				if (request.getParameter("hdnMode") != null && request.getParameter("hdnMode").equals("editRoomType")) {
					Integer idOfEditingType = Integer.valueOf(request.getParameter("id"));
					RoomType roomTypeLoaded = manager.getRoomTypeById(idOfEditingType);

					request.setAttribute("roomTypeObject", roomTypeLoaded);
					getAllRoomTypes(request);
					status = "success";

				} else {
					// delete an existing room category.
					if (request.getParameter("hdnMode") != null
							&& request.getParameter("hdnMode").equals("deleteRoomType")) {

						// get the id of object need to delete
						Integer idOfDeletingType = Integer.valueOf(request.getParameter("id"));

						// get the version of object need to delete
						Integer versionOfDeletingType = Integer.valueOf(request.getParameter("version"));

						// Load the existing object need to delete
						RoomType roomTypeLoaded = manager.getRoomTypeById(idOfDeletingType);

						// set the version of ui side object to loaded object.
						roomTypeLoaded.setVersion(versionOfDeletingType);

						manager.deleteRoomType(roomTypeLoaded);
						request.setAttribute("successDeleteMessage", resourceBundleUtil
								.getLocaleSpecificValue("room.type.success.delete"));
						getAllRoomTypes(request);
						status = "success";
					}
				}
			}

		} catch (DataIntegrityViolationException e) {

			request.setAttribute("RoomTypeException", resourceBundleUtil
					.getLocaleSpecificValue("roomType.already.engaged"));

			getAllRoomTypes(request);
			status = "exceptionInRoomType";

		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}

	private RoomType populateRoomType(RoomTypeFormBean roomTypeFormBean) {
		RoomType roomTypeToSave = new RoomType();
		roomTypeToSave.setRoomType(roomTypeFormBean.getRoomType());

		roomTypeToSave.setDescription(roomTypeFormBean.getDescription());

		// Editing an existing object with new values.
		if (roomTypeFormBean.getId() != 0) {
			// set editing object ID
			roomTypeToSave.setId(roomTypeFormBean.getId());
			// set editing object version
			roomTypeToSave.setVersion(roomTypeFormBean.getVersion());
		}
		return roomTypeToSave;
	}

	private void getAllRoomTypes(HttpServletRequest request) {
		List<RoomType> roomCategories = manager.getAllRoomType();

		request.setAttribute("roomTypeList", roomCategories);
	}
}
