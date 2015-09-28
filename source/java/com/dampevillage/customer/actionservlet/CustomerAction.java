package com.dampevillage.customer.actionservlet;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;

import com.dampevillage.common.util.ActionForwardConstants;
import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.common.util.SessionConstants;
import com.dampevillage.customer.formbean.CustomerFormBean;
import com.dampevillage.domain.Customer;
import com.dampevillage.domain.Privilage;
import com.dampevillage.manager.Manager;
import com.dampevillage.util.BeanUtil;
import com.dampevillage.util.FormDataValidatorUtil;
import com.dampevillage.util.ResourceBundleUtil;
import com.dampevillage.util.emailnotification.EmailNotificationUtil;

public class CustomerAction extends Action {

	private Manager manager;
	private static ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = "";
		boolean isDelete = false;
		CustomerFormBean customerFormBean = (CustomerFormBean) form;

		try {

			manager = (Manager) BeanUtil.getBean("hotelmanager");

			if (request.getParameter(SessionConstants.Common.HDNMODE) != null
					&& request.getParameter(SessionConstants.Common.HDNMODE).equals("getAllCustomers")) {
				setAllUsersIntoRequest(request, SessionConstants.Common.CUSTOMER_PRIVILAGE_ID);
				status = "viewAllCustomers";

			} else {

				// Request for Delete a manager, customer or staff user.
				if (request.getParameter(SessionConstants.Common.HDNMODE) != null
						&& (request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteManager")
								|| request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteStaff") || request
								.getParameter(SessionConstants.Common.HDNMODE).equals("deleteCustomer"))) {

					// validate session and user in session if not redirect into
					// login page. Only Admin allows this operation.
					if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)) {
						return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
					}

					isDelete = true;
					// Generally delete any (customer, staff or manager) user

					Customer customerToDelete = new Customer();
					customerToDelete.setId(customerFormBean.getId());
					customerToDelete.setVersion(customerFormBean.getVersion());
					customerToDelete.setEmail(customerFormBean.getEmail());
					customerToDelete.setFirstName(customerFormBean.getFirstName());
					manager.deleteUser(customerToDelete);

					request.setAttribute("successDeleteMessage", resourceBundleUtil
							.getLocaleSpecificValue("user.success.delete"));

					EmailNotificationUtil.notifyCustomerDeletion(customerToDelete);

					if (request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteManager")) {
						setAllUsersIntoRequest(request, SessionConstants.Common.MANAGER_PRIVILAGE_ID);
						request.setAttribute(SessionConstants.Common.HDNMODE, "addManager");
						status = ActionForwardConstants.EDIT_USER;
					} else {
						if (request.getParameter(SessionConstants.Common.HDNMODE).equals("deleteStaff")) {
							setAllUsersIntoRequest(request, SessionConstants.Common.STAFF_PRIVILAGE_ID);
							request.setAttribute(SessionConstants.Common.HDNMODE, "addStaff");
							status = ActionForwardConstants.EDIT_USER;
						} else {
							setAllUsersIntoRequest(request, SessionConstants.Common.CUSTOMER_PRIVILAGE_ID);
							status = "viewAllCustomers";
						}
					}

				} else {
					// Request for add new manager. Logging user an
					// administrator.
					if (request.getParameter(SessionConstants.Common.HDNMODE) != null
							&& request.getParameter(SessionConstants.Common.HDNMODE).equals("addManager")) {

						// validate session and user in session if not redirect
						// into login page.Only Admin allows this operation.
						if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)) {
							return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
						}

