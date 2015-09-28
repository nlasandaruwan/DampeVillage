package com.dampevillage.log.dao;

import com.dampevillage.domain.Customer;

public interface LoginDao {

	Customer isValidUser(Customer customer);
}
