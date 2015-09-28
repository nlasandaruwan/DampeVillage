package com.dampevillage.log.delegate;

import com.dampevillage.domain.Customer;
import com.dampevillage.log.dao.LoginDao;


public class LoginDelagate{

	private LoginDao loginDao;

	public Customer isValidUser(Customer customer){
		return loginDao.isValidUser(customer);
	}
	/**
	 * @return the loginDao
	 */
	public LoginDao getLoginDao() {
		return loginDao;
	}

	/**
	 * @param loginDao the loginDao to set
	 */
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
}
