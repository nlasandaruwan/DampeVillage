package com.dampevillage.admin.roomcatagory.actionservlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;

import com.dampevillage.admin.roomcatagory.formbean.RoomCategoryFormBean;
import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class RoomCategoryAction extends Action {

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

		RoomCategoryFormBean roomCategoryFormBean = (RoomCategoryFormBean) form;
		try {

			// Occation of insert a new room category or edit existing one after
			// modify values.
			if (request.getParameter(SessionConstants.Common.HDNMODE) == null) {

				if (FormDataValidatorUtil.isRoomCaregoryDataValid(roomCategoryFormBean, request)) {
					RoomCategory categoryToSave = populateRoomCategory(roomCategoryFormBean);

					manager.saveOrUpdateRoomCategory(categoryToSave);
					request.setAttribute("successMessage", resourceBundleUtil
							.getLocaleSpecificValue("room.category.success.save"));
					getAllRoomCategories(request);
					status = "success";

				} else {
					request.setAttribute("roomCategoryObject", populateRoomCategory(roomCategoryFormBean));
					getAllRoomCategories(request);
					status = "invalidRoomCategoryData";
				}

			}

			// Room Search result click room category details.
			if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("viewRoomCategory")) {

				Integer idOfEditingCategory = Integer.valueOf(request.getParameter("id"));
				RoomCategory roomCategoryLoaded = manager.getRoomCategoryById(idOfEditingCategory);

				request.setAttribute("roomCategoryObject", roomCategoryLoaded);
				status = "successPopUp";

			} else {

				// Load the page from administrator profile
				if (request.getParameter(SessionConstants.Common.HDNMODE) != null
						&& request.getParameter(SessionConstants.Common.HDNMODE).equals("getAllRoomCategory")) {

					getAllRoomCategories(request);
					status = ActionForwardConstants.SUCCESS;

				} else {
					// Request for edit an existing room category. Load
					// particular
					// category by
					// id. and send back to user interface
					if (request.getParameter(SessionConstants.Common.HDNMODE) != null
							&& request.getParameter(SessionConstants.Common.HDNMODE).equals("editRoomCategory")) {
						Integer idOfEditingCategory = Integer.valueOf(request.getParameter("id"));
						RoomCategory roomCategoryLoaded = manager.getRoomCategoryById(idOfEditingCategory);

						request.setAttribute("roomCategoryObject", roomCategoryLoaded);
						getAllRoomCategories(request);
						status = ActionForwardConstants.SUCCESS;

					} else {
						// delete an existing room category.
						if (request.getParameter(SessionConstants.Common.HDNMODE) != null
								&& request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteRoomCategory")) {

							// get the id of object need to delete
							Integer idOfDeletingCategory = Integer.valueOf(request.getParameter("id"));

							// get the version of object need to delete
							Integer versionOfDeletingCategory = Integer.valueOf(request.getParameter("version"));

							// Load the existing object need to delete
							RoomCategory roomCategoryLoaded = manager.getRoomCategoryById(idOfDeletingCategory);

							// set the version of ui side object to loaded
							// object.
							roomCategoryLoaded.setVersion(versionOfDeletingCategory);

							manager.deleteRoomCategory(roomCategoryLoaded);
							request.setAttribute("successDeleteMessage", resourceBundleUtil
									.getLocaleSpecificValue("room.category.success.delete"));
							getAllRoomCategories(request);
							status = ActionForwardConstants.SUCCESS;
						}
					}
				}
			}

		} catch (DataIntegrityViolationException e) {
			request.setAttribute("RoomCategoryException", resourceBundleUtil
					.getLocaleSpecificValue("roomCategory.already.engaged"));
			getAllRoomCategories(request);
			status = "exceptionInRoomCategory";
		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}

	private RoomCategory populateRoomCategory(RoomCategoryFormBean roomCategoryFormBean) {
		RoomCategory categoryToSave = new RoomCategory();
		categoryToSave.setRoomCategoryType(roomCategoryFormBean.getCategoryType());

		categoryToSave.setDescription(roomCategoryFormBean.getDescription());

		// Editing an existing object with new values.
		if (roomCategoryFormBean.getId() != 0) {
			// set editing object ID
			categoryToSave.setId(roomCategoryFormBean.getId());
			// set editing object version
			categoryToSave.setVersion(roomCategoryFormBean.getVersion());
		}
		return categoryToSave;
	}

	private void getAllRoomCategories(HttpServletRequest request) {
		List<RoomCategory> roomCategories = manager.getAllRoomCategory();

		request.setAttribute("roomCategoryList", roomCategories);
	}
}
