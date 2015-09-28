package com.dampevillage.admin.hotelprofile.actionservlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;

import com.dampevillage.admin.hotelprofile.formbean.HotelProfileFormBean;
import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.domain.HotelProfile;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;

public class HotelProfileAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// validate session and user in session if not redirect into login
		// page.only Admin allows this operation.
		if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)) {
			return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
		}

		String status = "";
		manager = (Manager) BeanUtil.getBean("hotelmanager");

		HotelProfileFormBean hotelProfileFormBean = (HotelProfileFormBean) form;
		try {

			// Load the page from administrator profile
			if (request.getParameter(SessionConstants.Common.HDNMODE) != null && request.getParameter(SessionConstants.Common.HDNMODE).equals("getHotelProfile")) {

				request.setAttribute("hotelProfileObject", manager.getProfile());
				status = ActionForwardConstants.SUCCESS;

			} else {

				// Update request for Hotel Profile
				if (request.getParameter(SessionConstants.Common.HDNMODE) == null || hotelProfileFormBean.getId() != 0) {

					// Validate field data.
					if (FormDataValidatorUtil.isHotelProfileDataValid(hotelProfileFormBean, request)) {
						HotelProfile hotelProfile = getProfileObject(hotelProfileFormBean);

						manager.updateProfile(hotelProfile);
						request.setAttribute("successMessage", resourceBundleUtil
								.getLocaleSpecificValue("hotelProfile.success.save"));
						request.setAttribute("hotelProfileObject", hotelProfile);

						status = ActionForwardConstants.SUCCESS;
					} else {

						request.setAttribute("hotelProfileObject", getProfileObject(hotelProfileFormBean));
						status = "invalidHotelProfileData";
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

	private HotelProfile getProfileObject(HotelProfileFormBean hotelProfileFormBean) {

		HotelProfile hotelProfile = null;

		if (hotelProfileFormBean != null) {
			hotelProfile = new HotelProfile();

			hotelProfile.setAddressOne(hotelProfileFormBean.getAddressOne());
			hotelProfile.setAddressTwo(hotelProfileFormBean.getAddressTwo());
			hotelProfile.setCity(hotelProfileFormBean.getCity());
			hotelProfile.setContactNumberOne(hotelProfileFormBean.getContactNumberOne());
			hotelProfile.setContactNumberTwo(hotelProfileFormBean.getContactNumberTwo());
			hotelProfile.setCountry(hotelProfileFormBean.getCountry());
			hotelProfile.setDescription(hotelProfileFormBean.getDescription());
			hotelProfile.setDirection(hotelProfileFormBean.getDirection());
			hotelProfile.setEmail(hotelProfileFormBean.getEmail());
			hotelProfile.setHotelName(hotelProfileFormBean.getHotelName());
			hotelProfile.setId(hotelProfileFormBean.getId());
			hotelProfile.setOtherWeb(hotelProfileFormBean.getOtherWeb());
			hotelProfile.setPostalCode(hotelProfileFormBean.getPostalCode());
			hotelProfile.setVersion(hotelProfileFormBean.getVersion());
			hotelProfile.setFax(hotelProfileFormBean.getFax());

		}
		return hotelProfile;
	}
}