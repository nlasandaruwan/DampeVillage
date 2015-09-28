package com.dampevillage.customer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.customer.dao.CustomerDao;
import com.dampevillage.domain.Customer;
import com.dampevillage.domain.Privilage;

public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements CustomerDao {

	public CustomerDaoImpl() {
		super(Customer.class);
	}

	@Override
	public void saveOrUpdateUser(Customer customer) {

		Privilage privilage = (Privilage) customer.getPrivilage();
		Privilage privilageLoaded = (Privilage) getHibernateTemplate().get(Privilage.class, privilage.getId());

		customer.setPrivilage(privilageLoaded);
		getHibernateTemplate().saveOrUpdate(customer);

	}

	@Override
	public Customer getUserById(Integer id) {
		return getEntityById(id);
	}

	@Override
	public List<Customer> getUsersByPrivilageId(Integer privilageId) {

		Object[] values = { privilageId };

		List<Customer> customerByPrivilageList = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.Customer.customersByPrivilage", values);
		return customerByPrivilageList;
	}

	@Override
	public void deleteUser(Customer customer) {

		Customer customerLoaded = getEntityById(customer.getId());
		customerLoaded.setVersion(customer.getVersion());
		getHibernateTemplate().delete(customerLoaded);
	}

	@Override
	public Customer getUserToRecoverPassword(Customer customer) {

		Object[] values = { customer.getRecoverQuestion(), customer.getRecoverAnswer(), customer.getUserName(),
				customer.getEmail() };

		Customer customerToRecover = null;
		List<Customer> customerToRecoverList = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.Customer.loadCustomerToRecoverpassword", values);

		if (customerToRecoverList != null && !customerToRecoverList.isEmpty()) {
			customerToRecover = customerToRecoverList.get(0);
		}
		return customerToRecover;

	}

	@Override
	public List<String> getAllCustomerEmails() {
		
		List<Customer> customerList = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.Customer.customersByPrivilage", 3);
		
		return fetchEmailList(customerList);
	}

	private List<String> fetchEmailList(List<Customer> customers) {

		List<String> emailList = null;

		if (customers != null && !customers.isEmpty()) {

			emailList = new ArrayList<String>();

			for (Customer customer : customers) {
				emailList.add(customer.getEmail());
			}
		}
		return emailList;
	}
}
