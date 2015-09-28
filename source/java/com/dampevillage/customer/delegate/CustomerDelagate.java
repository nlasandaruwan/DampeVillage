package com.dampevillage.customer.delegate;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.dampevillage.customer.dao.CustomerDao;
import com.dampevillage.domain.Customer;
import com.dampevillage.util.emailnotification.EmailNotificationUtil;

public class CustomerDelagate {

	private CustomerDao customerDao;

	public void saveOrUpdateUser(Customer customer) throws ClassNotFoundException, IOException, MessagingException {
		customerDao.saveOrUpdateUser(customer);

	}

	public void recoverUser(Customer customer) throws ClassNotFoundException, IOException, MessagingException {
		customerDao.saveOrUpdateUser(customer);
		EmailNotificationUtil.notifyPasswordRecovery(customer);
	}

	public Customer getCustomerById(Integer id) {
		return customerDao.getUserById(id);
	}

	public List<Customer> getCustomersByPrivilageId(Integer id) {
		return customerDao.getUsersByPrivilageId(id);
	}

	public void deleteUser(Customer customer) {
		customerDao.deleteUser(customer);
	}

	public Customer getUserToRecoverPassword(Customer customer) {
		return customerDao.getUserToRecoverPassword(customer);
	}

	public List<String> getAllCustomerEmails() {
		return customerDao.getAllCustomerEmails();

	}

	/**
	 * @return the customerDao
	 */
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	/**
	 * @param customerDao
	 *            the customerDao to set
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

}
