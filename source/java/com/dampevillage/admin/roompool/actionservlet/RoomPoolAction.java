package com.dampevillage.admin.roompool.actionservlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.admin.roompool.formbean.RoomPoolFormBean;
import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomPool;
import com.dampevillage.domain.RoomType;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class RoomPoolAction extends Action {

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

		RoomPoolFormBean roomPoolFormBean = (RoomPoolFormBean) form;
		try {

			// Occation of insert a new room category or edit existing one after
			// modify values.
			if (request.getParameter(SessionConstants.Common.HDNMODE) == null) {

				if (FormDataValidatorUtil.isRoomPoolDataValid(roomPoolFormBean, request)) {
					RoomPool roomPoolToSave = poupulateRoomPool(roomPoolFormBean);

					manager.saveOrUpdateRoomPool(roomPoolToSave);
					request.setAttribute("successMessage", resourceBundleUtil
							.getLocaleSpecificValue("room.pool.success.save"));
					getAllRoomPools(request);

					status = ActionForwardConstants.SUCCESS;

				} else {
					request.setAttribute("roomPoolObject", poupulateRoomPool(roomPoolFormBean));
					getAllRoomPools(request);
					status = "invalidRoomPoolData";
				}
			}

			// Load the page from administrator profile
			if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("getAllRoomPools")) {

				getAllRoomPools(request);
				status = ActionForwardConstants.SUCCESS;

			} else {
				// Request for edit an existing room Pool. Load particular
				// Pool by
				// id. and send back to user interface
				if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("editRoomPool")) {
					Integer idOfEditingRoomPool = Integer.valueOf(request.getParameter("id"));
					RoomPool roomPoolLoaded = manager.getRoomPoolById(idOfEditingRoomPool);

					request.setAttribute("roomPoolObject", roomPoolLoaded);
					getAllRoomPools(request);
					status = ActionForwardConstants.SUCCESS;

				} else {
					// delete an existing room Pool.
					if (request.getParameter(SessionConstants.Common.HDNMODE) != null
							&& request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteRoomPool")) {

						// get the id of object need to delete
						Integer idOfDeletingRoomPool = Integer.valueOf(request.getParameter("id"));

						// get the version of object need to delete
						Integer versionOfDeletingRoomPool = Integer.valueOf(request.getParameter("version"));

						// Load the existing object need to delete
						RoomPool roomPlloLoaded = manager.getRoomPoolById(idOfDeletingRoomPool);

						// set the version of ui side object to loaded object.
						roomPlloLoaded.setVersion(versionOfDeletingRoomPool);

						manager.deleteRoomPool(roomPlloLoaded);
						request.setAttribute("successDeleteMessage", resourceBundleUtil
								.getLocaleSpecificValue("room.pool.success.delete"));
						getAllRoomPools(request);
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

	private RoomPool poupulateRoomPool(RoomPoolFormBean roomPoolFormBean) {
		RoomPool roomPoolToSave = new RoomPool();

		// Default room category
		RoomCategory roomCategory = new RoomCategory();

		if (roomPoolFormBean.getRoomCategory() != null && !roomPoolFormBean.getRoomCategory().equals("")) {
			roomCategory.setId(Integer.valueOf(roomPoolFormBean.getRoomCategory().trim()).intValue());
		}
		roomPoolToSave.setRoomCategory(roomCategory);

		// Default Room type
		RoomType roomType = new RoomType();

		if (roomPoolFormBean.getRoomType() != null && !roomPoolFormBean.getRoomType().equals("")) {
			roomType.setId(Integer.valueOf(roomPoolFormBean.getRoomType().trim()).intValue());
		}
		roomPoolToSave.setRoomType(roomType);

		// Total Rooms
		roomPoolToSave.setTotalNumberOfRooms(roomPoolFormBean.getTotalRooms());

		// Editing an existing object with new values.
		if (roomPoolFormBean.getId() != 0) {
			// set editing object ID
			roomPoolToSave.setId(roomPoolFormBean.getId());
			// set editing object version
			roomPoolToSave.setVersion(roomPoolFormBean.getVersion());
		}
		return roomPoolToSave;
	}

	private void getAllRoomPools(HttpServletRequest request) {
		List<RoomPool> roomPools = manager.getAllRoomPools();

		request.setAttribute("roomPoolList", roomPools);
	}
}
