package com.dampevillage.admin.room.actionservlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;

import com.dampevillage.admin.room.formbean.RoomFormBean;
import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.Room;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomType;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class RoomAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login page.
		if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		boolean isDelete = false;
		String status = "";
		manager = (Manager) BeanUtil.getBean("hotelmanager");

		RoomFormBean roomFormBean = (RoomFormBean) form;
		try {

			// Occation of insert a new room category or edit existing one after
			// modify values.
			if (request.getParameter(SessionConstants.Common.HDNMODE) == null) {

				if (FormDataValidatorUtil.isRoomDataValid(roomFormBean, request)) {
					Room roomToSave = populateRoom(roomFormBean);

					manager.saveOrUpdateRoom(roomToSave);
					request.setAttribute("successMessage", resourceBundleUtil
							.getLocaleSpecificValue("room.success.save"));
					getAllRooms(request);

					status = ActionForwardConstants.SUCCESS;
				} else {
					request.setAttribute("roomObject", populateRoom(roomFormBean));
					getAllRooms(request);
					status = "invalidRoomData";
				}

			}

			// Load the page from administrator profile
			if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("getAllRooms")) {

				getAllRooms(request);
				status = ActionForwardConstants.SUCCESS;

			} else {
				// Request for edit an existing room category. Load particular
				// category by
				// id. and send back to user interface
				if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("editRoom")) {
					Integer idOfEditingCategory = Integer.valueOf(request.getParameter("id"));
					Room roomLoaded = manager.getRoomById(idOfEditingCategory);

					request.setAttribute("roomObject", roomLoaded);
					getAllRooms(request);
					status = ActionForwardConstants.SUCCESS;

				} else {
					// delete an existing room category.
					if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteRoom")) {

						isDelete = true;

						// get the id of object need to delete
						Integer idOfDeletingRoom = Integer.valueOf(request.getParameter("id"));

						// get the version of object need to delete
						Integer versionOfDeletingRoom = Integer.valueOf(request.getParameter("version"));

						// Load the existing object need to delete
						Room roomLoaded = manager.getRoomById(idOfDeletingRoom);

						// set the version of ui side object to loaded object.
						roomLoaded.setVersion(versionOfDeletingRoom);

						manager.deleteRoom(roomLoaded);
						request.setAttribute("successDeleteMessage", resourceBundleUtil
								.getLocaleSpecificValue("room.success.delete"));
						getAllRooms(request);
						status = ActionForwardConstants.SUCCESS;
					}
				}
			}

		} catch (DataIntegrityViolationException e) {

			if (isDelete) {
				request.setAttribute("RoomAlreadyEngagedException", resourceBundleUtil
						.getLocaleSpecificValue("room.already.engaged"));
				getAllRooms(request);
				status = ActionForwardConstants.SUCCESS;
			} else {
				request.setAttribute("DuplicateRoomNumberException", resourceBundleUtil
						.getLocaleSpecificValue("room.duplicate.room.number"));
				getAllRooms(request);
				status = ActionForwardConstants.SUCCESS;
			}

		}

		catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}

	private Room populateRoom(RoomFormBean roomFormBean) {
		Room roomToSave = new Room();

		// Default room category
		RoomCategory roomCategory = new RoomCategory();

		if (roomFormBean.getRoomCategory() != null && !roomFormBean.getRoomCategory().equals("")) {
			roomCategory.setId(Integer.valueOf(roomFormBean.getRoomCategory().trim()).intValue());
		}

		// Default Room type
		RoomType roomType = new RoomType();

		if (roomFormBean.getRoomType() != null && !roomFormBean.getRoomType().equals("")) {
			roomType.setId(Integer.valueOf(roomFormBean.getRoomType().trim()).intValue());
		}

		roomToSave.setRoomCategory(roomCategory);
		roomToSave.setRoomDescription(roomFormBean.getDescription());
		roomToSave.setRoomType(roomType);
		roomToSave.setFloorNumber(roomFormBean.getFloorNumber());
		roomToSave.setRoomNumber(roomFormBean.getRoomNumber());

		// Editing an existing object with new values.
		if (roomFormBean.getId() != 0) {
			// set editing object ID
			roomToSave.setId(roomFormBean.getId());
			// set editing object version
			roomToSave.setVersion(roomFormBean.getVersion());
		}
		return roomToSave;
	}

	private void getAllRooms(HttpServletRequest request) {
		List<Room> roomCategories = manager.getAllRooms();

		request.setAttribute("roomList", roomCategories);
	}
}
