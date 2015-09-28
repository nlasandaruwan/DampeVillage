package com.dampevillage.customer.dao;

import java.util.List;

import com.dampevillage.domain.Customer;

public interface CustomerDao {

	void saveOrUpdateUser(Customer customer);

	Customer getUserById(Integer id);

	List<Customer> getUsersByPrivilageId(Integer privilageId);

	void deleteUser(Customer customer);

	Customer getUserToRecoverPassword(Customer customer);

	List<String> getAllCustomerEmails();
}