						request.setAttribute(SessionConstants.Common.HDNMODE, "addManager");
						// Load all managers
						setAllUsersIntoRequest(request, SessionConstants.Common.MANAGER_PRIVILAGE_ID);
						status = ActionForwardConstants.EDIT_USER;
					} else {

						// Request for add new staff member. Logging user an
						// administrator.
						if (request.getParameter(SessionConstants.Common.HDNMODE) != null
								&& request.getParameter(SessionConstants.Common.HDNMODE).equals("addStaff")) {

							// validate session and user in session if not
							// redirect into login page.Only Admin allows this
							// operation.
							if (!CommonUtil.validateSessionUser(request, SessionConstants.Common.ADMIN_PRIVILAGE_ID)) {
								return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
							}

							request.setAttribute(SessionConstants.Common.HDNMODE, "addStaff");

							// Load all staff users.
							setAllUsersIntoRequest(request, SessionConstants.Common.STAFF_PRIVILAGE_ID);
							status = ActionForwardConstants.EDIT_USER;
						} else {
							// request for edit existing user load existing user
							if (request.getParameter(SessionConstants.Common.HDNMODE) != null
									&& request.getParameter(SessionConstants.Common.HDNMODE).equals("editCustomer")) {

								// validate session and user in session if not
								// redirect into login page.
								if (!CommonUtil.validateSessionUser(request)) {
									return mapping.findForward(SessionConstants.Common.SESSION_INVALID_GLOBAL_FORWARD);
								}

								loadExistingUser(request);
								status = ActionForwardConstants.EDIT_USER;

							} else {

								// Validate fields
								if (FormDataValidatorUtil.isUserRegisrationDataValid(customerFormBean, request)) {
									// request for add new or edit existing user
									// save or
									// update

									Customer customer = saveOrUpdateUser(customerFormBean, request);

									Integer logedInUserPrivilage = (Integer) request.getSession().getAttribute(
											SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID);

									// If user staff redirect into staff
									// specific
									// profile
									// page.
									if (logedInUserPrivilage != null && logedInUserPrivilage.intValue() == 4) {
										status = "successStaff";
									} else {
										// Else redirect into common profile
										// page
										// with
										// all users load
										if (logedInUserPrivilage != null && logedInUserPrivilage.intValue() == 1) {
											setAllUsersIntoRequest(request, customer.getPrivilage().getId());
										}

										status = ActionForwardConstants.SUCCESS;
									}

								} else {

									// if fields not valid
									Integer logedInUserPrivilage = (Integer) request.getSession().getAttribute(
											SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID);

									Customer customerPopulated = getCustomerObject(customerFormBean);
									request.setAttribute("customerObject", customerPopulated);

									if (logedInUserPrivilage != null && logedInUserPrivilage.intValue() == 1) {
										setAllUsersIntoRequest(request, customerPopulated.getPrivilage().getId());
									}
									status = "invalidUserData";

								}
							}
						}
					}
				}
			}

		} catch (DataIntegrityViolationException e) {

			// DataIntegrityViolationException occures due to delete customer
			// with pending reservations.
			if (isDelete) {
				// Trying to delete a customer who already have reservations in
				// the
				// system.

				request.setAttribute("DataIntegrityViolationExceptionDelete", resourceBundleUtil
						.getLocaleSpecificValue("user.remove.integrity.violation"));

				setAllUsersIntoRequest(request, SessionConstants.Common.CUSTOMER_PRIVILAGE_ID);
				status = "viewAllCustomers";
			} else {
				// Trying to insert a customer with user name already in system.
				request.setAttribute("DataIntegrityViolationExceptionInsert", resourceBundleUtil
						.getLocaleSpecificValue("user.name.already.available"));

				// set the customer object back to ui
				request.setAttribute("customerObject", getCustomerObject(customerFormBean));

				if (customerFormBean.getPrivilageId() == SessionConstants.Common.MANAGER_PRIVILAGE_ID) {
					request.setAttribute(SessionConstants.Common.HDNMODE, "addManager");
				}
				if (customerFormBean.getPrivilageId() == SessionConstants.Common.STAFF_PRIVILAGE_ID) {
					request.setAttribute(SessionConstants.Common.HDNMODE, "addStaff");
				}

				status = ActionForwardConstants.EDIT_USER;
			}

		} catch (MessagingException e) {
			status = "emailException";
		} catch (IOException e) {
			status = "generalException";
		} catch (ClassNotFoundException e) {
			status = "generalException";
		} catch (NestedRuntimeException e) {
			status = "databaseException";
		} catch (Exception e) {
			status = "generalException";
		}

		return mapping.findForward(status);
	}

	private void setAllUsersIntoRequest(HttpServletRequest request, Integer userPrivilage) {
		List<Customer> users = manager.getSpecificUserType(userPrivilage);
		// Set Manager list in request.
		if (userPrivilage.equals(SessionConstants.Common.MANAGER_PRIVILAGE_ID)) {
			request.setAttribute("managersToDelete", users);
			request.setAttribute(SessionConstants.Common.HDNMODE, "addManager");
		} else {
			if (userPrivilage.equals(SessionConstants.Common.STAFF_PRIVILAGE_ID)) {
				request.setAttribute("staffToDelete", users);
				request.setAttribute(SessionConstants.Common.HDNMODE, "addStaff");
			} else {
				request.setAttribute("customersToDelete", users);
			}
		}
	}

	private void loadExistingUser(HttpServletRequest request) {
		Integer currentUserId = (Integer) request.getSession().getAttribute(SessionConstants.Common.CURRENT_USER_ID);
		Customer customerLoaded = manager.getUserById(currentUserId);
		request.setAttribute("customerObject", customerLoaded);
	}

	private Customer saveOrUpdateUser(CustomerFormBean customerFormBean, HttpServletRequest servletRequest)
			throws ClassNotFoundException, IOException, MessagingException {

		Customer customer = getCustomerObject(customerFormBean);

		// Id null = new external user creation
		// Id = 2 or 4 means Admin creates a staff or manager account.

		Integer currentUserId = (Integer) servletRequest.getSession().getAttribute(
				SessionConstants.Common.CURRENT_USER_ID);

		if ((currentUserId == null)
				|| ((currentUserId != null && currentUserId.equals(1)) && customer.getPrivilage().getId() == 2)
				|| ((currentUserId != null && currentUserId.equals(1)) && customer.getPrivilage().getId() == 4)) {

			manager.saveOrUpdateUser(customer);
			if (customer.getPrivilage().getId() != 2 && customer.getPrivilage().getId() != 4) {

				// When Administrator creates staff and manager users customer
				// privilage id will be 2 or 4
				CommonUtil.setUserSessionData(servletRequest, customer);
			}

			EmailNotificationUtil.notifyUserCreation(customer);

		} else {
			manager.saveOrUpdateUser(customer);
			if (customer.getPrivilage().getId() != 2 && customer.getPrivilage().getId() != 4) {

				// When Administrator creates staff and manager users customer
				// privilage id will be 2 or 4
				CommonUtil.setUserSessionData(servletRequest, customer);
			}
		}

		servletRequest.setAttribute("successMessage", resourceBundleUtil.getLocaleSpecificValue("user.success.save"));

		return customer;
	}

	private Customer getCustomerObject(CustomerFormBean customerFormBean) {

		Customer customer = null;
		if (null != customerFormBean) {

			customer = new Customer();

			customer.setAddressOne(customerFormBean.getAddressOne());
			customer.setAddressTwo(customerFormBean.getAddressTwo());
			customer.setStreetNumber(customerFormBean.getStreetNumber());
			customer.setContactLand(customerFormBean.getContactLand());
			customer.setContactMobile(customerFormBean.getContactMobile());
			customer.setCountry(customerFormBean.getCountry());
			customer.setEmail(customerFormBean.getEmail());
			customer.setFirstName(customerFormBean.getFirstName());

			customer.setId(customerFormBean.getId());
			customer.setLasteName(customerFormBean.getLasteName());
			customer.setNicPassportNumber(customerFormBean.getNicPassportNumber());
			customer.setPassword(customerFormBean.getPassword());
			customer.setPostalCode(customerFormBean.getPostalCode());
			customer.setState(customerFormBean.getState());

			customer.setTitle(customerFormBean.getTitle());
			customer.setUserName(customerFormBean.getUserName());
			customer.setVersion(customerFormBean.getVersion());

			Privilage privilage = new Privilage();
			privilage.setId(customerFormBean.getPrivilageId());

			customer.setPrivilage(privilage);

			customer.setRecoverQuestion(customerFormBean.getRecoverQuestion());
			customer.setRecoverAnswer(customerFormBean.getRecoverAnswer());
		}
		return customer;
	}
}
